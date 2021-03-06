package com.loncoto.firstBoot.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class SpaceShip {
	
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private double weight;
	private double length;

}
