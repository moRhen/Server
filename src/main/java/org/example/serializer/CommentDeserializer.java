package org.example.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.CommentDto;

public class CommentDeserializer implements Deserializer<CommentDto> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
  }

  @Override
  public CommentDto deserialize(String topic, byte[] bytes) {
    try {
      return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), CommentDto.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() {
    Deserializer.super.close();
  }
}
