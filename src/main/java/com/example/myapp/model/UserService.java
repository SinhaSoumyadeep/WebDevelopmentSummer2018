package com.example.myapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.bean.User;
import com.example.myapp.bean.UserRepository;

@RestController
public class UserService {

    /*@RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }*/
	
	
	@Autowired
	UserRepository adminrepo;
	
	@RequestMapping(value="api/updateuser")
	public String updateValues(@RequestBody User UserObject)
	{
		
		User userNameChangechk = adminrepo.findByUserId(UserObject.getId());
		if(!userNameChangechk.getUser().equals(UserObject.getUser()))
		{
			return "USERNAME CANNOT BE MODIFIED";
		}

		List<User> listByUserName=new ArrayList<>();
		listByUserName = adminrepo.findAllByUser(UserObject.getUser());
		System.out.println(listByUserName);
		
		
		if(listByUserName.size()==1&&listByUserName.get(0).getUser().equals(UserObject.getUser())) {
			
			adminrepo.save(UserObject);
			System.out.println("successfully updated an User");
			return "success";
		}
		else
		{
			System.out.println("cannot be updated");
			return "cannot be updated";
		}
		
	}
	
	@RequestMapping(value="/api/adduser")
	public String addValues(@RequestBody User UserObject)
	{
		
		if(UserObject.getUser().equals(""))
		{
			System.out.println("user id field is empty");
			return "USER ID FEILD IS EMPTY";
		}
		
		
		List<User> listByUserName=new ArrayList<>();
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
	public Set<User> serachUser(String search)
	{
		
		List<User> listByUserName=new ArrayList<>();
		List<User> listByFirstName=new ArrayList<>();
		List<User> listByLastName=new ArrayList<>();
		List<User> listByRole=new ArrayList<>();
		Set<User> finalSet= new HashSet<User>();
			
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
	public Iterable<User> onLoad()
	{
		System.out.println("Inside onload method...");
		Iterable<User> listByUser=new ArrayList<>();
		listByUser=adminrepo.findAll();
		return listByUser;
		
	}
	
	@RequestMapping(value="api/finduser")
	public String findByUserName(String user)
	{
		User checkUser=adminrepo.findByUsername(user);
		if(checkUser == null)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
		
		
	}
	
	@RequestMapping(value="api/login")
	public User login(@RequestBody User UserObject, HttpSession session)
	{
		String user = UserObject.getUser();
		String pass = UserObject.getPassword();
		User DAOUser = new User();
		
		DAOUser = adminrepo.findByUsernameAndPassword(user, pass);
		if(DAOUser!=null)
		{
			session.setAttribute("id", DAOUser.getId());
			return DAOUser;
		}
		
		
		
		return new User();
	}
	
	@RequestMapping(value="api/register")
	public String register(@RequestBody User UserObject, HttpSession session)
	{
		User DAOUser = new User();
		
		//adminrepo.save(UserObject);
		//DAOUser = adminrepo.findByUsernameAndPassword(UserObject.getUser(), UserObject.getPassword());
		
		
		if(UserObject.getUser().equals(""))
		{
			System.out.println("user id field is empty");
			return "USER ID FEILD IS EMPTY";
		}
		
		
		List<User> listByUserName=new ArrayList<>();
		listByUserName = adminrepo.findAllByUser(UserObject.getUser());
		
		
		if(listByUserName.isEmpty()) {
			adminrepo.save(UserObject);
			DAOUser = adminrepo.findByUsernameAndPassword(UserObject.getUser(), UserObject.getPassword());
			if(DAOUser!=null)
			{
				session.setAttribute("id", DAOUser.getId());
				System.out.println("successfully created an user");
				return "success";
			}
			else
			{
				return "SOMETHING WENT WRONG!";
			}
			
		}
		else
		{
			System.out.println("cannot create with same user id");
			return "USER ALREADY EXISTS";
		}

			

	}
	
	@RequestMapping(value="api/retrieveProfile")
	public User retrieveProfile(@RequestBody User UserObject,  HttpSession session)
	{
		
		System.out.println(UserObject);
		System.out.println(session.getAttribute("id"));
		Integer id = Integer.parseInt(session.getAttribute("id").toString());
		
		User retUserObj = adminrepo.findByUserId(id);
		System.out.println(retUserObj);
		return retUserObj;
	}
	
	@RequestMapping(value="api/profile")
	public User updateProfile(@RequestBody User UserObject,  HttpSession session)
	{
		
		System.out.println(":::::::::"+UserObject);
		System.out.println(session.getAttribute("id"));
		//Integer id = Integer.parseInt(session.getAttribute("id").toString());
		adminrepo.save(UserObject);
		System.out.println(UserObject);
		return UserObject;
	}
	
	@RequestMapping(value="api/logout")
	public String logout( HttpSession session)
	{
		
		System.out.println(session.getAttribute("id"));
		session.invalidate();
		return "logout";
	}
	
	


}