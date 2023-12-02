package com.example.board.application.service;

import com.example.board.application.vo.BoardVO;
import com.example.board.domain.dao.BoardDAO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    @Autowired
    public BoardDAO boardDAO;


    public int insertBoard(BoardVO vo) {
        return boardDAO.insertBoard(vo);
    }

    public int deleteBoard(int seq) {
        System.out.println("1");
        return boardDAO.deleteBoard(seq);
    }

    public int updateBoard(BoardVO vo) {
        return boardDAO.updateBoard(vo);
    }

    public BoardVO getBoard(int seq) {
        return boardDAO.getBoard(seq);
    }

    public List<BoardVO> getBoardList() {

        return boardDAO.getBoardList();
    }

}
