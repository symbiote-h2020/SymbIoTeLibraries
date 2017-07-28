package eu.h2020.symbiote.cloud.model.internal;

import eu.h2020.symbiote.core.model.RDFInfo;

import java.util.HashMap;
import java.util.Map;

public class RdfCloudResorceList {
  
  private Map<String, String> idMappings = new HashMap<>();
  private RDFInfo rdfInfo;
  
  public Map<String, String> getIdMappings() {
    return idMappings;
  }
  
  public void setIdMappings(Map<String, String> idMappings) {
    this.idMappings = idMappings;
  }
  
  public RDFInfo getRdfInfo() {
    return rdfInfo;
  }
  
  public void setRdfInfo(RDFInfo rdfInfo) {
    this.rdfInfo = rdfInfo;
  }
}
