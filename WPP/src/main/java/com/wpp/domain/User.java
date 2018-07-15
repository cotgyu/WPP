package com.wpp.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wpp.security.Role;

public class User implements UserDetails { 
	@NotEmpty @Size(min=4, max=12)
	private String userid;			//회원아이디
	@NotEmpty @Size(min=4, max=12)
	private String password;		//회원 패스워드
	@NotEmpty
	private String name;			//회원 이름
	@Email
	private String email;			//회원 이메일

	private String profileimg;		//회원 프로필사진
	private Date joindate;			//가입날짜
	//이부분 찾아보기 
	private List<Role> authorities;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	public User(){
		
	}
	
	public User(String userid, String password, String name, String email, String profileimg, Date joindate) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.email = email;
		this.profileimg = profileimg;
		this.joindate = joindate;
	}
	
	
	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getProfileimg() {
		return profileimg;
	}

	public void setProfileimg(String profileimg) {
		this.profileimg = profileimg;
	}

	public String getUserId() {
		return userid;
	}
	public void setUserId(String userid) {
		this.userid = userid;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", password=" + password + ", name=" + name + ", email=" + email + ", profileimg="+profileimg+", joindate=" +joindate+" ]";
	}

	//시큐리티 로그인 관련..?	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	//코드가 더러워짐 ㅠㅠ 유저 디테일때문에 유저네임을 설정했음..ㅠㅠ
	@Override
	public String getUsername() {
		return userid;
	}
	public void setUsername(String username) {
		this.userid = username;
	}
	
}
