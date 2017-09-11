//package com.example.security;
//
//import com.project.service.UserRoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationTrustResolver;
//import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	@Qualifier("customUserDetailsService")
//    UserDetailsService userDetailsService;
//
//	@Autowired
//	UserRoleService userRoleService;
//
//	@Autowired
//	CustomOnSuccessHandler customOnSuccessHandler;
//
////	@Autowired
////	PersistentTokenRepository tokenRepository;
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//		auth.authenticationProvider(authenticationProvider());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		// Get Role permission mapping
//		//List<UserRole> roleList = this.userRoleService.findAll(); // TODO uncomment after migrate to Neo4j
//
//		http.csrf().disable().authorizeRequests()
//
//				.antMatchers("/lib/**", "/fullLogin", "/loginAction", "/**/api/**" /* "/mobile/login", "/api/auth/**", "/reservations/*"*/).permitAll();
//				//.antMatchers("/dashboard").hasRole("DELTA");
//
//				//.antMatchers("/loginAction").permitAll()
////				.antMatchers("/", "/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
////				.antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')")
////				.antMatchers("/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")
//
////				http.authorizeRequests().antMatchers("/**").access("hasRole('ROLE_SUPERADMIN')"); // Access to all url for super admin
////
////				for (UserRole userRole : roleList) {
////					if (userRole.getRolePermissions() != null && userRole.getRolePermissions().size() > 0) {
////						for (UserPermission userPermission : userRole.getRolePermissions()) {
////							http.authorizeRequests().antMatchers(userPermission.getUrl()).access("hasRole('"+userRole.getRoleName()+"')");
////						}
////
////					}
////
////				}
//
//				http.authorizeRequests().antMatchers("/**").authenticated();  // Always at the bottom
//				http.formLogin().loginPage("/fullLogin").loginProcessingUrl("/loginAction/login").usernameParameter("userName").passwordParameter("password")
//				.successHandler(customOnSuccessHandler)
//				.and().exceptionHandling().accessDeniedPage("/denied"); // Access Denied Page
//
//
//
//
////				.and().rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
////				.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
//
////	@Bean
////	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
////		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
////				"remember-me", userDetailsService, tokenRepository);
////		return tokenBasedservice;
////	}
//
//	@Bean
//	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
//		return new AuthenticationTrustResolverImpl();
//	}
//
//}
