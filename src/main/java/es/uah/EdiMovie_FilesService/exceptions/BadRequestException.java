package es.uah.EdiMovie_FilesService.exceptions;

public class BadRequestException extends BaseCodeException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
