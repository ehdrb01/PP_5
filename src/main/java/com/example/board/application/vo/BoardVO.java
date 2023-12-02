package com.example.board.application.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardVO {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regdate;
    private String category;
    private int cnt;
}
