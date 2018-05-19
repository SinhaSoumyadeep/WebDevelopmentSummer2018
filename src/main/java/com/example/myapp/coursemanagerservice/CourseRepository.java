package com.example.myapp.coursemanagerservice;
import org.springframework.data.repository.CrudRepository;

import com.example.myapp.coursemanager.Course;


public interface CourseRepository extends CrudRepository<Course, Integer> { }
