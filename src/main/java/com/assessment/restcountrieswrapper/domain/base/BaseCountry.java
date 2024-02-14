package com.assessment.restcountrieswrapper.domain.base;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class BaseCountry {

  private Name name;
  private List<String> tld;
  private String cca2;
  private String ccn3;
  private String cca3;
  private String cioc;
  private Boolean independent;
  private String status;
  private Boolean unMember;
  private Map<String, Currency> currencies;
  private Idd idd;
  private List<String> capital;
  private List<String> altSpellings;
  private String region;
  private String subregion;
  private Map<String, String> languages;
  private Map<String, Map<String, String>> translations;
  private List<Double> latlng;
  private Boolean landlocked;
  private List<String> borders;
  private Double area;
  private Map<String, Map<String, String>> demonyms;
  private List<String> callingCodes;
  private String flag;
  private Map<String, String> maps;
  private Integer population;
  private Map<String, Double> gini;
  private String fifa;
  private Car car;
  private List<String> timezones;
  private List<String> continents;

  @Override
  public String toString() {
    return "Country{" + "\n" +
        "NativeName=" + name.getNativeName() + "\n" +
        "Common=" + name.getCommon() + "\n" +
        "Official=" + name.getOfficial() + "\n" +
        ", tld=" + tld + "\n" +
        ", cca2='" + cca2 + '\'' + "\n" +
        ", ccn3='" + ccn3 + '\'' + "\n" +
        ", cioc='" + cioc + '\'' + "\n" +
        ", independent=" + independent + "\n" +
        ", status='" + status + '\'' + "\n" +
        ", unMember=" + unMember + "\n" +
        ", currencies=" + currencies + "\n" +
        ", idd=" + idd + "\n" +
        ", capital=" + capital + "\n" +
        ", altSpelling=" + altSpellings + "\n" +
        ", region='" + region + '\'' + "\n" +
        ", subregion='" + subregion + '\'' + "\n" +
        ", language=" + languages + "\n" +
        ", latlng=" + latlng + "\n" +
        ", landlocked=" + landlocked + "\n" +
        ", borders=" + borders + "\n" +
        ", area=" + area + "\n" +
        ", demonyms=" + demonyms + "\n" +
        '}';
  }

  public String getCca3() {
    return cca3;
  }

  public void setCca3(String cca3) {
    this.cca3 = cca3;
  }

  public Map<String, Map<String, String>> getTranslations() {
    return translations;
  }

  public void setTranslations(Map<String, Map<String, String>> translations) {
    this.translations = translations;
  }

  public List<String> getCallingCodes() {
    return callingCodes;
  }

  public void setCallingCodes(List<String> callingCodes) {
    this.callingCodes = callingCodes;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public Map<String, String> getMaps() {
    return maps;
  }

  public void setMaps(Map<String, String> maps) {
    this.maps = maps;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  public Map<String, Double> getGini() {
    return gini;
  }

  public void setGini(Map<String, Double> gini) {
    this.gini = gini;
  }

  public String getFifa() {
    return fifa;
  }

  public void setFifa(String fifa) {
    this.fifa = fifa;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public List<String> getTimezones() {
    return timezones;
  }

  public void setTimezones(List<String> timezones) {
    this.timezones = timezones;
  }

  public List<String> getContinents() {
    return continents;
  }

  public void setContinents(List<String> continents) {
    this.continents = continents;
  }
}
