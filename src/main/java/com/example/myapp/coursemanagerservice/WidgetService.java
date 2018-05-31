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
import com.example.myapp.coursemanager.Topics;
import com.example.myapp.coursemanager.Widget;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;

	@Autowired
	TopicsRepository topicsRepository;
	
	@GetMapping("/api/widget/{topicId}")
	public List<Widget> findAllWidget(@PathVariable("topicId") Integer topicId) {
			return widgetRepository.findAllWidgetByTopic(topicId.toString());
	}
	@PostMapping("/api/widget/save/{topicId}")
	public void saveAllWidget(@PathVariable("topicId") int topicId,@RequestBody List<Widget> widgets)
	{
		System.out.println("savetopic............>>>>"+widgets);
		Optional<Topics> data = topicsRepository.findById(topicId);
		if(data.isPresent()) {
			Topics topics = data.get();
			
			for(Widget wiget: widgets)
			{
				if(wiget.getTopicId()!=null) {
					Integer topicIdVerifier = Integer.parseInt(wiget.getTopicId());
					System.out.println(topicId+"  ### "+topicIdVerifier);
					if(topicId == topicIdVerifier) {
						System.out.println("its a match");
						wiget.setTopics(topics);
						widgetRepository.save(wiget);
					}
				}
			}
			
		}
		
	}
	@DeleteMapping("/api/widget/delete")
	public void deleteWidget(@RequestBody Widget widget)
	{
		System.out.println("inside delete!!!!");
		widgetRepository.deleteById(widget.getId());
	}
	
	

}
