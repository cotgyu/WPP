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
		//ȸ�����Խ� ȸ���� �Է��� ��й�ȣ ��ȣȭ�Ͽ� db�� ���� 
		String password = user.getPassword();
		password = passwordEncoder.encode(password);
		
		//��ȣȭ�� ��й�ȣ�� ������ ����
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
	
	//��ť��Ƽ ���̵�Ȯ�� ��  �α��μ����� ó��,���� �ο� 
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = new User();
		user = userDao.findname(username);
		//���̵� ������....
		if(user ==null){
			return user;
		}

		user.setUserId(user.getUsername());
		user.setPassword(user.getPassword());
		Role role = new Role();
		
		//���̵� ���������� ���� �ο�.....�ʹ�����ѵ�...
		if(username.equals("������")){
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
	
	//�̸��Ϸ� ���̵�ã�� 
	public String finduserId(String user_email, String user_name) {
		return userDao.finduserId(user_email,user_name);
	}

	public String finduseremail(String userid) {
		return userDao.finduseremail(userid);
	}
	
	//�޼��� �κ� 
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
