package com.example.api.board.controller;

import com.example.api.board.domain.Board;
import com.example.api.board.domain.BoardRequest;
import com.example.api.board.domain.BoardResponse;
import com.example.api.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/v1/boards")
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
    public ResponseEntity<PagedModel<EntityModel<Board>>> list(@PageableDefault(size = 5, sort="createdDate") Pageable pageable, PagedResourcesAssembler<Board> assembler) {
        Page<Board> boards = boardService.findBoards(pageable);
        return new ResponseEntity<>(assembler.toModel(boards), HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }

    private BoardResponse convertToDto(Board board) {
        return modelMapper.map(board, BoardResponse.class);
    }
}
