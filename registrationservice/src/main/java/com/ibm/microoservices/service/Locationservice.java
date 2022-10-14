package com.ibm.microoservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.microoservices.model.GeoLocationResponce;

@Service
public class Locationservice {

	@Value("${locationapi.service.geolocation}")
	String geolocationUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String ValidateCountryAndGetCityUsingIPAddress(String ipAddress,String country){
		String city = null;
		ResponseEntity<GeoLocationResponce> res = 
				restTemplate.getForEntity(geolocationUrl + ipAddress, GeoLocationResponce.class);
		if("success".equals(res.getBody().getStatus()) && country.equals(res.getBody().getCountry())){
			city = res.getBody().getCity();
		}
		return city;
	}
}
