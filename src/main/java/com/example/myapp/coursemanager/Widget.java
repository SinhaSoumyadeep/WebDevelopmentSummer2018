package com.example.myapp.coursemanager;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String widgetType;
	private String topicId;
	private String text;
	private String size;
	private String linktext;
	private String listType;
	private String widget_Name;
	private String widgetOrder;
	@ManyToOne
	@JsonIgnore
	private Topics topics;
	
	
	
	public String getWidget_Name() {
		return widget_Name;
	}
	public void setWidget_Name(String widget_Name) {
		this.widget_Name = widget_Name;
	}
	public String getWidgetOrder() {
		return widgetOrder;
	}
	public void setWidgetOrder(String widgetOrder) {
		this.widgetOrder = widgetOrder;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getLinktext() {
		return linktext;
	}
	public void setLinktext(String linktext) {
		this.linktext = linktext;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public Topics getTopics() {
		return topics;
	}
	public void setTopics(Topics topics) {
		this.topics = topics;
	}
	
	
	

	
	
}
