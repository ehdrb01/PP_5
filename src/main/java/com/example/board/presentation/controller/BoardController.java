package com.example.board.presentation.controller;


import com.example.board.application.service.BoardService;
import com.example.board.application.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @GetMapping(value = "/")
    public String test() {

        return "redirect:board";
    }

    @GetMapping("/board")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.getBoardList());


        return "posts";
    }

    @GetMapping("/view/{id}")
    public String boardView(@PathVariable int id, Model model) {
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("u", boardVO);

        return "view";
    }

    @GetMapping("/add/form")
    public String addPostForm() {
        return "addpostform";
    }

    @PostMapping("/add")
    public String addPost(BoardVO vo) {
        boardService.insertBoard(vo);

        return "redirect:board";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable int id) {
        boardService.deleteBoard(id);

        return "redirect:../board";
    }

    @GetMapping("/edit/form/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("u", boardVO);


        return "editform";
    }

    @PostMapping("/edit")
    public String updatePost(BoardVO boardVO) {
        boardService.updateBoard(boardVO);

        return "redirect:board";

    }
}
