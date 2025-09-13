package com.springapp.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	private String email;
	private String name;
	private int age;
	private String occupation;
	private String city;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Expense> expenses;

}
