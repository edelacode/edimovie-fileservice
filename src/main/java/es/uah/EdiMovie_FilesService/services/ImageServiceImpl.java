package es.uah.EdiMovie_FilesService.services;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.uah.EdiMovie_FilesService.domain.ImageDTO;
import es.uah.EdiMovie_FilesService.exceptions.FunctionalException;
import es.uah.EdiMovie_FilesService.exceptions.ResourceNotFoundException;
import com.mongodb.client.gridfs.model.GridFSFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private GridFsTemplate gridFsTemplate;

	public String saveImage(MultipartFile file) {
		ObjectId fileId = null;

		try {
			fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
		} catch (IOException e) {
			log.error("Error load file: " + e.getMessage());
			throw new FunctionalException("Error load file");
		}
		return fileId.toHexString();
	}

	@Override
	public String updateImage(String imageId, MultipartFile newFile) {
		deleteImage(imageId);
		return saveImage(newFile);
	}

	@Override
	public void deleteImage(String imageId) {
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(imageId)));
	}

	@SuppressWarnings("unused")
	@Override
	public ImageDTO getImage(String imageId) {
		Query query = new Query(Criteria.where("_id").is(imageId));
		GridFSFile file = gridFsTemplate.findOne(query);

		if (ObjectUtils.isEmpty(file)) {
			throw new ResourceNotFoundException(imageId);
		}

		ImageDTO image = ImageDTO.builder()
				.id(file.getId().toString())
				.filename(file.getFilename())
				.length(file.getLength())
				.uploadDate(file.getUploadDate())
				.build();
		
		GridFsResource resource = gridFsTemplate.getResource(file);
		byte[] imageBytes;
		try {
			
			imageBytes = IOUtils.toByteArray(resource.getInputStream());	
			image.setImage(imageBytes);

		} catch (IllegalStateException e) {
			log.error("Error get bytes image : %s", e.getMessage());
		} catch (IOException e) {
			log.error("Error get bytes image : %s", e.getMessage());
		}

		return image;
	}

}
