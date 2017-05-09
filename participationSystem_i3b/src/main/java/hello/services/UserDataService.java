package hello.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hello.model.CitizenDTO;
import hello.model.User;
import hello.repository.DBServiceImpl;

@Service
public class UserDataService implements UserDetailsService {

	@Autowired
	private DBServiceImpl dbService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = dbService.getUser(username);
		if (user != null) {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("login", user.getEmail());
			vars.put("password", user.getPassword());
			CitizenDTO userDTO = restTemplate.postForObject(
					"http://localhost:8091/user", vars, CitizenDTO.class);

			if (userDTO != null) {
				return user;
			}
		}
		throw new UsernameNotFoundException("Username not found");
	}

	public void addUserData(User user) {
		dbService.addUser(user);
	}

}
