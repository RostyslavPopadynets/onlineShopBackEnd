package rostyslav.popadynets.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rostyslav.popadynets.entity.User;
import rostyslav.popadynets.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("User with email: '" + email + "' not found");
		}
		
		return org.springframework.security.core.userdetails.User
				.builder()
					.username(user.getEmail())
					.password(user.getPassword())
					.authorities(user.getRole())
				.build();
	}

}