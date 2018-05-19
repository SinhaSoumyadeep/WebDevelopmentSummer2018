package com.example.myapp.coursemanagerservice;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.coursemanager.Module;


public interface ModuleRepository
extends CrudRepository<Module, Integer>{}
