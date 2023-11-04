package com.study.board;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
}

/*

org.springframework.beans.factory.BeanDefinitionStoreException:
Failed to parse configuration class [com.study.board.BoardApplication];
nested exception is org.springframework.context.annotation.ConflictingBeanDefinitionException:
Annotation-specified bean name 'boardController' for bean class [com.study.board.ga.BoardController] conflicts with existing,
non-compatible bean definition of same name and class [com.study.board.controller.BoardController]

 */


