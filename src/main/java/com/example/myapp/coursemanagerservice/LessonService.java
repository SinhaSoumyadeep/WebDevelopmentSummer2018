package com.example.myapp.coursemanagerservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.myapp.coursemanager.Lesson;
import com.example.myapp.coursemanager.Module;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	ModuleRepository moduleRepository;
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}")
	public List<Lesson> findAllLessonForModule(@PathVariable("moduleId") int moduleId) {
		System.out.println("inside lesson plan  "+moduleId);
		Optional<Module> data = moduleRepository.findById(moduleId);
		System.out.println(data.get().getLessons());
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;		
	}
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}")
	public Lesson createLesson(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int moduleId, @RequestBody Lesson newlesson) {
		
		System.out.println("inside create Lesson");
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newlesson.setModule(module);
			return lessonRepository.save(newlesson);
		}
		return null;		
	}
	
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int lessonId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN LESSON************************");
		lessonRepository.deleteById(lessonId);
	}
	

}
