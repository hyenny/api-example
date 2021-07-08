package com.example.api;

import com.example.api.board.domain.Board;
import com.example.api.board.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(BoardRepository boardRepository) {
        return (args) -> {
            for (int i = 0; i < 20; i++) {
                int index = i+1;
                boardRepository.save(new Board("title" + index, "content" + index, LocalDateTime.now(), LocalDateTime.now()));
            }
        };
    }

}
