package com.wpp.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.SaltSource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wpp.domain.User;
import com.wpp.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	UserService userService;
	//시큐리티 암호화 salt값을 내부적으로 자동 생성하는 방식으로 작동...?
	@Autowired
	private PasswordEncoder passwordEncoder;
	/* 
	 다른방식 암호화
	@Autowired
	private SaltSource saltSource;
	 */
	
	//로그인 인증처리(아이디 , 비밀번호 확인)
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user;
		Collection<? extends GrantedAuthority> authorities;
		try {
			user = userService.loadUserByUsername(username);
			if(user ==null){
				//아이디 못찾을때 메시지
				throw new BadCredentialsException("아이디가 없습니다.");
			}
			//암호화의 다른방식??
			//String hashedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
			String hashedPassword = password;
			logger.info(
					"username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
			logger.info("username : " + user.getUsername() + " / password : " + user.getPassword());
			
			//matchs로 비교하기, 비밀번호 틀렸을때 메세지
			if (!passwordEncoder.matches(hashedPassword ,user.getPassword()))
				throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			authorities = user.getAuthorities();
			
		} catch (UsernameNotFoundException e) {
			logger.info(e.toString());
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			logger.info(e.toString());
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			logger.info(e.toString());
			throw new RuntimeException(e.getMessage());
		}
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}