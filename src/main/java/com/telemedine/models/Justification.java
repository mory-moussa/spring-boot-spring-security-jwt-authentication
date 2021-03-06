package com.telemedine.models;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "justifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Justification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private ArrayList<File> documents;
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "medecin_id", referencedColumnName = "id")
	    private Medecin medecin;
}
