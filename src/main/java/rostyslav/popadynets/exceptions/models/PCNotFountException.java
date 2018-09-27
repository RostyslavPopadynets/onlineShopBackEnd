package rostyslav.popadynets.exceptions.models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PCNotFountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PCNotFountException(String message) {
		super(message);
	}
	
}
