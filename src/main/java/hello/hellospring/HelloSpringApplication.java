package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		
		// 처음 The type java.lang.Class cannot be resolved. 오류
		// 이클립스가 설치된 JDK를 인식하지 못하는 게 원인 --> Java Build Path 라이브러리에서 JRE System Library --> 설치된 jre를 추가하면 됨
		// 아니 그냥 자바11 새로 깔고 JRE System Library를 jdk 11로 연결하고 project facets에서 자바 버전도 11로 바꿈.
		// 원래 설치된 자바 버전을 바꾸려고 환경변수 설정 바꿨는데도 cmd로 확인하면 8만 떠서 포기함.
		
		// 처음 톰캣 포트가 8080이었는데 이미 쓰고 있는 거라고 에러나서 application.properties에 server.port=8000 추가해서 포트설정 바꿈.
		
	}

}
