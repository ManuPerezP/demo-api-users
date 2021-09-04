package com.globallogic.demo.api.users.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.demo.api.users.entity.Phone;
import com.globallogic.demo.api.users.entity.User;
import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.repository.UserRepository;

@Service
public class UserService {

	Log LOGGER = LogFactory.getLog(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers()throws UserServiceException {
		try {
			return userRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new UserServiceException("Error in list users", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public User update(UserData createUserRest, UUID id) throws UserServiceException {
		try {
			return userRepository.findByUserId(id).map(user -> {
				user.setModified(LocalDateTime.now());

				user.getPhones().clear();
				List<Phone> phones = createUserRest.getPhones().stream().map(p -> {
					Phone phone = new Phone();
					phone.setNumber(p.getNumber());
					phone.setCityCode(p.getCityCode());
					phone.setCountryCode(p.getCountryCode());
					return phone;

				}).collect(Collectors.toList());

				user.getPhones().addAll(phones);
				user.setName(createUserRest.getName());

				return userRepository.save(user);
			}).get();
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new UserServiceException("Error in update user", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public void delete(String id) throws UserServiceException {
		try {
			userRepository.delete(new User(UUID.fromString(id)));
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new UserServiceException("Error in delete user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
