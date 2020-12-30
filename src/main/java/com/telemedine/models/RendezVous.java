package com.telemedine.models;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class RendezVous {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @NotNull
    private String typeConsultation;
	@NotNull
	@Size(max = 10)
	private Double price;
	@NotNull
	private Date dateDebut;
	@NotNull
	private Date dateFin;
	@NotNull
	private boolean isvalid;

}
