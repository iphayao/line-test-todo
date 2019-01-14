package com.iphayao.linetest;

import com.iphayao.linetest.todo.Todo;
import com.iphayao.linetest.todo.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@SpringBootApplication
@RestController
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String root() {
		return "Server is running ...";
	}

//	@Bean
//	public CommandLineRunner mockData(TodoRepository repository) {
//		return (args -> {
//			repository.save(
//					Todo.builder()
//							.id(1)
//							.userId("userA")
//							.dateTime(LocalDateTime.now())
//							.build());
//			repository.save(
//					Todo.builder()
//							.id(2)
//							.userId("userA")
//							.dateTime(LocalDateTime.now())
//							.build());
//			repository.save(
//					Todo.builder()
//							.id(3)
//							.userId("userB")
//							.dateTime(LocalDateTime.now())
//							.build());
//			repository.save(
//					Todo.builder()
//							.id(4)
//							.userId("userB")
//							.dateTime(LocalDateTime.now())
//							.build());
//		});
//	}

}

