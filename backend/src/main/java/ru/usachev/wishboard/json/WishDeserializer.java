package ru.usachev.wishboard.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import ru.usachev.wishboard.entities.Wish;

public class WishDeserializer extends JsonDeserializer<Wish> {

  @Override
  public Wish deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    ObjectCodec objectCodec = jsonParser.getCodec();
    JsonNode jsonNode = objectCodec.readTree(jsonParser);
    return new Wish(jsonNode.get("name").asText(), jsonNode.get("person_id").asInt());
  }
}
