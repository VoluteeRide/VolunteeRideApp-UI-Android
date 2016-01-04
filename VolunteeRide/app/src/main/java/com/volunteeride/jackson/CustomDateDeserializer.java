package com.volunteeride.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;

/**
 * Created by ayazlakdawala on 12/15/15.
 */
public class CustomDateDeserializer extends JsonDeserializer<DateTime>
{
    @Override
    public DateTime deserialize(JsonParser jsonparser,
                            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        String format = "yyyy-MM-dd'T'HH:mm:ssZ";
        String date = jsonparser.getText();
        return DateTimeFormat.forPattern(format).parseDateTime(date);
    }

}
