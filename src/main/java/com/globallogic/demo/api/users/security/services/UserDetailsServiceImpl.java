package com.globallogic.demo.api.users.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globallogic.demo.api.users.entity.User;
import com.globallogic.demo.api.users.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user = userRepository.findByUserEmail(username);

		if (!(user.size() > 0)) {
			throw new UsernameNotFoundException("User Not Found with email: " + username);
		}

		return UserDetailsImpl.build(user.get(0));
	}

}
