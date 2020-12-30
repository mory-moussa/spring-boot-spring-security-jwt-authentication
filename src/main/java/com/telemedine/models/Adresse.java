package com.telemedine.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Entity
@Table(name = "adresse")
@Data
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 30)
	private String nom;
	 
	@NotBlank
	@Size(max = 30)
	private String ville;
	
	@NotBlank
	@Size(max = 30)
	private String province;
	
	@NotBlank
	@Size(max = 30)
	private int codePostal;
	
	
	private String latitude;

	private String longitude;
	/*
	 @OneToOne(fetch = FetchType.LAZY, optional = false)
	   @JoinColumn(name = "user_id", nullable = false)
	   private User user;
	 @OneToOne(fetch = FetchType.LAZY, optional = false)
	   @JoinColumn(name = "hopital_id", nullable = false)
	   private Hopital hopital;
	*/
	
}
