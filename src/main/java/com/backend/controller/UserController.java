package com.backend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/getAllUsers")
	public JSONArray getAllUsers(){
		List<User> userList = userRepository.findAll();
		JSONArray result = new JSONArray();
		for(int i=0; userList.size()>i;i++) {
			JSONObject presutl= new JSONObject();
			presutl.put("id", userList.get(i).getId());
			presutl.put("firstName", userList.get(i).getFirstName());
			presutl.put("lastName", userList.get(i).getLastName());
			presutl.put("phoneno", userList.get(i).getPhoneNumber());
			result.add(presutl);
		}
		JSONArray sortedResult = new JSONArray();
		
		  List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		    for (int i = 0; i < result.size(); i++) {
		        jsonValues.add((JSONObject) result.get(i));
		    }
		
		Collections.sort( jsonValues, new Comparator<JSONObject>() {
	        //You can change "Name" with "ID" if you want to sort by ID
	        private static final String KEY_NAME = "phoneno";

	        @Override
	        public int compare(JSONObject a, JSONObject b) {
	            Long valA;
	            Long valB;

	      
	                valA = (Long) a.get(KEY_NAME);
	                valB = (Long) b.get(KEY_NAME);
	       

	            return valA.compareTo(valB);
	            //if you want to change the sort order, simply use the following:
	            //return -valA.compareTo(valB);
	        }
	    });
		
		System.out.println(jsonValues);
		   for (int i = 0; i < result.size(); i++) {
		        sortedResult.add(jsonValues.get(i));
		    }
		
		return sortedResult;
	}

}
