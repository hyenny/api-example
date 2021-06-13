package com.example.api.board.service;

import com.example.api.board.domain.Board;
import com.example.api.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void register(Board board) {
        boardRepository.save(board);
    }

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }
}
