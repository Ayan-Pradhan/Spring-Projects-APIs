package com.springapp.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@Email(message="Invalid email format!!")
	@NotBlank(message="Email can't be empty")
	private String email;
	
	@NotEmpty(message="Name can't be blank")
	private String name;
	
	@Min(value=13,message="You're too young to worry about expenses")
	private int age ;

	private String occupation;
	private String city;
	
	@NotBlank(message="Password can't be empty")
	@Size(min=8,message="Password needs to be atleast 8 characters long")
	private String password;

}
