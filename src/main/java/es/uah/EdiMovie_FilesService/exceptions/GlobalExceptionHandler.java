package es.uah.EdiMovie_FilesService.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.uah.EdiMovie_FilesService.exceptions.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ResourceNotFoundException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponseDTO notFoundRequest(Exception exception) {
		return new ErrorResponseDTO("Not Fount Resource", exception.getMessage(), 404, null);
	}
	
	@ExceptionHandler({BadRequestException.class, HttpMessageNotReadableException.class, DuplicateKeyException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponseDTO badRequest(Exception exception) {
		return new ErrorResponseDTO(exception.getMessage(), null, 400, null);
	}
	
	@ExceptionHandler({InvalidDataException.class, MethodArgumentNotValidException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponseDTO invalidData(Exception exception) {
		Map<String, String> errors = null;
		if (exception instanceof InvalidDataException ex && ex.hasErrors()) {
			errors = ex.getErrors();
		} else if (exception instanceof BindException ex && ex.hasFieldErrors()) {
			errors = new HashMap<>();
			ex.getFieldErrors().stream().collect(Collectors.toMap(x -> x.getField(), x -> x.getDefaultMessage()));
		}
		
		return new ErrorResponseDTO("Invalid Data", exception.getMessage(), 400, errors);
	}
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorResponseDTO methodNotSupported(Exception exception) {
		return new ErrorResponseDTO(exception.getMessage(), null, 405, null);
	}

	@ExceptionHandler({ResourceConflict.class})
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorResponseDTO conflict(Exception exception) {
		return new ErrorResponseDTO(exception.getMessage(), 409);
	}
	
	@ExceptionHandler({FunctionalException.class})
	public ErrorResponseDTO funcional(Exception exception) {
		return new ErrorResponseDTO("Functional Error", exception.getMessage(), 500, null);
	}
	
	@ExceptionHandler({Throwable.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseDTO throwable(Exception exception) {
		return new ErrorResponseDTO("INTERNAL_SERVER_ERROR", exception.getMessage(), 500);
	}
}
