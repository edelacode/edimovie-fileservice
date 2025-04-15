package es.uah.EdiMovie_FilesService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.uah.EdiMovie_FilesService.domain.ImageDTO;
import es.uah.EdiMovie_FilesService.services.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ApiResponse(responseCode = "400", description = "Bad request")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "403", description = "Forbidden")
@ApiResponse(responseCode = "404", description = "Resource not found")
@ApiResponse(responseCode = "500", description = "Internal Server Error")
@Tag(name = "Images", description = "Images API Rest")
@RequestMapping("/files/images")
public class ImageController {

	@Autowired
	private ImageService imageService;

	
	@Operation(summary = "Create Image", operationId = "create", description = "Create new Image")
	@ApiResponse(responseCode = "201", description = "Location created ID")
	@PostMapping(path = "/save", consumes = { "multipart/form-data" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public String saveImage(@RequestParam MultipartFile file) {
		return imageService.saveImage(file);
	}

	@Operation(summary = "Upload Image", operationId = "upload")
	@PutMapping(path = "/upload/{id}", consumes = { "multipart/form-data" })
	public String uploadImage(@PathVariable String id, @RequestParam MultipartFile file) {
		return imageService.saveImage(file);
	}
	
    @GetMapping("/{id}")
    public ImageDTO getImage(@PathVariable String id) {
        return imageService.getImage(id);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable String id) {
        imageService.deleteImage(id);
    }
}
