package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
//	// 모든 예외를 받는 메서드
//	@ExceptionHandler(value=Exception.class)
//	public String handleArgumentException(Exception e) {
//		return "<h1>"+e.getMessage()+"</h1>";
//	}
	
}
