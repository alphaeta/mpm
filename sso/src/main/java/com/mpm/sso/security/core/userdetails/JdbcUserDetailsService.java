package com.mpm.sso.security.core.userdetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDetailsService implements UserDetailsService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String LOAD_USER_BY_USERID_SQL = "select employee_id,name,password,company_id,companyCode from sys_user t where t.login_name=? and companyCode=?";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String[] arr = username.split(":");
		String cmpcode = arr[0];
		String loginname = arr[1];
		List<Map<String, Object>> list = jdbcTemplate.queryForList(LOAD_USER_BY_USERID_SQL, loginname, cmpcode);
		if (list == null || list.isEmpty()) {
			throw new UsernameNotFoundException("not found username:" + username);
		}
		Map<String, Object> map = list.get(0);
		String employeeId = map.get("EMPLOYEE_ID").toString();
		String password = map.get("PASSWORD").toString();
		String namecn = map.get("NAME").toString();
		String companyCode = map.get("COMPANYCODE").toString();
		String companyId = map.get("COMPANY_ID").toString();

		List<GrantedAuthority> authorities = new ArrayList<>();

		MpmUser securityUser = new MpmUser(username, password, namecn, authorities);
		securityUser.setLoginName(loginname);
		securityUser.setEmployeeId(employeeId);
		securityUser.setCompanyCode(companyCode);
		securityUser.setCompanyId(companyId);
		return securityUser;
	}

}
