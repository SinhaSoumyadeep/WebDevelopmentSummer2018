package com.example.myapp.coursemanagerservice;


import org.springframework.data.repository.CrudRepository;

import com.example.myapp.coursemanager.EssayQuestion;

public interface EssayQuestionRepository extends CrudRepository<EssayQuestion, Integer> {

}
