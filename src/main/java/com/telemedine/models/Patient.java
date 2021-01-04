package com.telemedine.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(	name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	 @OneToOne(mappedBy = "patient")
	    private User user;
	 @ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "patient_rendezVous", 
					joinColumns = @JoinColumn(name = "patient_id"), 
					inverseJoinColumns = @JoinColumn(name = "rendezVous_id"))
		private Set<RendezVous> rendezVous = new HashSet<>();
}
