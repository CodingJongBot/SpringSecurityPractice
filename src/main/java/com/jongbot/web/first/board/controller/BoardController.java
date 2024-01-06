package com.jongbot.web.first.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongbot.web.first.board.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private BoardService boardServiceImpl;
	
	@Autowired
	public BoardController(BoardService boardServiceImpl) {
		this.boardServiceImpl = boardServiceImpl;
	}
	
	
	@GetMapping("/list")
	public String getBoardList() {
		return "Sample Test List";
	}
	
	
	

}
