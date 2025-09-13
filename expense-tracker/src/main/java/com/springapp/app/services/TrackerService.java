package com.springapp.app.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.app.dto.ExpenseDto;
import com.springapp.app.dto.ResponseDto;
import com.springapp.app.entities.Expense;
import com.springapp.app.entities.Report;
import com.springapp.app.entities.User;
import com.springapp.app.exception.RecordNotFoundException;
import com.springapp.app.repo.ExpenseRepository;
import com.springapp.app.repo.ReportRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TrackerService {
	
	private final ExpenseRepository expenseRepo;
	private final ReportRepository reportRepo;
	private final UserService userService;
	
	private final ModelMapper mapper;
	
	private User getLoggedInUserDetails() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.getUser(username);
	}

	public ExpenseDto getExpense(Integer id) {
		Optional<Expense> expense = expenseRepo.findByIdAndUser(id, getLoggedInUserDetails());
		if(expense.isPresent()) return mapper.map(expense.get(), ExpenseDto.class);
		throw new RecordNotFoundException("Expense details not found with this id and user");
	}
	
	public List<Expense> getExpenses() {
		List<Expense> expenses = expenseRepo.findByUser(getLoggedInUserDetails());
		if(expenses.isEmpty()) throw new RecordNotFoundException("Expense details not found for this user");
		return expenses;
	}
	
	@Transactional
	public ResponseDto addExpense(ExpenseDto expense) {
		Expense mapped = mapper.map(expense, Expense.class);
		mapped.setUser(getLoggedInUserDetails());
		mapped.setTimestamp(LocalDateTime.now());
		expenseRepo.save(mapped);
		return new ResponseDto(true,"Expense Added Successfully");
	}
	
	@Transactional
	public ResponseDto editExpense(ExpenseDto expense,Integer id) {
		return expenseRepo.findByIdAndUser(id, getLoggedInUserDetails()).map(existingExpense->{
			expense.setTimestamp(LocalDateTime.now());
			mapper.map(expense, existingExpense);
			expenseRepo.save(existingExpense);
			return new ResponseDto(true, "Expense Details Updated");
		}) 
		.orElseThrow(() -> new RecordNotFoundException("Expense with id: "+id+" not found for the current user"));
	}
 
	@Transactional
	public ResponseDto deleteExpense(Integer id) {
		int status = expenseRepo.deleteByIdAndUser(id, getLoggedInUserDetails());
		if(status != 0) return new ResponseDto(true, "Expense Deleted Successfully");
		throw new RecordNotFoundException("Expense with id: "+id+" not found for the current user");
	}
	
	@Transactional
	public Report generateReport() {
		List<Expense> expenses = expenseRepo.findByUser(getLoggedInUserDetails());
		
		if(expenses.isEmpty()) {
			throw new RecordNotFoundException("No expense details found for generating report");
		} else {
			long totalExpense = expenses.stream()
					.mapToLong(Expense::getAmount)
					.sum();
			String summary = "Your total expense till now is: "+totalExpense+" Rs.";
			
			Report report = new Report();
			report.setTitle("Report Generated!");
			report.setSummary(summary);
			report.setTimestamp(LocalDate.now());
			
			reportRepo.save(report);

			return report;
		}
		
	}
}
