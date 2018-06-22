package com.mpm.sso.security.core.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MpmUser extends User {
	private String namecn;

	public MpmUser(String username, String password, String namecn,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, namecn, true, true, true, true, authorities);
	}

	public MpmUser(String username, String password, String namecn, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		setNamecn(namecn);
	}

	public String getNamecn() {
		return namecn;
	}

	public void setNamecn(String namecn) {
		this.namecn = namecn;
	}

}
