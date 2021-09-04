package com.globallogic.demo.api.users.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.demo.api.users.entity.Phone;
import com.globallogic.demo.api.users.entity.User;
import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.Phones;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.jsons.request.Login;
import com.globallogic.demo.api.users.jsons.response.UserDetails;
import com.globallogic.demo.api.users.repository.UserRepository;
import com.globallogic.demo.api.users.security.jwt.JwtUtils;
import com.globallogic.demo.api.users.security.services.UserDetailsImpl;

@Service
public class AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Autowired
	JwtUtils jwtUtils;
	
	public UserDetails login(Login loginRequest) {
		Authentication authentication = authenticationManager.authenticate( 
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		UserDetails resp = new UserDetails();
		
		final User user = userRepository.findByUserEmail(userDetails.getEmail().trim()).get(0);
		
		resp.setId(user.getId().toString());
		resp.setEmail(user.getEmail());
		resp.setName(user.getEmail());
    	resp.setPhones(
    			user.getPhones().stream().map(p->{
    	    		Phones phone = new Phones();
    	        	phone.setNumber(p.getNumber());
    	        	phone.setCityCode(p.getCityCode());
    	        	phone.setCountryCode(p.getCountryCode());
    	        	return phone;
    	        	
    	    	}).collect(Collectors.toList()));
    	
    	resp.setCreated(user.getCreated());
    	resp.setModified(user.getModified());
    	resp.setLastLogin(user.getLastLogin());
		resp.setToken(jwt);	
		resp.setIsActive(user.getIsActive());
		
		//actualizar login
		updateLastLogin(user.getId());
	
		return resp;
	}
	
	public UserDetails registerUser(UserData createUserRest) throws UserServiceException{
		//validaciones pre
		String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z0-9\\S]{8,}$";
    	String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,2}$";
    	
    	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Pattern pat = Pattern.compile(PASSWORD_PATTERN);
    	
    	if(!pattern.matcher(createUserRest.getEmail()).matches()) {
    		throw new UserServiceException("Correo no valido", HttpStatus.NOT_ACCEPTABLE);
    	}
    	
	    if(!pat.matcher(createUserRest.getPassword()).matches()) {
			throw new UserServiceException("El password debe ser de al menos 8 caracteres, contener al menos una letra minuscula, una mayuscula y dos numeros.", HttpStatus.NOT_ACCEPTABLE);
	    }

		final List<User> user = userRepository.findByUserEmail(createUserRest.getEmail().trim());
		
		if(user.size() > 0) {
			throw new UserServiceException("Correo ya registrado", HttpStatus.CONFLICT);
		}
		

		// Create new user's account
		final LocalDateTime date = LocalDateTime.now();
		
    	final User newUser = new User();
    	newUser.setEmail(createUserRest.getEmail().trim());
    	newUser.setName(createUserRest.getName());
    	newUser.setCreated(date);
    	newUser.setModified(date);
    	newUser.setLastLogin(date);
    	newUser.setPassword(createUserRest.getPassword());
    	newUser.setPassword(bCryptPasswordEncoder.encode(createUserRest.getPassword()));
    	
    	List<Phone> phones = createUserRest.getPhones().stream().map(p->{
    		Phone phone = new Phone();
        	phone.setNumber(p.getNumber());
        	phone.setCityCode(p.getCityCode());
        	phone.setCountryCode(p.getCountryCode());
        	return phone;
        	
    	}).collect(Collectors.toList());  

    	newUser.setPhones(phones);
    	
    	//crea usuario 
	 	
    	User userRepo = userRepository.save(newUser);
    	
    	//procedimiento respuesta

    	UserDetails resp = new UserDetails();
    	resp.setId(userRepo.getId().toString());
    	resp.setCreated(userRepo.getCreated());
    	resp.setModified(userRepo.getModified());
    	resp.setLastLogin(userRepo.getLastLogin());
    	resp.setEmail(userRepo.getEmail());
    	resp.setName(userRepo.getName());
    	resp.setIsActive(userRepo.getIsActive());
    	resp.setPhones(
    			userRepo.getPhones().stream().map(p->{
    	    		Phones phone = new Phones();
	    	        	phone.setNumber(p.getNumber());
	    	        	phone.setCityCode(p.getCityCode());
	    	        	phone.setCountryCode(p.getCountryCode());
    	        	return phone;
    	    	}).collect(Collectors.toList()));
    	
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(createUserRest.getEmail(), createUserRest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtils.generateJwtToken(authentication);
    	
    	resp.setToken(jwt);
    	
		return resp;
	}
	
    private void updateLastLogin(UUID id) {
        userRepository.findByUserId(id).map(user->{
        	LocalDateTime now = LocalDateTime.now();
       	user.setLastLogin(now);
       	user.setModified(now);
           return userRepository.save(user);
        });
   }
}
