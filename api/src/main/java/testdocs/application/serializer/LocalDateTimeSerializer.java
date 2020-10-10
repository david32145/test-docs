package testdocs.application.serializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

	@Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
    	ZoneId zoneId = ZoneId.systemDefault();
    	long epoch = localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    	return new JsonPrimitive(epoch);
    }
}