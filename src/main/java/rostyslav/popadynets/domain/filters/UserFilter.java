package rostyslav.popadynets.domain.filters;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFilter {

	private String search; // поле по якому будемо шукати дані
	
}
