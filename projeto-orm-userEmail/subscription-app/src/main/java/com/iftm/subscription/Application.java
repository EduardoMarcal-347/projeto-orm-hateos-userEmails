package com.iftm.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		//User user = new User();
		//UserVO userVO = DozerMapper.parseObject(user, UserVO.class);
	}

}
