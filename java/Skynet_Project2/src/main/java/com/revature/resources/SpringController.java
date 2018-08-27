package com.revature.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.bean.Users;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.service.Register;

@RestController
public class SpringController {
	
	//-------------------Create a User-------------------------------------------------------- \\
	@RequestMapping(headers="Accept=application/json", value="/play.do", method = RequestMethod.POST)
	public String registerUser(@RequestBody String jsonObject, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		Register r = new Register();
		Users user = null;
		System.out.println("jsonObject: " + jsonObject);
		try {
			user = new ObjectMapper().readValue(jsonObject, Users.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("User: " + user);
		if(r.createUser(user.getUsername())){
		session.setAttribute("username", user.getUsername());
		
		System.out.println("Created user: " + user.getUsername());
		
		return "lobby";
		
		
		}else{
			
			return "login";
			
		}
		
	}
	
	//-------------------Retrieve All Players--------------------------------------------------------
    
    @RequestMapping(value = "/lobby", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUsers() {
    	UserDao dao = new UserDaoImpl();
        List<Users> users = dao.getUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }
	
	//-------------------Create a User--------------------------------------------------------
    
    /*@RequestMapping(value = "/user/", method = RequestMethod.POST)
    public String registerUser(Users user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
        System.out.println("Creating User " + user.getUsername());
  
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        userService.saveUser(user);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }*/
	
	
}
