package testdocs.application.serializer;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    	LocalDateTime localDateTime =
    	        LocalDateTime.ofInstant(Instant.ofEpochMilli(json.getAsLong()), 
    	                                TimeZone.getDefault().toZoneId());
    	return localDateTime;
    }
}