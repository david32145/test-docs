package br.ufc.crateus.testdocs.application.services;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface StorageService {

	String upload(InputStream inputStream, String originalName);
	
	InputStream getImage(String name) throws FileNotFoundException;
}
