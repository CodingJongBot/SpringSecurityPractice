package com.jongbot.web.first.animal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jongbot.web.first.animal.AnimalService.AnimalServiceImpl;
import com.jongbot.web.first.animal.vo.DogVo;

@RestController
@RequestMapping("/animal")
public class AnimalController {
	
	private AnimalServiceImpl animalService;
	
	@Autowired
	public AnimalController (AnimalServiceImpl animalServiceImpl){
		this.animalService = animalServiceImpl;
	}

	@GetMapping("/list")
	public List<DogVo> getAnimalList() {
		return animalService.getDogList();
	}
	
	

}
