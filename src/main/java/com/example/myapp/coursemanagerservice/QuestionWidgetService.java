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

import com.example.myapp.coursemanager.EssayQuestion;
import com.example.myapp.coursemanager.Exam;
import com.example.myapp.coursemanager.FillInTheBlanks;
import com.example.myapp.coursemanager.MultipleChoiceQuestion;
import com.example.myapp.coursemanager.Question;
import com.example.myapp.coursemanager.QuestionWidget;
import com.example.myapp.coursemanager.Topics;
import com.example.myapp.coursemanager.TrueAndFalse;




@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionWidgetService {
	
	@Autowired
	QuestionWidgetRepository questionWidgetRepository;
	
	@Autowired
	ExamRepository examRepository;

	@Autowired
	TopicsRepository topicsRepository;
	
	@Autowired
	MultipleChoicesQuestionRepository mutiRepo;
	
	@Autowired
	EssayQuestionRepository essayRepo;
	
	@Autowired
	TrueAndFalseRepository tnfRepo;
	
	@Autowired
	FillInTheBlanksRepository fbRepo;
	
	

	@GetMapping("/api/multi/{examId}")
	public List<Question> findMultiQuestionById(@PathVariable("examId") Integer examId) {
		
		System.out.println("inside MULTIPLE CHOICE........");
		Optional<Exam> exam = examRepository.findById(examId);
		System.out.println(exam.get().getTitle());
		List<Question> optional = mutiRepo.findAllQuestionByExam(exam.get());
		return optional;
		
	}
	
	
	@PostMapping("/api/qwidget/save/question/{examId}")
	public void saveMultipleChoice(@PathVariable("examId") Integer examId,@RequestBody MultipleChoiceQuestion mcq)
	{
		
		System.out.println("savetopic exam ****************XXXXXXXXXXXXXX>>>>"+mcq.getId()+" "+mcq.getPoints()+" "+examId);
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			
			Exam exam = data.get();
			mcq.setExam(exam);
			mcq.seteId(examId.toString());
			
			mutiRepo.save(mcq);
			
		}
		
		
	}
	
	
	@PostMapping("/api/qwidget/save/essayquestion/{examId}")
	public void saveEssayQuestion(@PathVariable("examId") Integer examId,@RequestBody EssayQuestion eq)
	{
		
		System.out.println("savetopic ESSAYAYAYAY ****************XXXXXXXXXXXXXX>>>>"+eq.getId()+" "+eq.getPoints()+" "+examId);
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			
			Exam exam = data.get();
			eq.setExam(exam);
			eq.seteId(examId.toString());
			
			essayRepo.save(eq);
			
		}
		
		
	}
	
	
	@PostMapping("/api/qwidget/save/tfquestion/{examId}")
	public void saveTrueAndFalse(@PathVariable("examId") Integer examId,@RequestBody TrueAndFalse tf)
	{
		
		System.out.println("savetopic TRUEANDFALSE ****************XXXXXXXXXXXXXX>>>>"+tf.getId()+" "+tf.getIsTrue()+" "+examId);
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			
			Exam exam = data.get();
			tf.setExam(exam);
			tf.seteId(examId.toString());
			
			tnfRepo.save(tf);
			
		}
		
		
	}
	
	@PostMapping("/api/qwidget/save/fbquestion/{examId}")
	public void saveFillBlanks(@PathVariable("examId") Integer examId,@RequestBody FillInTheBlanks fb)
	{
		
		System.out.println("savetopic TRUEANDFALSE ****************XXXXXXXXXXXXXX>>>>"+fb.getId()+" "+fb.getBlanks()+" "+examId);
		Optional<Exam> data = examRepository.findById(examId);
		
		if(data.isPresent()) {
			
			Exam exam = data.get();
			fb.setExam(exam);
			fb.seteId(examId.toString());
			
			fbRepo.save(fb);
			
		}
		
		
	}
	
	
	
	@DeleteMapping("/api/qwidget/delete/MC/{questionId}")
	public void deleteMultipleChoice(@PathVariable("questionId") int questionId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN Assignment************************");
		mutiRepo.deleteById(questionId);
	}
	
	@DeleteMapping("/api/qwidget/delete/ES/{questionId}")
	public void deleteEssayQuestion(@PathVariable("questionId") int questionId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN Assignment************************");
		essayRepo.deleteById(questionId);
	}
	
	@DeleteMapping("/api/qwidget/delete/TF/{questionId}")
	public void deleteTrueAndFalseQuestion(@PathVariable("questionId") int questionId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN Assignment************************");
		tnfRepo.deleteById(questionId);
	}
	
	@DeleteMapping("/api/qwidget/delete/FB/{questionId}")
	public void deleteFillInTheBlanks(@PathVariable("questionId") int questionId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN Assignment************************");
		fbRepo.deleteById(questionId);
	}
	
	
	
	
	
	
	
	@GetMapping("/api/qwidget/assign/{topicId}")
	public List<QuestionWidget> findAllAssignmentWidget(@PathVariable("topicId") Integer topicId) {
		System.out.println("==========>>"+topicId);
			return questionWidgetRepository.findAllAssignmentByTopic(topicId.toString());
	}
	
	@GetMapping("/api/qwidget/exam/{topicId}")
	public List<Exam> findAllExamWidget(@PathVariable("topicId") Integer topicId) {
		System.out.println("********************>>"+topicId);
			return examRepository.findAllExamByTopic(topicId.toString());
	}
	
	
	@PostMapping("/api/qwidget/save/exam/{topicId}")
	public void saveAllExamWidget(@PathVariable("topicId") Integer topicId,@RequestBody Exam widgets)
	{
		
		System.out.println("savetopic exam ****************XXXXXXXXXXXXXX>>>>"+widgets.getId()+" "+widgets.getPoints()+" "+topicId);
		Optional<Topics> data = topicsRepository.findById(topicId);
		
		if(data.isPresent()) {
			
			Topics topics = data.get();
			widgets.setTopics(topics);
			widgets.setTopicId(topicId.toString());
			
			examRepository.save(widgets);
			
		}
		
		
	}
	
	
	
	@PostMapping("/api/qwidget/save/{topicId}")
	public void saveAllQuestionWidget(@PathVariable("topicId") Integer topicId,@RequestBody QuestionWidget widgets)
	{
		
		System.out.println("savetopic dsfsjdhkfbskadj............>>>>"+widgets.getId()+" "+widgets.getPoints()+" "+topicId);
		Optional<Topics> data = topicsRepository.findById(topicId);
		System.out.println("**************************before saved 1**********************************"+data);
		if(data.isPresent()) {
			System.out.println("**************************before saved 2**********************************");
			Topics topics = data.get();
			widgets.setTopics(topics);
			widgets.setTopicId(topicId.toString());
			
			questionWidgetRepository.save(widgets);
			System.out.println("**************************successfully saved**********************************");
		}
		
		
	}
	
	@DeleteMapping("/api/qwidget/delete/{widgetId}")
	public void deleteModule(@PathVariable("widgetId") int widgetId)
	{
		System.out.println("************************INSIDE DELETE METHOD IN Assignment************************");
		questionWidgetRepository.deleteById(widgetId);
	}
	
	
	

}
