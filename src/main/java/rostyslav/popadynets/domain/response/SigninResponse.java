package rostyslav.popadynets.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rostyslav.popadynets.domain.UserDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponse {

	private String token;
	private String role;
	private UserDTO user;

}
