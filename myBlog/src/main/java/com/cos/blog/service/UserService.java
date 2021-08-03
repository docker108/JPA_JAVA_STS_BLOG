package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC를 해준다.

// 서비스가 필요한이유.
// 1. 트랜잭션 관리.
// 2. 서비스 의미 때문.
@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	// ex 2에 있는 여러개의 트랜젝션을 하나의 트랜젝션으로 관리
	@Transactional
	public void Regist(User user) {
			// ex 2. 여러개의 트랜잭션
//			userRepository.save(user);
			userRepository.save(user);
	}
}
