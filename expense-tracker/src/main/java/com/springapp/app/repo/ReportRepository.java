package com.springapp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapp.app.entities.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{

}
