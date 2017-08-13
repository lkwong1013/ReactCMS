package com.example.security;

import com.example.neo4j.domain.UserEntity;
import com.example.neo4j.domain.UserPermission;
import com.example.neo4j.domain.UserRole;
import com.example.neo4j.repo.UserEntityRepo;
import com.example.neo4j.repo.UserRoleRepository;
import com.example.object.AuthorityInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	Logger log = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserEntityRepo userEntityRepo;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private HttpServletRequest request;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


		List<UserEntity> userList = userEntityRepo.findByName(userName);
		UserEntity userAccount = new UserEntity();

		log.info("User : {}");
		if ( userList == null  || userList.size() == 0) {
			log.info("User not found");
			throw new UsernameNotFoundException("Username not found : " + userName);
		} else {
			userAccount = userList.get(0);
		}
			return new User(userAccount.getName(), userAccount.getPassword(),
				 true, true, true, true, getGrantedAuthorities(userAccount));
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		HttpSession session = request.getSession();
		List<UserRole> roleList = user.getHaveRole();
		List<AuthorityInfo> permittedInfo = new ArrayList<AuthorityInfo>();
		// Get Permission / Role by service
		if (roleList != null && roleList.size() > 0) {


			for (UserRole userRole : roleList) { // Get from user-role map
				// Get permission
				List<UserPermission> userPermissions = userRole.getHavePermission();

				if (userPermissions != null && userPermissions.size() > 0) {
					for (UserPermission permission : userPermissions) {
						authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
						AuthorityInfo authorityInfo = new AuthorityInfo();
						authorityInfo.setUrl(permission.getUrl());
						authorityInfo.setPermissionName(permission.getPermissionName());
						permittedInfo.add(authorityInfo);
					}
				}
				authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
			}

			session.setAttribute("authorityInfo", permittedInfo);  // Set Permission Detail info for SecurityInterceptor

		}

		log.info("User logged in : " + user.getName());
		return authorities;
	}
	
}
