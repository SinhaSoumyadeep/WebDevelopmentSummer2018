package com.example.myapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String updateValues(String user,String pass,String first,String last, String role)
	{

		List<AdminHost> listByUserName=new ArrayList<>();
		listByUserName = adminrepo.findAllByUser(user);
		System.out.println(listByUserName);
		
		if(listByUserName.size()==1&&listByUserName.get(0).getUser().equals(user)) {
			
			AdminHost delUser=listByUserName.get(0);
			adminrepo.delete(delUser);
			AdminHost userInfo = new AdminHost();
			userInfo.setFirst(first.trim());
			userInfo.setLast(last.trim());
			userInfo.setPassword(pass.trim());
			userInfo.setRole(role.trim());
			userInfo.setUser(user.trim());
			adminrepo.save(userInfo);
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
	public String addValues(String user,String pass,String first,String last, String role)
	{
		
		if(user.equals(""))
		{
			System.out.println("user id field is empty");
			return "USER ID FEILD IS EMPTY...";
		}
		
		
		List<AdminHost> listByUserName=new ArrayList<>();
		listByUserName = adminrepo.findAllByUser(user);
		
		
		if(listByUserName.isEmpty()) {
			AdminHost userInfo = new AdminHost();
			userInfo.setFirst(first.trim());
			userInfo.setLast(last.trim());
			userInfo.setPassword(pass.trim());
			userInfo.setRole(role.trim());
			userInfo.setUser(user.trim());
			adminrepo.save(userInfo);
			System.out.println("successfully created an user");
			return "success";
		}
		else
		{
			System.out.println("cannot create with same user id");
			return "USER ALREADY EXISTS...";
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
	public void delValues(String id)
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
		}
		
	}
	
	@RequestMapping(value="api/onload")
	public Iterable<AdminHost> onLoad()
	{
		
		Iterable<AdminHost> listByUser=new ArrayList<>();
		listByUser=adminrepo.findAll();
		return listByUser;
		
	}
	


}