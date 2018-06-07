package com.example.myapp.coursemanagerservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.coursemanager.Exam;

public interface ExamRepository extends CrudRepository<Exam, Integer>{
	
	@Query("SELECT qwid FROM QuestionWidget qwid WHERE qwid.topicId = :topicId AND qwid.widgetType = 'Quiz'")
	List<Exam> findAllExamByTopic(@Param("topicId")String topicId);
	
}
