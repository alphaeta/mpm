package com.mpm.sso.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public Principal me(Principal principal) {
		return principal;
	}
}
