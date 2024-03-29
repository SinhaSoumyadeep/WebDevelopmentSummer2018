package com.example.myapp.coursemanager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topics {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	@OneToMany(mappedBy="topics", orphanRemoval=true)
	private List<Widget> widget;
	@OneToMany(mappedBy="topics", orphanRemoval=true)
	private List<QuestionWidget> qWidget;
	

	
	public List<QuestionWidget> getqWidget() {
		return qWidget;
	}
	public void setqWidget(List<QuestionWidget> qWidget) {
		this.qWidget = qWidget;
	}
	public List<Widget> getWidget() {
		return widget;
	}
	public void setWidget(List<Widget> widget) {
		this.widget = widget;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

}
