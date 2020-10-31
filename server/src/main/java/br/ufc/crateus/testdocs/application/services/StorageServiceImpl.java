package br.ufc.crateus.testdocs.application.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {
	private static final String PATH = "/home/david/www/test-docs/server/images/";

	@Override
	public String upload(InputStream inputStream, String originalName) {
		String name = UUID.randomUUID().toString() + "-" + originalName.replaceAll(" ", "_");
		String filePath = PATH + name;
		byte[] buffer;
		try {
			buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			File targetFile = new File(filePath);
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return "http://localhost:8080/api/images/" + name;
	}

	@Override
	public InputStream getImage(String name) throws FileNotFoundException {
		String filePath = PATH + name;
		return new FileInputStream(new File(filePath));
	}

}
