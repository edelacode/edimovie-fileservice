package es.uah.EdiMovie_FilesService.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
	private String id;
	private String filename;
	private long length;
	private Date uploadDate;
	private byte[] image;
}
