package com.example.myapp.coursemanagerservice;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.coursemanager.Lesson;



public interface LessonRepository
extends CrudRepository<Lesson, Integer>{}
