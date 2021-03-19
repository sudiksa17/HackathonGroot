package com.gonuclei.hackathonGroot;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonUtils {
  public <T> T getObjectFromJson(final String jsonString,final Class<T> valueType) throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
    return mapper.readValue(jsonString, valueType);
  }
}

