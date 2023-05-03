package com.study.board;
import org.python.util.PythonInterpreter;
import org.python.util.PythonInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {

		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("print('Hello from Jython')");

		SpringApplication.run(BoardApplication.class, args);
	}

}
