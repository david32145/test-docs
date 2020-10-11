package testdocs.application.services;

import java.io.BufferedReader;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import testdocs.application.serializer.LocalDateTimeDeserializer;
import testdocs.application.serializer.LocalDateTimeSerializer;

public class GsonService {
	private Gson gson;
	
	public GsonService() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
		Gson gson = gsonBuilder.create();
		this.gson = gson;
	}
	
	public <T> T fromJson(BufferedReader reader, Class<T> eClass) {
		return this.gson.fromJson(reader, eClass);
	}
	
	public String toJson(Object obj) {
		return this.gson.toJson(obj);
	}
}
