package com.example.myapp.bean;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminHostRepo extends CrudRepository<AdminHost, Integer> {
	
	
	List<AdminHost> findAllByUser(String user);
	
	@Query("SELECT usr FROM AdminHost usr WHERE LOWER(usr.first) LIKE CONCAT(LOWER(:first),'%')")
	public List<AdminHost> findByFirstName(@Param("first") String first);
	
	@Query("SELECT usr FROM AdminHost usr WHERE LOWER(usr.last) LIKE CONCAT(LOWER(:last),'%')")
	public List<AdminHost> findByLastName(@Param("last") String last);
	
	@Query("SELECT usr FROM AdminHost usr WHERE LOWER(usr.role) LIKE CONCAT(LOWER(:role),'%')")
	public List<AdminHost> findByRole(@Param("role") String role);
	
}
