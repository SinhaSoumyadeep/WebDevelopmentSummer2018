package com.example.myapp.coursemanagerservice;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.coursemanager.Course;



public interface CourseRepository extends CrudRepository<Course, Integer> {
	
	
	@Query("SELECT crs FROM Course crs WHERE LOWER(crs.title) LIKE CONCAT(LOWER(:title),'%')")
	public List<Course> findByTitle(@Param("title") String title);
	
	
	
}
