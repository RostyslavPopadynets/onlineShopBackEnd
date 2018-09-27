package rostyslav.popadynets.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import rostyslav.popadynets.config.jwt.JWTTokenProvider;
import rostyslav.popadynets.constant.ApplicationConstants;
import rostyslav.popadynets.domain.UserDTO;
import rostyslav.popadynets.domain.filters.UserFilter;
import rostyslav.popadynets.domain.mail.Mail;
import rostyslav.popadynets.entity.User;
import rostyslav.popadynets.entity.enums.UserRole;
import rostyslav.popadynets.exceptions.UserServiceException;
import rostyslav.popadynets.exceptions.models.UserNotFountException;
import rostyslav.popadynets.repository.UserRepository;
import rostyslav.popadynets.service.MailService;
import rostyslav.popadynets.service.UserService;
import rostyslav.popadynets.service.utils.ObjectMapperUtils;
import rostyslav.popadynets.service.utils.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepositiry;

	@Autowired
	private ObjectMapperUtils modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MailService mailService;

	@Autowired
	private StringUtils stringUtils;

	@Override
	public void addUser(UserDTO userDTO) {
		
		String verifyToken = stringUtils.generate(100);
		
		if(userRepositiry.existsByEmail(userDTO.getEmail())) {
			throw new UserServiceException("User exist");
		} else {
			userDTO.setUserId(stringUtils.generate());
			if(userDTO.getRole() == null) {
				userDTO.setRole(UserRole.ROLE_USER);
			}
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			User u = modelMapper.map(userDTO, User.class);
			if(userDTO.getEmailVarificationStatus() != Boolean.TRUE) {
				u.setEmaleVerificationToken(verifyToken);
				u.setEmailVarificationStatus(Boolean.FALSE);
				System.out.println(u);
				userRepositiry.save(u);
				try {
					sendEmail(userDTO.getEmail(), verifyToken);
				} catch(Exception e) {
					System.err.println("Неправельний емейл");
				}
			}
			if(userDTO.getEmailVarificationStatus() == Boolean.TRUE) {
				u.setEmaleVerificationToken(null);
				userRepositiry.save(u);
			}

		}
	}
			
	@Override
	public void verifyAccount(String verifyToken) {
		User user = userRepositiry.findByEmaleVerificationToken(verifyToken);
		if(user != null) {
			user.setEmaleVerificationToken(null);
			user.setEmailVarificationStatus(Boolean.TRUE);
			userRepositiry.save(user);
		}
	}

	private void sendEmail(String email, String verufyToken) {
		String verifyURL = getHostName() + "verify?token=" + verufyToken;
		
		Mail mail = new Mail();
		mail.setTo(email);
		mail.setSubject("Реєстрація пройшла успішно");
		mail.setContent("Будь ласка підтвердіть ваш аккауни, перейшовши по силці: " + verifyURL);
		
		mailService.sendMessage(mail);
	}
	
	private String getHostName() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	}

	@Override
	public UserDTO findUserById(String userid) {
		User user = null;
		try {
			user = userRepositiry.findByUserId(userid);
		} catch (Exception e) {
			throw new UserNotFountException(ApplicationConstants.USER_NOT_FOUND_EXCEPTION);
		}
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> findAllUsers() {
		List<User> users = userRepositiry.findAll();
		List<UserDTO> userDTOs = modelMapper.mapAll(users, UserDTO.class);
		return userDTOs;
	}

	@Override
	public UserDTO findUserByEmail(String email) {
		boolean e = userRepositiry.existsByEmail(email);
		if(e) {
			User user = userRepositiry.findByEmail(email);
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;
		} else {
			throw new UserServiceException("Користувача з таким емейлом не існує");
		}
	}

	@Override
	public UserDTO findUserByFirstName(String firstName) {
		boolean e = userRepositiry.existsByEmail(firstName);
		if(e) {
		User user = userRepositiry.findByFirstName(firstName);
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;
		} else {
			throw new UserServiceException("Користувача з таким ім'ям не існує");
		}
	}

	@Override
	public UserDTO findUserByLastName(String lastName) {
		boolean e = userRepositiry.existsByEmail(lastName);
		if(e) {
			User user = userRepositiry.findByLastName(lastName);
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;
		} else {
			throw new UserServiceException("Користувача з таким прізвищем не існує");
		}
	}

	@Override
	public UserDTO findUserByPassword(String password) {
		boolean e = userRepositiry.existsByEmail(password);
		if(e) {
			User user = userRepositiry.findByPassword(password);
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return userDTO;
		} else {
			throw new UserServiceException("Користувача з таким паролем не існує");
		}
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		userRepositiry.save(modelMapper.map(userDTO, User.class));
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepositiry.findByUserId(userId);
		if (user != null) {
			userRepositiry.delete(user);
		} else {
			throw new UserServiceException("Користувача не існує");
		}
	}

	@Override
	public List<UserDTO> findAllUsersBySpecification(UserFilter filter, String ret) {
		return modelMapper.mapAll(userRepositiry.findAll(getSpecification(filter, ret)), UserDTO.class);
	}

	private Specification<User> getSpecification(UserFilter filter, String ret) {
		return new Specification<User>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				if (filter.getSearch().isEmpty()) {
					return null;
				}

				if (ret.equals("firstName")) {
					Expression<String> searchByFirstName = root.get("firstName");
					Predicate searchTitleByFirstNamePredicate = criteriaBuilder.like(searchByFirstName,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByFirstNamePredicate);
				}

				if (ret.equals("lastName")) {
					Expression<String> searchByLastName = root.get("lastName");
					Predicate searchTitleByLastNamePredicate = criteriaBuilder.like(searchByLastName,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByLastNamePredicate);
				}

				if (ret.equals("email")) {
					Expression<String> searchByEmail = root.get("email");
					Predicate searchTitleByEmailPredicate = criteriaBuilder.like(searchByEmail,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByEmailPredicate);
				}

				if (ret.equals("password")) {
					Expression<String> searchByPassword = root.get("password");
					Predicate searchTitleByPasswordPredicate = criteriaBuilder.like(searchByPassword,
							"%" + filter.getSearch() + "%");
					return criteriaBuilder.and(searchTitleByPasswordPredicate);
				} else {
					return null;
				}
			}
		};
	}

	@Override
	public List<UserDTO> findAllUsersByPages(Pageable pageable) {
		Page<User> usersPage = userRepositiry
				.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())
				// 1 - сторінка яку показата
				// 2 - кількість сторінок які показати
				);

		List<User> users = usersPage.getContent();
		List<UserDTO> userDTOs = modelMapper.mapAll(users, UserDTO.class);
		return userDTOs;
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userRepositiry.existsByEmail(email);
	}

	@Override
	public boolean existsUserByPassword(String password) {
		return userRepositiry.existsByPassword(password);
	}

	@Override
	public String signin(String email, String password) {
		System.out.println(">>> " + email);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		System.out.println(">>> " + email);
		return jwtTokenProvider.createToken(email, userRepositiry.findByEmail(email).getRole());
	}

}
