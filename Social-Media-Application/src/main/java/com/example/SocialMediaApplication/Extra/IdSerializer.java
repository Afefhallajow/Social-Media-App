package com.example.SocialMediaApplication.Extra;

import com.example.SocialMediaApplication.Entity.BaseEntity;
import com.example.SocialMediaApplication.Entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class IdSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object entity, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        gen.writeStartObject();
        gen.writeNumberField("id", entity instanceof BaseEntity ?  ((BaseEntity) entity).getId() : ((User) entity).getId());
        if (entity instanceof User){
            gen.writeStringField("username", ((User) entity).getUsername());
        }
        gen.writeEndObject();
        gen.writeEndArray();
    }
}
