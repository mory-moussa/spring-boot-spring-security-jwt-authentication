package com.telemedine.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "phone"),
			@UniqueConstraint(columnNames = "email") 
		})

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
	@NotBlank
	@Size(max = 100)
	private String photo;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	/* @OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "adresse")
	    private Adresse adresse;*/

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String email,String fullname, String phone ,String photo ,String password) {
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.photo  = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() { return fullname; }

	public void setFullname(String fullname) { this.fullname = fullname; }

	public String getPhone() { return phone; }

	public void setPhone(String phone) { this.phone = phone; }

	public String getPhoto() { return photo; }

	public void setPhoto(String photo) {this.photo = photo; }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
