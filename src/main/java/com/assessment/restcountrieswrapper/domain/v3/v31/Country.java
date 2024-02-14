package com.assessment.restcountrieswrapper.domain.v3.v31;


import com.assessment.restcountrieswrapper.domain.base.BaseCountry;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
public class Country extends BaseCountry {
  private Flag flags;
  private Flag coatOfArms;
  private String startOfWeek;
  private CapitalInformation capitalInfo;
  private Map<String, String> postalCode;
}
