package com.jongbot.web.first.animal.vo;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
public class DogVo extends Animal{
	
	private int dogId;
	private List<Integer> ownerId;
	private String name;
	private String dogType;
	
	private LocalDate bornDate; //age can be calculated by borndate;			
}