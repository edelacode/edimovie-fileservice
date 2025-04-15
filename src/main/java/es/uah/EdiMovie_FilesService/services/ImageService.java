package es.uah.EdiMovie_FilesService.services;

import org.springframework.web.multipart.MultipartFile;

import es.uah.EdiMovie_FilesService.domain.ImageDTO;

public interface ImageService {
	
	public String saveImage(MultipartFile file);
	
	public String updateImage(String imageId, MultipartFile newFile);
	
	public void deleteImage(String imageId);
	
	public ImageDTO getImage(String imageId);
	
}