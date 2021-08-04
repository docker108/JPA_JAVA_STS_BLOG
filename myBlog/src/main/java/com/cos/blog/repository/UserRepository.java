  package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


// DAO
// 자동으로 bean등록이 된다.
//@Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
	// User -> 유저테이블, Integer-> 유저테이블의 primary key
	
	// JPA Naming 전략
	// SELECT * FROM user WHERE username = ? AND password = ?; 이거랑 같다.
	User findByUsernameAndPassword(String username, String password);
	
//	// 위 코드랑 아래 코드랑 같다.
//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
	
	
}