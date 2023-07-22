package com.jongbot.web.first.animal.AnimalService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jongbot.web.first.animal.vo.DogVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService{
	
	
	public List<DogVo> getDogList(){
		List<DogVo> animalList = new ArrayList<DogVo>();
		
		DogVo dogVo = new DogVo();
		dogVo.setName("mocha");
		dogVo.setDogId(1);
		//dogVo.getOwnerId().add(dogVo.getOwnerId().size()+1);
		//List<Integer> ownerList =dogVo.getOwnerId(); 
		//dogVo.setOwnerId(ownerList);
		
		DogVo dogVo2 = new DogVo();
		dogVo2.setName("ari");
		dogVo2.setDogId(2);
		//dogVo2.setOwnerId(ownerList);
		
		//dogVo.			
		animalList.add(dogVo);
		animalList.add(dogVo2);
	
		return animalList;
	}

}
