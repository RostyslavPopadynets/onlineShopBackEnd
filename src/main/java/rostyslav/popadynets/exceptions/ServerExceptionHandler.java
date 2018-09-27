package rostyslav.popadynets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import rostyslav.popadynets.domain.response.ErrorMessage;
import rostyslav.popadynets.exceptions.models.LaptopNotFountException;
import rostyslav.popadynets.exceptions.models.MonitorNotFountException;
import rostyslav.popadynets.exceptions.models.PCNotFountException;
import rostyslav.popadynets.exceptions.models.PhoneNotFountException;
import rostyslav.popadynets.exceptions.models.TVNotFountException;
import rostyslav.popadynets.exceptions.models.TabletNotFountException;
import rostyslav.popadynets.exceptions.models.UserNotFountException;

@ControllerAdvice
public class ServerExceptionHandler {
	
	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<ErrorMessage> handleUserServiceException(
			UserServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = TVServiceException.class)
	public ResponseEntity<ErrorMessage> handleTVServiceException(
			TVServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = TabletServiceException.class)
	public ResponseEntity<ErrorMessage> handleTabletServiceException(
			UserServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = PhoneServiceException.class)
	public ResponseEntity<ErrorMessage> handlePhoneServiceException(
			UserServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = PCServiceException.class)
	public ResponseEntity<ErrorMessage> handlePCServiceException(
			UserServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = MonitorServiceException.class)
	public ResponseEntity<ErrorMessage> handleMonitorServiceException(
			UserServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = LaptopServiceException.class)
	public ResponseEntity<ErrorMessage> handleLaptopServiceException(
			UserServiceException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = UserNotFountException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(
			UserNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());

		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = TVNotFountException.class)
	public ResponseEntity<ErrorMessage> handleTVNotFountException(
			TVNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = TabletNotFountException.class)
	public ResponseEntity<ErrorMessage> handleTabletNotFountException(
			TabletNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = PhoneNotFountException.class)
	public ResponseEntity<ErrorMessage> handlePhoneNotFountException(
			PhoneNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = PCNotFountException.class)
	public ResponseEntity<ErrorMessage> handlePCNotFountException(
			PCNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = MonitorNotFountException.class)
	public ResponseEntity<ErrorMessage> handleMonitorNotFountException(
			MonitorNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = LaptopNotFountException.class)
	public ResponseEntity<ErrorMessage> handleLaptopNotFountException(
			LaptopNotFountException ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleAllException(
			Exception ex, WebRequest request) {
		
		ErrorMessage exception = new ErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
