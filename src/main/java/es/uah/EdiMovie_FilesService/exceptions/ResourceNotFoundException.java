package es.uah.EdiMovie_FilesService.exceptions;

public class ResourceNotFoundException extends BaseCodeException {

	private static final long serialVersionUID = 1L;
	private static String errorCodeNOtFound = "Not Found Resource";

	public ResourceNotFoundException(String id) {
		super(errorCodeNOtFound, String.format(" %s Id: %s", id));
	}
	
	public ResourceNotFoundException(String message, String id) {
		super(errorCodeNOtFound, String.format(" %s Id: %s", message, id));
	}
	
	public ResourceNotFoundException(String message, Integer id) {
		super(errorCodeNOtFound, String.format(" %s Id: %s", message, id.toString()));
	}


	public ResourceNotFoundException(String messageNotFound, Throwable cause) {
		super(messageNotFound, cause);
	}

}
