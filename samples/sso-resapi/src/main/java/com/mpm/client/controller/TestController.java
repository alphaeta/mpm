package com.mpm.client.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "TestController", description = "测试服务相关")
@RestController
@RequestMapping(value = "/api/test")
public class TestController {
	@ApiOperation(value = "POST服务", notes = "POST服务")
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String post(@RequestParam(value = "id") Integer id, Principal principal) {
		System.out.println(principal);
		return "POST_ID:" + id;
	}

	@ApiOperation(value = "GET服务", notes = "GET服务")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(@RequestParam(value = "id") Integer id, Principal principal) {
		System.out.println(principal);
		return "GET_ID:" + id;
	}

	@ApiOperation(value = "PUT服务", notes = "PUT服务")
	@RequestMapping(value = "/put", method = RequestMethod.PUT)
	public String put(@RequestParam(value = "id") Integer id, Principal principal) {
		System.out.println(principal);
		return "PUT_ID:" + id;
	}

	@ApiOperation(value = "DELETE服务", notes = "DELETE服务")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String delete(@RequestParam(value = "id") Integer id, Principal principal) {
		System.out.println(principal);
		return "DELETE_ID:" + id;
	}

}
