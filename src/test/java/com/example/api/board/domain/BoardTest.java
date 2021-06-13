package com.example.api.board.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void create() {
        Board board = Board.builder()
                .id(1L)
                .title("title")
                .content("내용입니다.")
                .build();

        assertThat(board.getTitle()).isEqualTo("title");
    }

}