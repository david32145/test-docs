package br.ufc.crateus.testdocs.application.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.testdocs.application.services.StorageService;
@RestController
@CrossOrigin
@RequestMapping("/api/images")
public class ImageController {
	
	private StorageService storageService;

	public ImageController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@RequestMapping("/{name}")
	@ResponseBody
	public HttpEntity<byte[]> getArticleImage(@PathVariable("name") String name) throws FileNotFoundException, IOException {
	    byte[] image = storageService.getImage(name).readAllBytes();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    headers.setContentLength(image.length);

	    return new HttpEntity<byte[]>(image, headers);
	}

}
