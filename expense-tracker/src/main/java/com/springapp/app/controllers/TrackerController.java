package com.springapp.app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.app.dto.ExpenseDto;
import com.springapp.app.dto.ResponseDto;
import com.springapp.app.entities.Report;
import com.springapp.app.services.TrackerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
 
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tracker")
public class TrackerController {
	
	private final TrackerService service;

	@GetMapping("/expenses/{id}")
	public ResponseEntity<ExpenseDto> getExpenseDetails(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getExpense(id));
	}

	@GetMapping("/expenses")
	public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
		return ResponseEntity.ok(service.getExpenses());
	}

	@PostMapping("/expenses")
	public ResponseEntity<ResponseDto> addExpense(@Valid @RequestBody ExpenseDto expense) {
		return ResponseEntity.ok(service.addExpense(expense));
	}

	@PatchMapping("/expenses/{id}")
	public ResponseEntity<ResponseDto> updateExpense(@Valid @RequestBody ExpenseDto expense, @PathVariable Integer id) {
		return ResponseEntity.ok(service.editExpense(expense,id));
	}

	@DeleteMapping("/expenses/{id}")
	public ResponseEntity<ResponseDto> deleteExpense(@PathVariable Integer id) {
		return ResponseEntity.ok(service.deleteExpense(id));
	}

	@GetMapping("/expenses/reports")
	public ResponseEntity<Report> reports() {
		return ResponseEntity.ok(service.generateReport());
	}

}
