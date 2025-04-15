package es.uah.EdiMovie_FilesService.exceptions;

import java.util.Map;

import org.springframework.util.CollectionUtils;

public class InvalidDataException extends BaseCodeException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_INVALID_DATA = "Invalid Data";
	private Map<String, String> errors;
	
	public InvalidDataException() {
		this(MESSAGE_INVALID_DATA);
	}

	public InvalidDataException(String message) {
		super(MESSAGE_INVALID_DATA);
	}
	
	public InvalidDataException(Map<String, String> errors) {
		this(MESSAGE_INVALID_DATA, errors);
	}
	
	public InvalidDataException(String message, Map<String, String> errors) {
		super(MESSAGE_INVALID_DATA);
		this.errors = errors;
	}

	public InvalidDataException(Throwable cause) {
		this(MESSAGE_INVALID_DATA, cause);
	}
	
	public InvalidDataException(String message, Throwable cause) {
		super(MESSAGE_INVALID_DATA, cause);
	}
	
	public InvalidDataException(String message, Throwable cause, Map<String, String> errors) {
		super(MESSAGE_INVALID_DATA, cause);
		this.errors = errors;
	}
	
	public boolean hasErrors() {
		return !CollectionUtils.isEmpty(errors);
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

}
