package com.springapp.app.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="expense_details")
public class Expense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@Column(name="description")
	private String desc;
	private Long amount;
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public Expense(int id, String title, String desc, long amount, User user) {
		super();
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.amount = amount;
		this.user = user;
	}

}
