package com.example.interceptor;

import com.example.exception.NotLoggedInException;
import com.example.object.response.UserLoginSession;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class TestInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log = Logger.getLogger(TestInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean handlerResult = true;
		String logPrefix = "TestInterceptor preHandle(): ";
		//TODO resume after iOS build submitted
		HandlerMethod hm = (HandlerMethod)handler;
		log.info(logPrefix+"triggered");

		HttpSession session = request.getSession();

		UserLoginSession loginSession = (UserLoginSession)session.getAttribute("loginSession");
		if (loginSession != null) {
			log.info(loginSession.getUserId().toString()+" : "+loginSession.getExpiryTime());
		} else {
			log.info("Not logged in");
			throw new NotLoggedInException(HttpStatus.UNAUTHORIZED, "Not Logged In");
		}
		//	        Method method = hm.getMethod();
		//
		//	        if(method.getDeclaringClass().isAnnotationPresent(Controller.class) && method.isAnnotationPresent(HttpAuthTokenValidation.class)){
		//	            handlerResult = this.checkHttpTokenValidation(request);
		//	        }

	return handlerResult;
	}
}