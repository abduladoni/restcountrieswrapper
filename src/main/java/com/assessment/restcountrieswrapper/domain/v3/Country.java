package com.assessment.restcountrieswrapper.domain.v3;


import com.assessment.restcountrieswrapper.domain.base.BaseCountry;

import java.util.List;

public class Country extends BaseCountry {
  private List<String> flags;

  public List<String> getFlags() {
    return flags;
  }

  public void setFlags(List<String> flags) {
    this.flags = flags;
  }
}
