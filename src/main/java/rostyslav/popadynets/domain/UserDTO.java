package rostyslav.popadynets.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rostyslav.popadynets.entity.enums.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

	@JsonIgnore
	private Long id;

	private String userId;

	private String firstName;

	private String lastName;

	private String email;

	private String password;
	
	private UserRole role;
	
	private Boolean emailVarificationStatus;

}
