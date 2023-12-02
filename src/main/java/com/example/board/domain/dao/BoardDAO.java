package com.example.board.domain.dao;


import com.example.board.application.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper rowMapper; // builder랑 똑같은 건가?
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    private final String BOARD_LIST = "select * from BOARD order by seq desc";

    public List<BoardVO> getBoardList() {
        List<BoardVO> list = jdbcTemplate.query(BOARD_LIST, new RowMapper<BoardVO>() {
            @Override
            public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardVO boardVO = new BoardVO();

                boardVO.setSeq(rs.getInt("seq"));
                boardVO.setTitle(rs.getString("title"));
                boardVO.setWriter(rs.getString("writer"));
                boardVO.setContent(rs.getString("content"));
                boardVO.setCategory(rs.getString("category"));
                boardVO.setRegdate(rs.getDate("regdate"));


                return boardVO;
            }
        });

        return list;
    }

    public BoardVO getBoard(int seq) {
        String BOARD_GET = "select * from BOARD  where seq=" + seq;

        return jdbcTemplate.queryForObject(BOARD_GET, new RowMapper<BoardVO>() {
            @Override
            public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardVO vo = new BoardVO();
                vo.setSeq(rs.getInt("seq"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setContent(rs.getString("content"));
                vo.setCategory(rs.getString("category"));
                vo.setRegdate(rs.getDate("regdate"));

                return vo;
            }
        });
    }

    public int insertBoard(BoardVO boardVO) {
        String BOARD_INSERT = "insert into BOARD (title, writer, content) values ("
                + "'" + boardVO.getTitle() +"',"
                + "'" + boardVO.getWriter() +"',"
                + "'" + boardVO.getContent() +"')";


        return jdbcTemplate.update(BOARD_INSERT);
    }

    public int updateBoard(BoardVO boardVO) {
        String sql = "update BOARD set title=?, writer=?, content=? where seq=?";

        return jdbcTemplate.update(sql, boardVO.getTitle(), boardVO.getWriter(), boardVO.getContent(), boardVO.getSeq());
    }

    public int deleteBoard(int seq) {
        System.out.println("2");
        String BOARD_DELETE = "delete from BOARD  where seq= " + seq;
        return jdbcTemplate.update(BOARD_DELETE);
    }


}
