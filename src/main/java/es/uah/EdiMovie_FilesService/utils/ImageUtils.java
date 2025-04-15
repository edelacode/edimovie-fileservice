package es.uah.EdiMovie_FilesService.utils;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageUtils {

	private static final String TYPE__JPG_IMAGE = "image/jpeg";
	private static final String TYPE_PNG_IMAGE = "image/png";

	public boolean isAcceptedImageType(String contentType) {
		return TYPE__JPG_IMAGE.equals(contentType) || TYPE_PNG_IMAGE.equals(contentType);
	}

	public String convertFileToBase64(MultipartFile file) {
		try {
			return Base64.getEncoder().encodeToString(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Error converting file to Base64", e);
		}
	}

	public String convertFileToBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public byte[] convertBase64ToFile(String base64) {
		return Base64.getDecoder().decode(base64);
	}

}