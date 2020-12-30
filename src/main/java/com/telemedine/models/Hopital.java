package com.telemedine.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Entity
@Table(name = "hopitals")
@Data
public class Hopital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String name;
	
	/* @OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "adresse")
	    private Adresse adresse;*/
}
