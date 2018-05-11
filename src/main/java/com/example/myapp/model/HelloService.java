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
public class HelloService {

    /*@RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }*/
	
	
	@Autowired
	AdminHostRepo adminrepo;
	
	
	
	@RequestMapping(value="/api/adduser")
	public void addValues(String user,String pass,String first,String last, String role)
	{
		System.out.println(user+" "+pass+" "+first+" "+last+" "+role);
		AdminHost userInfo = new AdminHost();
		userInfo.setFirst(first.trim());
		userInfo.setLast(last.trim());
		userInfo.setPassword(pass.trim());
		userInfo.setRole(role.trim());
		userInfo.setUser(user.trim());
		adminrepo.save(userInfo);
		
	}
	
	@RequestMapping(value="/api/searchuser")
	public Set<AdminHost> serachUser(String search)
	{
		System.out.println("**************   "+search+"  **************");
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
		System.out.println("hello!! i am in onload");
		Iterable<AdminHost> listByUser=new ArrayList<>();
		listByUser=adminrepo.findAll();
		return listByUser;
		
	}
	


}