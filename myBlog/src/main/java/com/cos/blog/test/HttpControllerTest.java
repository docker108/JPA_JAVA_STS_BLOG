package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML파일)
// @Controller

// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "ssar", "12345", "ssar@nate.com");
		Member m2 = new Member();	
		
		System.out.println(TAG+" getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG+" setter : "+m.getId());

		return "lombok test 완료";
	}
	
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {// id=1&username=ssar&password=1234&email=ssar@nate.com // MessageConverter(spring boot)
		return "get 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	// http://localhost:8080/http/post (insert)
	// postman에서 확인할 때 -> body에서 raw-text로 입력시에는 매개변수로 문자열 String으로 받을 수 있다.
	// 그리고 json형식으로 받을 때는 매개변수에 오브젝트 Member m에 자동으로 맵핑에서 값을 넣어준다.
	// 정리 -> mime type :   text/plain, application/json
	// + 이 자동적으로 해주는 일을 누가해주냐 -> MessageConverter(spring boot)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+", "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}













