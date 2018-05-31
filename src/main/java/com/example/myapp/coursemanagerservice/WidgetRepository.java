package com.example.myapp.coursemanagerservice;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.coursemanager.Widget;



public interface WidgetRepository extends CrudRepository<Widget, Integer>{
	
	@Query("SELECT wid FROM Widget wid WHERE wid.topicId = :topicId ORDER BY wid.widgetOrder")
	List<Widget> findAllWidgetByTopic(@Param("topicId")String topicId);
	
}
