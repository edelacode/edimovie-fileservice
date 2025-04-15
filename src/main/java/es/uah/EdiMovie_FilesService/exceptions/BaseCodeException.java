package es.uah.EdiMovie_FilesService.exceptions;

public class BaseCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;

	public BaseCodeException() {
		super();
	}

	public BaseCodeException(String errorCode) {
		this.setErrorCode(errorCode);
	}

	public BaseCodeException(String errorCode, String message) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public BaseCodeException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public BaseCodeException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	protected BaseCodeException(String errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
