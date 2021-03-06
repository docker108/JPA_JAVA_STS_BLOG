package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@Data // getter + setter
//@AllArgsConstructor // 생성자
//@NoArgsConstructor // 빈 생성자
//@RequiredArgsConstructor final붙은 애들에 대한 생성자를 만들어준다.

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
//      
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	// (build 패턴 공부하기)
	// Builder를 사용하면 
	// 사용하려는 매개변수에 따른 각각의 생성자를 굳이 만들필요가 없다.
	// 또한 builder()를 사용할 때 순서를 안 지켜도 된다.
	// ex
	// 기존 사용방식 -> Member m = new Member((매개변수))
	// Builder 사용방식 -> Member m = Member.builder().username("ssar").password("1234").build();
	
	


}
