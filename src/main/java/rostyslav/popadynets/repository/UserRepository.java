package rostyslav.popadynets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rostyslav.popadynets.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByUserId(String userId);

	User findByFirstName(String firstName);

	User findByLastName(String firstName);

	User findByEmail(String email);

	User findByPassword(String password);
	
	boolean existsByEmail(String email);
	
	boolean existsByPassword(String password);

	User findByEmaleVerificationToken(String token);
	
}
