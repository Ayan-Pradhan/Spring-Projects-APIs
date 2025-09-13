package com.springapp.app.configs;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapp.app.dto.ResponseDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TrackerAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ResponseDto responseDto = new ResponseDto(false, "Authentication required: "+ authException.getMessage());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(mapper.writeValueAsString(responseDto));
		response.getWriter().flush();
	}

}
