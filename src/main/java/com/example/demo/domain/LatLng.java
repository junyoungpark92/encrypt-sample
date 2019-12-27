package com.example.demo.domain;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LatLng {

  @Convert(converter = StringToDoubleConverter.class)
  private String lat;

  @Convert(converter = StringToDoubleConverter.class)
  private String lng;

  public Double getLat() {
    return lat == null ? null : Double.parseDouble(lat);
  }

  public void setLat(Double lat) {
    this.lat = lat == null ? null : lat.toString();
  }

  public Double getLng() {
    return lng == null ? null : Double.parseDouble(lng);
  }

  public void setLng(Double lng) {
    this.lng = lng == null ? null : lng.toString();
  }
}
