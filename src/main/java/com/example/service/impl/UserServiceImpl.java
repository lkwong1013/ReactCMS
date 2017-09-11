package com.example.service.impl;

import com.example.exception.DuplicateRecordFoundException;
import com.example.neo4j.domain.UserEntity;
import com.example.neo4j.domain.UserRole;
import com.example.neo4j.repo.UserEntityRepo;
import com.example.neo4j.repo.UserRoleRepository;
import com.example.object.request.LoginRequestObj;
import com.example.object.request.UserEntityRequest;
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

}
