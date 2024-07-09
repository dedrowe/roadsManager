package com.streetsmanager.jsonconverters;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.streetsmanager.entity.RoadDTO;
import org.springframework.boot.jackson.JsonComponent;
import com.streetsmanager.exception_handling.exceptions.GeoJsonFormatException;

import java.io.IOException;

@JsonComponent
public class RoadDTOConverter {
    public static class RoadDTOSerializer extends StdSerializer<RoadDTO> {
        public RoadDTOSerializer() {
            this(null);
        }
        protected RoadDTOSerializer(Class<RoadDTO> t) {
            super(t);
        }

        @Override
        public void serialize(RoadDTO roadDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("type", "Feature");
            jsonGenerator.writeObjectField("geometry", roadDTO.getGeometry());
            jsonGenerator.writeObjectFieldStart("properties");
            jsonGenerator.writeNumberField("id", roadDTO.getId());
            jsonGenerator.writeStringField("name", roadDTO.getName());
            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();
        }
    }

    public static class RoadDTODeserializer extends JsonDeserializer<RoadDTO> {
        @Override
        public RoadDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);

            try {
                RoadDTO roadDTO = new RoadDTO();
                JsonNode temp = node.get("properties").get("id");
                if (temp != null) {
                    roadDTO.setId(temp.asInt());
                }
                temp = node.get("properties").get("name");
                if (temp != null) {
                    roadDTO.setName(temp.asText());
                }
                temp = node.get("geometry");
                if (temp != null) {
                    roadDTO.setGeometry(temp);
                }

                return roadDTO;
            }
            catch (NullPointerException e) {
                throw new GeoJsonFormatException();
            }
        }
    }
}
