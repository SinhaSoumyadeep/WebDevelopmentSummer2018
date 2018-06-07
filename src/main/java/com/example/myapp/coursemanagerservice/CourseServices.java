package com.example.myapp.coursemanagerservice;





import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.coursemanager.Course;
import com.example.myapp.utilities.ConvertIteratableToList;




@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class CourseServices {
	
	@Autowired
	CourseRepository courseRepository;	
	@RequestMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		System.out.println("inside find all courses");
		
		Iterable<Course> crsLst = courseRepository.findAll();
		List<Course> crlst = (List<Course>)ConvertIteratableToList.iterableToCollection(crsLst);
		
		Collections.sort(crlst, new Comparator<Course>() {
			  public int compare(Course o1, Course o2) {
			      if (o1.getCreated() == null || o2.getCreated() == null)
			        return 0;
			      return o2.getCreated().compareTo(o1.getCreated());
			  }
			});
		return crlst; 
	}
	
	@RequestMapping("/api/addcourse")
	public Course createCourse(@RequestBody Course course) {
		
		course.setOwner("Me");
		Date date = new Date();
		course.setCreated(date);
		
		Course crs = courseRepository.save(course);
		return crs;
	}
	
	@RequestMapping("/api/delcourse")
	public String deleteCourse(@RequestBody Course course) {
		
		courseRepository.delete(course);
		
		return "deleted";
		
	}
	
	@RequestMapping("/api/search")
	public List<Course> searchCourse(@RequestBody Course course) {
	
		List<Course> crseFilter = courseRepository.findByTitle(course.getTitle());
		return crseFilter;

	}
	


}
