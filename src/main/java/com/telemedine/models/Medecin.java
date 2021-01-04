package com.telemedine.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(	name = "medecin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medecin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	 @OneToOne(mappedBy = "medecin")
	    private User user;
	 @OneToOne(mappedBy = "medecin")
	    private Hopital hopital;
	 @OneToOne(mappedBy = "medecin")
	    private Justification justification;
	/* @OneToOne(mappedBy = "medecin")
	    private Adresse adresse;
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "specialite_id", referencedColumnName = "id")
	    private Specialite specialite;*/

}
