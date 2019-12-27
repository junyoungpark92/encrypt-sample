package com.example.demo.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class IntegerToStringConverter implements AttributeConverter<Integer, String> {

  @Override
  public String convertToDatabaseColumn(Integer attribute) {
    return (attribute == null) ? null : attribute.toString();
  }

  @Override
  public Integer convertToEntityAttribute(String dbData) {
    return dbData == null ? null : Integer.parseInt(dbData);
  }
}
