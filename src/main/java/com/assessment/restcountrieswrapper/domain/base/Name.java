package com.assessment.restcountrieswrapper.domain.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Name {
  private String common;
  private String official;
  private Map<String, NativeName> nativeName;

  private List<String> altSpellings;
}
