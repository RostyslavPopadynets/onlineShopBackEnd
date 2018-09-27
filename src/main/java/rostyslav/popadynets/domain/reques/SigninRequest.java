package rostyslav.popadynets.domain.reques;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SigninRequest {

	private String email;
	private String password;

}
