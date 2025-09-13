package com.springapp.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.springapp.app.entities.Expense;
import com.springapp.app.entities.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	List<Expense> findByUser(User user);
	Optional<Expense> findByIdAndUser(Integer id, User user);
	
	@Modifying
	int deleteByIdAndUser(Integer id, User user);
	
}
