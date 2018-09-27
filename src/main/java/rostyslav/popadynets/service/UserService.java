package rostyslav.popadynets.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.UserFilter;

public interface UserService {

	public void addUser(UserDTO userDTO);
	
	public UserDTO findUserById(String userid);
	
	public List<UserDTO> findAllUsers();
	
	public UserDTO findUserByEmail(String email);

	public UserDTO findUserByFirstName(String firstName);
	
	public UserDTO findUserByLastName(String lastName);
	
	public UserDTO findUserByPassword(String password);
	
	public void deleteUser(String userId);
	
	public void updateUser(UserDTO userDTO);

	public List<UserDTO> findAllUsersBySpecification(UserFilter filter, String ret);
	
	public List<UserDTO> findAllUsersByPages(Pageable pageable);
	
	public boolean existsUserByEmail(String email);
	
	public boolean existsUserByPassword(String password);
	
	public String signin(String email, String password);
	
	public void verifyAccount(String verifyToken);
	
}
