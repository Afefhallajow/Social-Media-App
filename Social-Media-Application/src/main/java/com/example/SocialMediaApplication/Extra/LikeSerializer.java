package com.example.SocialMediaApplication.Extra;

import com.example.SocialMediaApplication.Entity.Likes;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class LikeSerializer extends JsonSerializer<List<Likes>> {
    @Override
    public void serialize(List<Likes> likes, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Likes like : likes) {
            gen.writeStartObject();
            gen.writeNumberField("id", like.getId());
            gen.writeStringField("username", like.getUser() != null ? like.getUser().getUsername() : "guest");
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}