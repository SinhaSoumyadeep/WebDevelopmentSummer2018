package com.example.myapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.bean.AdminHost;
import com.example.myapp.bean.AdminHostRepo;

@RestController
public class AdminService {

    /*@RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }*/
	
	
	@Autowired
	AdminHostRepo adminrepo;
	
	@RequestMapping(value="api/updateuser")
	public String updateValues(@RequestBody AdminHost UserObject)
	{

		List<AdminHost> listByUserName=new ArrayList<>();
		listByUserName = adminrepo.findAllByUser(UserObject.getUser());
		System.out.println(listByUserName);
		
		if(listByUserName.size()==1&&listByUserName.get(0).getUser().equals(UserObject.getUser())) {
			
			AdminHost delUser=listByUserName.get(0);
			adminrepo.delete(delUser);
			adminrepo.save(UserObject);
			System.out.println("successfully updated an user");
			return "success";
		}
		else
		{
			System.out.println("cannot be updated");
			return "cannot be updated";
		}
		
	}
	
	@RequestMapping(value="/api/adduser")
	public String addValues(@RequestBody AdminHost UserObject)
	{
		
		if(UserObject.getUser().equals(""))
		{
			System.out.println("user id field is empty");
			return "USER ID FEILD IS EMPTY";
		}
		
		
		List<AdminHost> listByUserName=new ArrayList<>();
		listByUserName = adminrepo.findAllByUser(UserObject.getUser());
		
		
		if(listByUserName.isEmpty()) {
			adminrepo.save(UserObject);
			System.out.println("successfully created an user");
			return "success";
		}
		else
		{
			System.out.println("cannot create with same user id");
			return "USER ALREADY EXISTS";
		}
		
		
	}
	
	@RequestMapping(value="/api/searchuser")
	public Set<AdminHost> serachUser(String search)
	{
		
		List<AdminHost> listByUserName=new ArrayList<>();
		List<AdminHost> listByFirstName=new ArrayList<>();
		List<AdminHost> listByLastName=new ArrayList<>();
		List<AdminHost> listByRole=new ArrayList<>();
		Set<AdminHost> finalSet= new HashSet<AdminHost>();
			
		listByUserName = adminrepo.findAllByUser(search);
		listByFirstName= adminrepo.findByFirstName(search);
		listByLastName= adminrepo.findByLastName(search);
		listByRole=adminrepo.findByRole(search);
		
		if(!listByUserName.isEmpty())
		finalSet.addAll(listByUserName);
		if(!listByFirstName.isEmpty())
		finalSet.addAll(listByFirstName);
		if(!listByLastName.isEmpty())
		finalSet.addAll(listByLastName);
		if(!listByRole.isEmpty())
		finalSet.addAll(listByRole);
		
		System.out.println(finalSet);
		return finalSet;
		
	}
	
	@RequestMapping(value="/api/deluser")
	public String delValues(String id)
	{
		System.out.println(id);
		Pattern pattern = Pattern.compile("trow\\[([0-9]*)\\]");
		Matcher matcher = pattern.matcher(id);
		String key = null;
		if(matcher.find())
		{
			key=matcher.group(1);
			System.out.println(key);
			adminrepo.deleteById(Integer.parseInt(key));
			return "success";
		}
		return "SOMETHING WENT WRONG!";
	}
	
	@RequestMapping(value="api/onload")
	public Iterable<AdminHost> onLoad()
	{
		System.out.println("Inside onload method...");
		Iterable<AdminHost> listByUser=new ArrayList<>();
		listByUser=adminrepo.findAll();
		return listByUser;
		
	}
	


}