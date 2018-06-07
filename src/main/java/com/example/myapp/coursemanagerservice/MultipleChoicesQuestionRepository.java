package com.example.myapp.coursemanagerservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.coursemanager.Exam;
import com.example.myapp.coursemanager.MultipleChoiceQuestion;
import com.example.myapp.coursemanager.Question;

public interface MultipleChoicesQuestionRepository
extends CrudRepository<MultipleChoiceQuestion, Integer> {
	
	@Query("SELECT qwid FROM Question qwid WHERE qwid.exam = :exam")
	List<Question> findAllQuestionByExam(@Param("exam")Exam exam);

}