package com.ibm.microoservices.registrationservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.microoservices.Utils.UtilityMethods;
import com.ibm.microoservices.model.UserRegistartionDTO;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRegistration {
	
	@GetMapping("/registration")
	@ResponseBody
	public UserRegistartionDTO getFooByIdUsingQueryParam(@RequestParam String username,
			@RequestParam String password,@RequestParam String ipaddress) {
		String uuid="Not Generated";  
		UserRegistartionDTO obj = new UserRegistartionDTO();
		String cityname = "";
		String msg = "User is not elligible to register";
		
		if(UtilityMethods.isBlankString(username)){
			msg = "Username can't be blank";
			obj.setUuid(uuid);
			obj.setMessage(msg);
		    return obj;
		}
		if(UtilityMethods.isBlankString(password)){
			msg = "Password can't be blank";
			obj.setUuid(uuid);
			obj.setMessage(msg);
		    return obj;
		}
		if(UtilityMethods.isBlankString(ipaddress)){
			msg = "ipaddress can't be blank";
			obj.setUuid(uuid);
			obj.setMessage(msg);
		    return obj;
		}
		// If the password not match
        // return message
		if(!UtilityMethods.isValidPassword(password)) {
			msg = "Password combination not match its contains at least 8 characters, 1 number, 1 capitalized letter, 1  one special character in this set _ # $ % . ";
			obj.setUuid(uuid);
			obj.setMessage(msg);
		    return obj;
		}
		
		//*************  GEO LOCATION *******************
		try {
			cityname = UtilityMethods.validateCityDetailsUsingIPAddrs(ipaddress);
			
			if((null != cityname) && (!cityname.equals("N/A")))  {
				msg = "User registered successfully. Username is "+username+" and City name is "+cityname;
				uuid=UUID.randomUUID().toString();
				//uuid = "8cf40204-99eb-45ae-a417-4b9de3bb1200";//For Unit testing
			} else {
				msg = "User is not elligible to register";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//*************  END ****************************
		
		
		obj.setUuid(uuid);
		obj.setMessage(msg);
	    return obj;
	}
}
