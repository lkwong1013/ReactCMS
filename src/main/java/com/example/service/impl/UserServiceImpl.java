package com.example.service.impl;

import com.example.exception.BaseException;
import com.example.exception.DuplicateRecordFoundException;
import com.example.exception.ParameterMissingException;
import com.example.exception.RecordNotFoundException;
import com.example.neo4j.domain.UserEntity;
import com.example.neo4j.domain.UserRole;
import com.example.neo4j.repo.UserEntityRepo;
import com.example.neo4j.repo.UserRoleRepository;
import com.example.object.request.LoginRequestObj;
import com.example.object.request.UserEntityRequest;
import com.example.object.request.user.UserChangePasswordRequest;
import com.example.object.response.BaseResponseObj;
import com.example.object.response.UserLoginSession;
import com.example.service.SystemMessageService;
import com.example.service.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.utils.Constants.MSG_DUPLICATE_RECORD;


/**
 * Created by LKW on 2017/8/6.
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepo userEntityRepo;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private SystemMessageService systemMessageService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    Logger log = Logger.getLogger(this.getClass());

    public BaseResponseObj memberLogin(LoginRequestObj loginRequestObj) throws Exception {

        BaseResponseObj response = new BaseResponseObj();
        Boolean loginSuccess = Boolean.TRUE;
        if (loginFormValidation(loginRequestObj)) {

            List<UserEntity> userEntity = new ArrayList<>();
            userEntity = userEntityRepo.findByName(loginRequestObj.getUserName());

            if (userEntity == null) {
                return new BaseResponseObj(HttpStatus.UNAUTHORIZED, "User not found.");
            }
            if (userEntity.size() > 1) {
//                return new BaseResponseObj(HttpStatus.UNAUTHORIZED, "Duplicate user found.");
                throw new DuplicateRecordFoundException(systemMessageService.getMessage("msg.duplicateRecordFound"));
            }

            UserEntity user = userEntity.get(0);

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(loginRequestObj.getPassword());

            Boolean matches = passwordEncoder.matches(loginRequestObj.getPassword(), user.getPassword());

            if (!matches) {
                loginSuccess = Boolean.FALSE;
            }

        } else {
            //response = new BaseResponseObj(401, "Data missing");
            loginSuccess = Boolean.FALSE;
        }

        if (!loginSuccess) {
            response = new BaseResponseObj(HttpStatus.UNAUTHORIZED, systemMessageService.getMessage("msg.loginFailed"));
            return response;
        }

        // Normal Case
        return response;
    }

    public BaseResponseObj registration(UserEntityRequest request) throws Exception {

        BaseResponseObj response = new BaseResponseObj();


        String logPrefix = "registration(): ";

//        if (!formValidation(formData)) {
        if (StringUtils.isBlank(request.getUserName())) {
            return new BaseResponseObj(HttpStatus.UNAUTHORIZED, "Parameter missing");
        }

        // Check Duplicated record
        List<UserEntity> chkDuplicateRecord = this.userEntityRepo.findByName(request.getUserName()); // Check advTitle
        if (chkDuplicateRecord != null && chkDuplicateRecord.size() > 0) {
            log.error(logPrefix + MSG_DUPLICATE_RECORD);
//            return new BaseResponseObj(HttpStatus.UNAUTHORIZED, MSG_DUPLICATE_RECORD);
            throw new DuplicateRecordFoundException(systemMessageService.getMessage("msg.duplicateRecordFound"));
        }

        try {
            UserEntity newUser = new UserEntity();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            if (request.getUserRoleIdList() != null) {
                Iterable<UserRole> userRoleList = userRoleRepository.findAll(request.getUserRoleIdList());
                List<UserRole> roleList = Lists.newArrayList(userRoleList);
                newUser.setHaveRole(roleList);
            }

            newUser.setName(request.getUserName());
            newUser.setEmail(request.getEmail());
            newUser.setKeyExpiryDate(new Date());
            newUser.setPassword(hashedPassword);
            userEntityRepo.save(newUser);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(logPrefix + e.getMessage());
            response = new BaseResponseObj(HttpStatus.UNAUTHORIZED, e.getMessage());
            return response;
        }
        return response;
    }

    private Boolean loginFormValidation(LoginRequestObj formData) {
        //Boolean valid = true;
        if (formData != null) {
            if (StringUtils.isBlank(formData.getUserName()) || StringUtils.isBlank(formData.getPassword())) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public String issueToken(LoginRequestObj requestObj) throws Exception {

        List<UserEntity> userAccount = this.userEntityRepo.findByName(requestObj.getUserName());
        UserEntity user = userAccount.get(0);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String timeStampStr = timestamp.toString();
        String source = requestObj.getUserName() + timeStampStr;

        // Session Expiry time : 3 Days
        Date expiryDate = new Date();
        expiryDate = DateUtils.addDays(expiryDate, 3);

        String newToken = tokenHash(source);
        user.setAccessKey(newToken);
        user.setKeyExpiryDate(expiryDate);
        this.userEntityRepo.save(userAccount);
        return newToken;

    }

    public List<UserEntity> findByName(String username) {
        return userEntityRepo.findByName(username);
    }

    private String tokenHash(String source) throws NoSuchAlgorithmException {

        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(source.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

    }

    private void saveLoginSession(HttpServletRequest request, UserEntity userAccount) {
        UserLoginSession userLoginSession = new UserLoginSession();
        // Session Expiry Time (30 mins)
        Date expiryTime = new Date();
        DateUtils.addMinutes(expiryTime, 30);


        userLoginSession.setUserId(userAccount.getId());
        userLoginSession.setExpiryTime(expiryTime);
        request.getSession().setAttribute("loginSession", userLoginSession);

    }

    public void extendSession(UserEntity userAccount) {
        Date sysDate = new Date();
        Date newExpiryDate = DateUtils.addDays(sysDate, 3);
        userAccount.setKeyExpiryDate(newExpiryDate);
        this.userEntityRepo.save(userAccount);
    }

    public BaseResponseObj memberLogout() {

        String logPrefix = "memberLogout(): ";
        BaseResponseObj response = new BaseResponseObj();
        String currentUser = httpServletRequest.getHeader("username");
        if (StringUtils.isBlank(currentUser)) {
            log.error(logPrefix + "User not Found");
            response = new BaseResponseObj(HttpStatus.BAD_REQUEST, "User not Found");
            return response;
        }

        List<UserEntity> userEntity = this.userEntityRepo.findByName(currentUser);

        try {

            // Clear user access token
            if (userEntity != null && userEntity.size() == 1) {
                UserEntity user = userEntity.get(0);
                user.setKeyExpiryDate(new Date());
                user.setAccessKey("");
                userEntityRepo.save(userEntity);
            } else {
                log.error(logPrefix + "User not Found");
                response = new BaseResponseObj(HttpStatus.BAD_REQUEST, "Unexpected Result - record is not unique");
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(logPrefix + e.getMessage());
            response = new BaseResponseObj(HttpStatus.UNAUTHORIZED, e.getMessage());
            return response;
        }

        return response;

    }

    public void changePassword(UserChangePasswordRequest request) {

        String currentUser = httpServletRequest.getHeader("username");
        if (StringUtils.isBlank(currentUser)) {
            throw new ParameterMissingException("User Name");
        }
        if (StringUtils.isBlank(request.getOldPassword())) {
            throw new ParameterMissingException("Old-Password");
        }
        if (StringUtils.isBlank(request.getPassword())) {
            throw new ParameterMissingException("New-Password");
        }
        if (StringUtils.isBlank(request.getConfirmPassword())) {
            throw new ParameterMissingException("Confirm-Password");
        }

        List<UserEntity> source = userEntityRepo.findByName(currentUser);

        if (source != null && source.size() > 0) {

            if (source.size() > 1) {
                throw new DuplicateRecordFoundException();
            }

            UserEntity userEntity = source.get(0);

            // Check Password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Boolean matches = passwordEncoder.matches(request.getOldPassword(), userEntity.getPassword());

            if (!matches) {
                throw new BaseException(HttpStatus.BAD_REQUEST, "Incorrect Original Password");
            }

            // Normal Case
            if (request.getPassword().equals(request.getConfirmPassword())) {
                String newPassword = passwordHashing(request.getConfirmPassword());
                userEntity.setPassword(newPassword);
                userEntityRepo.save(userEntity);
            } else {
                throw new BaseException(HttpStatus.BAD_REQUEST, "Incorrect Confirmation Password");
            }


        } else {
            throw new RecordNotFoundException("User not found");
        }


    }

    public String passwordHashing(String source) {

        if (StringUtils.isBlank(source)) {
            log.error("passwordHashing(): Parameter missing.");
            throw new ParameterMissingException("Source String missing");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(source);

        return hashedPassword;

    }


}
