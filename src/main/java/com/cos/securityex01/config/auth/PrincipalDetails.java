package com.cos.securityex01.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.securityex01.model.User;

import lombok.Data;

// 세션에 담길 객체
// Authentication 객체에 저장할 수 있는 유일한 타입. User가 아님에 주의!!
@Data
public class PrincipalDetails implements UserDetails {

	private User user;
	
	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 아래 여러 메서드들 false이면 로그인 불가
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;	// 원래는 동적으로 최종접속 시간을 보고 결정해야
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));	// "ROLE_USER", "ROLE_ADMIN"
		
		return authorities;
		
		// 이 메서드 구현에 참조한 사이트 : https://www.baeldung.com/spring-security-granted-authority-vs-role
	}

}
