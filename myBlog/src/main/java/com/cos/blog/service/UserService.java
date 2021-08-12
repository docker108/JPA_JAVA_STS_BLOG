package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. loC를 해준다.

// 서비스가 필요한이유.
// 1. 트랜잭션 관리.
// -> 트랜잭션이란 일이 처리되기 위한 가장 작은 단위
// 2. 서비스 의미 때문.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	

	@Transactional(readOnly = true)
	public User user_find(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return user;
	}

	// ex 2에 있는 여러개의 트랜젝션을 하나의 트랜젝션으로 관리
	@Transactional
	public void Regist(User user) {
		// ex 2. 여러개의 트랜잭션
//			userRepository.save(user); 
		String rawPassword = user.getPassword(); // 1234원문
		String encPassword = encoder.encode(rawPassword); // 해쉬화
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void user_update(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());

		// Validate 체크 => oauth 필드에 값이 없으면 수정 가능
//		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
//			String rawPassword = user.getPassword();
//			String encPassword = encoder.encode(rawPassword);
//			persistance.setPassword(encPassword);
//			persistance.setEmail(user.getEmail());
//		}

		// 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 이 자동으로 됩니다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
	}

	// 이 로그인은 사용안함 (시큐리티로 사용하기 때문.)
//	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public User Login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}

}
