package com.example.SocialMediaApplication.Extra;

import com.example.SocialMediaApplication.Entity.Comment;
import com.example.SocialMediaApplication.Entity.Likes;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class CommentSerializer extends JsonSerializer<List<Comment>> {
    @Override
    public void serialize(List<Comment> comments, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Comment comment : comments) {
            gen.writeStartObject();
            gen.writeNumberField("id", comment.getId());
            gen.writeStringField("content", comment.getContent());
            gen.writeStringField("username", comment.getUser() != null ? comment.getUser().getUsername() : "guest");
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
