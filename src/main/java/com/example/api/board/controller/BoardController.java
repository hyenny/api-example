package com.example.api.board.controller;

import com.example.api.board.domain.Board;
import com.example.api.board.domain.BoardRequest;
import com.example.api.board.domain.BoardResponse;
import com.example.api.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {

    private final BoardService boardService;
    private ModelMapper modelMapper;

    @PostMapping("/new")
    public void create(@RequestBody BoardRequest boardRequest) {
        boardService.save(boardRequest.toEntity());
    }

    @PutMapping("/{boardId}")
    public void update(@PathVariable("boardId") Long boardId, @RequestBody BoardRequest boardRequest) {
        Board findBoard = boardService.findBoard(boardId);
        findBoard.update(boardRequest.toEntity());
        boardService.save(findBoard);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> read(@PathVariable("boardId") Long boardId) {
        return new ResponseEntity<>(convertToDto(boardService.findBoard(boardId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> list() {
        List<Board> boards = boardService.findBoards();
        return new ResponseEntity<>(boards.stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }

    private BoardResponse convertToDto(Board board) {
        return modelMapper.map(board, BoardResponse.class);
    }
}
