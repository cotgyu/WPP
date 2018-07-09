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
	//��ť��Ƽ ��ȣȭ salt���� ���������� �ڵ� �����ϴ� ������� �۵�...?
	@Autowired
	private PasswordEncoder passwordEncoder;
	/* 
	 �ٸ���� ��ȣȭ
	@Autowired
	private SaltSource saltSource;
	 */
	
	//�α��� ����ó��(���̵� , ��й�ȣ Ȯ��)
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user;
		Collection<? extends GrantedAuthority> authorities;
		try {
			user = userService.loadUserByUsername(username);
			if(user ==null){
				//���̵� ��ã���� �޽���
				throw new BadCredentialsException("���̵� �����ϴ�.");
			}
			//��ȣȭ�� �ٸ����??
			//String hashedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
			String hashedPassword = password;
			logger.info(
					"username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
			logger.info("username : " + user.getUsername() + " / password : " + user.getPassword());
			
			//matchs�� ���ϱ�, ��й�ȣ Ʋ������ �޼���
			if (!passwordEncoder.matches(hashedPassword ,user.getPassword()))
				throw new BadCredentialsException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
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
