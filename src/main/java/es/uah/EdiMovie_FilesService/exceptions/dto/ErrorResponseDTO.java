package es.uah.EdiMovie_FilesService.exceptions.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
public class ErrorResponseDTO {

	private String title;
	private String detail;
	private int status;
	private Map<String, String> errors;

	public ErrorResponseDTO(String title, int status) {
		this(title, null, status, null);
	}
	
	public ErrorResponseDTO(String title, String detail, int status) {
		this(title, detail, status, null);
	}
	
	public ErrorResponseDTO(String title, int status, Map<String, String> errors) {
		this(title, null, status, errors);
	}

}
