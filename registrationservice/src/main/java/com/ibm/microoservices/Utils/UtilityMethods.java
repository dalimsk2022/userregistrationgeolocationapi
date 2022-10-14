package com.ibm.microoservices.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}
