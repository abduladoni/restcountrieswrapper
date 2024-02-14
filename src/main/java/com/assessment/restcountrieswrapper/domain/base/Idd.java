package com.assessment.restcountrieswrapper.domain.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Idd {
  private String root;
  private List<String> suffixes;
}
