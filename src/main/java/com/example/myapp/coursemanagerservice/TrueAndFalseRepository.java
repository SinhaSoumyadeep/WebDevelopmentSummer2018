package com.example.myapp.coursemanagerservice;

import org.springframework.data.repository.CrudRepository;
import com.example.myapp.coursemanager.TrueAndFalse;


public interface TrueAndFalseRepository extends CrudRepository<TrueAndFalse, Integer> {
	
}