package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA 유사 ORM
// ORM -> JAVA(다른 언어 포함) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MYSQL에 테이블이 생성이 된다.
//@DynamicInsert // insert할 때 null인 필드 제외
public class User {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique = true) // unique 중복X
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 -> 해쉬(비밀번호 암호화)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'") // DB는 RoleType이라는 게 없다.
//	private String role; // Enum을 쓰는게 좋다. // admin, user, manager
	
	// @ColumnDefault("user")
	// DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum 사용. // USER, ADMIN 이 두 개 중에 하나만 사용가능.
	
	private String oauth; // kakao, google
	
	// 내가 직접 시간을 넣으려면 Timestamp.valueOf(LocalDateTime.now())
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
