package com.ibm.microoservices.Utils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.ResourceUtils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

public class UtilityMethods {

	
	// Function to validate the password.
    public static boolean
    isValidPassword(String password)
    {
    	// Regex to check valid password.
    			String regex = "^(?=.*[0-9])"
    						+ "(?=.*[A-Z])"
    						+ "(?=.*[_#$%.])"
    						+ "(?=\\S+$).{8,20}$";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }
  
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
  
        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public static boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
    
    public static String validateCityDetailsUsingIPAddrs(String ip) 
	  throws IOException, GeoIp2Exception {//81.2.69.142
	    String city = "N/A";
	        
	    File database = ResourceUtils.getFile("classpath:GeoLite2-City-Test.mmdb");
	    		//new File(getClass().getResource("GeoLite2-City-Test.mmdb").getFile());
	    DatabaseReader dbReader = new DatabaseReader.Builder(database)
	      .build();
	        
	    InetAddress ipAddress = InetAddress.getByName(ip);
	    CityResponse response = dbReader.city(ipAddress);
	        
	    String countryName = response.getCountry().getName();
	    String cityName = response.getCity().getName();
	    //String postal = response.getPostal().getCode();
	    //String state = response.getLeastSpecificSubdivision().getName();
	    System.out.println(countryName+" ------------  :: ----------  "+cityName);
	    if((null != countryName) && (countryName.toLowerCase().equals("united kingdom"))){
	    	city = cityName;
	    }
	    return city;
	}
}
