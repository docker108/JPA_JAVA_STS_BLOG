package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// RestController를 문자열 자체를 리턴해준다면, Controller 해당 경로에 있는 파일을 리턴해준다.
@Controller //file을 return한다.
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 리턴명: /homt.html (! 앞에 /를 붙여줘야한다.)
		// -> 풀경로 : /src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.jpg";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views/
		// suffix: .jsp
		// 풀네임: /WEB-INF/views/test.jsp
		
		// /를 안 붙여도 된다.
		return "/test"; 
	}
	
	
}
/*
 * 스프링부트는 application.yml 파일에 설정하면 된다.

@RestController는 문자 그 자체를 return 하는 반면,
@Controller는 해당 경로 이하에 있는 파일을 리턴하므로 슬러시(/)를 붙여야 한다.

스프링부트는 기본적으로 jsp 지원을 하지 않는다.
jsp파일을 리턴해줘도 정상적으로 동작하지 않는다.
스프링부트에서 jsp를 인식하기 위한 사전설정은 다음과 같다.

    1. pom.xml에 JSP 템플릿 엔진 dependency 추가
    		<!-- JSP 템플릿 엔진 -->
		<dependency>
		    <groupId>org.apache.tomcat.embed</groupId>
		    <artifactId>tomcat-embed-jasper</artifactId>
		</dependency>

    2. application.yml에 다음 코드를 추가한다.
        spring:
          mvc:
            view:
              prefix: /WEB-INF/views/
              suffix: .jsp

       src/main/webapp/WEB-INF/views 디렉토리를 직접 만들어야 한다.
       이때 뒤에 확장자명이 이미 설정파일에 추가되어 있으므로 파일이름만을 return한다. EX) return "temp";

JSP파일은 정적 파일이 아니므로 웹서버인 아파치가 처리하지 못한다. 톰캣이 대신 컴파일해서 웹브라우저에게 전달한다.
 * */
