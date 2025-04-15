package es.uah.EdiMovie_FilesService.exceptions;

public class FunctionalException extends BaseCodeException {

	private static final long serialVersionUID = 1L;

	public FunctionalException(String errorCode) {
		super(errorCode);
	}

	public FunctionalException(String errorCode, String message) {
		super(errorCode, message);
	}

	public FunctionalException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

}
