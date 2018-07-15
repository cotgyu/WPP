package com.wpp.service;




import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wpp.dao.UserDao;
import com.wpp.domain.Message;
import com.wpp.domain.User;
import com.wpp.security.Role;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Autowired 
	private PasswordEncoder passwordEncoder;

	public void create(User user) {
		//회원가입시 회원이 입력한 비밀번호 암호화하여 db에 저장 
		String password = user.getPassword();
		password = passwordEncoder.encode(password);
		
		//암호화된 비밀번호를 유저에 셋팅
		user.setPassword(password);
		
		userDao.create(user);
	}

	public User findByID(String userId) {
		return userDao.findByID(userId);
	}

	public void imgmodify(User vo) {
		userDao.imgmodify(vo);
		
	}

	public void update(User user) {
		String password = user.getPassword();
		password = passwordEncoder.encode(password);
		user.setPassword(password);
		
		userDao.update(user);	
	}

	public int checkId(User vo) {
		return userDao.checkId(vo);
	}

	public String findprofile(String userId) {	
		return userDao.findprofile(userId);
	}
	
	//시큐리티 아이디확인 및  로그인성공시 처리,권한 부여 
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = new User();
		user = userDao.findname(username);
		//아이디가 없을때....
		if(user ==null){
			return user;
		}

		user.setUserId(user.getUsername());
		user.setPassword(user.getPassword());
		Role role = new Role();
		
		//아이디가 관리자일지 어드민 부여.....너무허술한듯...
		if(username.equals("관리자")){
			role.setName("ROLE_ADMIN");
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			user.setAuthorities(roles);		
			return user;
		}
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setAuthorities(roles);
		
		return user;
	}
	
	//이메일로 아이디찾기 
	public String finduserId(String user_email, String user_name) {
		return userDao.finduserId(user_email,user_name);
	}

	public String finduseremail(String userid) {
		return userDao.finduseremail(userid);
	}
	
	//메세지 부분 
	public void sendmessage(Message message) {
		userDao.sendmessage(message);
	}

	public List<Message> viewmessage(String userid) {
		return userDao.viewmessage(userid);
		
	}

	public void unregister(String userid) {
		userDao.unregister(userid);
	}

	public List<Message> viewsendmessage(String userid) {
		return userDao.veiwsendmessage(userid);
	}
	
	
}