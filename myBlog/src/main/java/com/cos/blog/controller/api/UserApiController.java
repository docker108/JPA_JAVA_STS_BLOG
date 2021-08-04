package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save (@RequestBody User user) { // username, password, email
		System.out.println("UserApiController: save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 됩니다. 
		
		user.setRole(RoleType.USER);
		userService.Regist(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오트젝트를 JSON으로 변환해서 리턴(Jackson)
	}
	
	// 스프링 시큐리티 이용해서 로그인!!
	// 아래방법은 전통적인 로그인 방법임
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("UserApiController: login 호출됨");
		User principal = userService.Login(user); // principal (접근주체)라는 뜻, 많이 쓰임
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	

}
