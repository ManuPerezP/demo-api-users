package com.globallogic.demo.api.users.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.Phones;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.jsons.response.UserDetails;

@SpringBootTest
public class AuthControllerTest {
	
	public static final UserData  userData= new UserData();
	
	public static final UserDetails userDetails = new UserDetails();
	
	@Mock
	private AuthController authController;
	
	/*
	@Test()
	public void postRegisterUserMissingFields() {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
	    
	    List<Phones> list = new ArrayList<Phones>();

	    
	    userData.setEmail("juan@juan.cl");
	    userData.setName("Juan");
	    userData.setPassword("Hh33123456");
	    userData.setPhones(list);
		//Mockito.when(authController.registerUser(userData,result))
	//	Mockito.when(authController.registerUser(userData, result)).thenReturn(userDetails);
		try {
		
		final UserDetails resp = authController.registerUser(userData, result);
	//	assertTrue(actualMessage.contains(expectedMessage));
		
		}catch(Exception e) {
			System.out.println("---->e: "+e.getMessage());
		}
		
	//	System.out.println("ACA--->"+resp);
		
		
	//	System.out.println(result);
	}*/
	
	
	@Test()  //(expected = NullPointerException.class)
	public void postRegisterUser() throws UserServiceException {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
	    
	    List<Phones> list = new ArrayList<Phones>();
	    Phones phone = new Phones();
	    phone.setCityCode("1");
	    phone.setCountryCode("2");
	    phone.setNumber("3");
	    list.add(phone);
	    
	    userData.setEmail("juan@juan.cl");
	    userData.setName("Juan");
	    userData.setPassword("Hh33123456");
	    userData.setPhones(list);
		//Mockito.when(authController.registerUser(userData,result))
		Mockito.when(authController.registerUser(userData, result)).thenReturn(userDetails);
		
		final UserDetails resp = authController.registerUser(userData, result);
		
		assertEquals(userData.getEmail(), resp.getEmail());
	//	assertTrue(actualMessage.contains(expectedMessage));
		
		//System.out.println("ACA--->"+resp);
	}
	
}
