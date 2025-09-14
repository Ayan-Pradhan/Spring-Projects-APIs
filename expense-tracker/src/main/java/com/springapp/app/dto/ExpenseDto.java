package com.springapp.app.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
	@Size(min = 1, message = "Title must not be blank")
	private String title;
	private String desc;
	
	@Positive(message = "Amount must be greater than 0")
	private Long amount;
	private LocalDateTime timestamp;

}
