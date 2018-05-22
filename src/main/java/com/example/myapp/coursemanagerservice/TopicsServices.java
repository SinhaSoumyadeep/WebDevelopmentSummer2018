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
import com.example.myapp.coursemanager.Topics;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicsServices {
	
	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	TopicsRepository topicsRepository;
	
	/*@GetMapping("/api/course/{courseId}/module/{moduleId}")
	public List<Topics> findAllTopicsForLesson(@PathVariable("moduleId") int lessonId) {
		System.out.println("inside lesson plan  "+lessonId);
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		System.out.println(data.get().getTopics());
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();
		}
		return null;		
	}
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}")
	public Topics createTopics(@PathVariable("courseId") int courseId, @PathVariable("moduleId") int lessonId, @RequestBody Topics newTopic) {
		
		System.out.println("inside create Topic");
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newTopic.setLesson(lesson);
			return topicsRepository.save(newTopic);
		}
		return null;		
	}
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteTopic(@PathVariable("id") int topicId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN Topic************************");
		topicsRepository.deleteById(topicId);
	}*/

}
