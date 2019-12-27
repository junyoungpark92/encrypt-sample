package com.example.demo.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class StringToDoubleConverter implements AttributeConverter<String, Double> {

  @Override
  public Double convertToDatabaseColumn(String attribute) {
    return (attribute == null) ? null : Double.parseDouble(attribute);
  }

  @Override
  public String convertToEntityAttribute(Double dbData) {
    return dbData == null ? null : dbData.toString();
  }
}
