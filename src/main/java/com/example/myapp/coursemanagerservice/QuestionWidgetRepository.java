package com.example.myapp.coursemanagerservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.coursemanager.QuestionWidget;


public interface QuestionWidgetRepository extends CrudRepository<QuestionWidget, Integer>{
	
	@Query("SELECT qwid FROM QuestionWidget qwid WHERE qwid.topicId = :topicId AND qwid.widgetType = 'Assignment'")
	List<QuestionWidget> findAllAssignmentByTopic(@Param("topicId")String topicId);
	
	/*@Query("SELECT qwid FROM QuestionWidget qwid WHERE qwid.topicId = :topicId AND qwid.widgetType = 'Quiz'")
	List<QuestionWidget> findAllExamByTopic(@Param("topicId")String topicId);*/
	
}
