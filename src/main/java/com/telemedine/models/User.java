package com.telemedine.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.Nullable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "phone"),
			@UniqueConstraint(columnNames = "email") 
		})
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String fullname;
	@NotBlank
	@Size(max = 10)
	private String phone;
	@Nullable
	@Size(max = 100)
	private String photo;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@Nullable
	private String paypalEmail;
	private Instant created;
	private boolean enabled;
	@NotBlank
	@Size(max = 120)
	private String password;
	
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	/* @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
	    private Adresse adrsse;*/
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "patient_id", referencedColumnName = "id")
	    private Patient patient;
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "medecin_id", referencedColumnName = "id")
	    private Medecin medecin;


}
