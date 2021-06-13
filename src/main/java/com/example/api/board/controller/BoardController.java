package com.example.api.board.controller;

import com.example.api.board.domain.Board;
import com.example.api.board.domain.BoardRequest;
import com.example.api.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/new")
    public void create(@RequestBody BoardRequest boardRequest) {
        boardService.register(boardRequest.toEntity());
    }

    @GetMapping
    public ResponseEntity<List<Board>> list() {
        return new ResponseEntity<>(boardService.findBoards(), HttpStatus.OK);
    }
}
