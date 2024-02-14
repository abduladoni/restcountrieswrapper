package com.assessment.restcountrieswrapper.domain.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Car {
  private List<String> signs;
  private String side;
}
