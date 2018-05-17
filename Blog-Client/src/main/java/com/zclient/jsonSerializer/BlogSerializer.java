package com.zclient.jsonSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zclient.model.Blog;

import java.io.IOException;

/**
 * @Description: student josn序列化
 * @Author: liubin
 * @Date: Created in 13:10 2018/4/10
 * @Modified by:
 */
public class BlogSerializer extends JsonSerializer<Blog> {
    @Override
    public void  serialize(Blog blog, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("name", blog.getBlogid());
        jsonGenerator.writeStringField("age", blog.getTitle());
        jsonGenerator.writeStringField("address", blog.getSummary());
        jsonGenerator.writeEndObject();
    }
}
