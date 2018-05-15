package com.example.myapp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.user) LIKE CONCAT(LOWER(:user),'%')")
	List<User> findAllByUser(@Param("user")String user);
	
	@Query("SELECT usr FROM User usr WHERE usr.user = :user")
	User findByUsername(@Param("user")String user);
	
	@Query("SELECT usr FROM User usr WHERE usr.id = :id")
	User findByUserId(@Param("id")Integer id);
	
	@Query("SELECT usr FROM User usr WHERE usr.user = :user AND usr.password = :password")
	User findByUsernameAndPassword(@Param("user")String user,@Param("password")String pass);
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.first) LIKE CONCAT(LOWER(:first),'%')")
	public List<User> findByFirstName(@Param("first") String first);
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.last) LIKE CONCAT(LOWER(:last),'%')")
	public List<User> findByLastName(@Param("last") String last);
	
	@Query("SELECT usr FROM User usr WHERE LOWER(usr.role) LIKE CONCAT(LOWER(:role),'%')")
	public List<User> findByRole(@Param("role") String role);
	
	@Query("SELECT usr FROM User usr WHERE usr.email = :email")
	User findByEmail(@Param("email")String email);
	
}
