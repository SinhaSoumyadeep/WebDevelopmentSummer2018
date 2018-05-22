package com.example.myapp.coursemanagerservice;

import org.springframework.data.repository.CrudRepository;
import com.example.myapp.coursemanager.Topics;


public interface TopicsRepository
extends CrudRepository<Topics, Integer>{}
