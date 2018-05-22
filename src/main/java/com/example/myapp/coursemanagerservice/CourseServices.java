package com.example.myapp.coursemanagerservice;




import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.coursemanager.Course;




@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class CourseServices {
	
	@Autowired
	CourseRepository courseRepository;	
	@RequestMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	@RequestMapping("/api/addcourse")
	public Course createCourse(@RequestBody Course course) {
		
		System.out.println("*********inside add method!!!!@***********");
		course.setOwner("Me");
		Date date = new Date();
		course.setCreated(date);
		
		Course crs = courseRepository.save(course);
		return crs;
	}
	
	@RequestMapping("/api/delcourse")
	public String deleteCourse(@RequestBody Course course) {
		
		System.out.println("*********inside delete method!!!!@***********");
	
		courseRepository.delete(course);
		
		return "deleted";
		
	}
	


}
