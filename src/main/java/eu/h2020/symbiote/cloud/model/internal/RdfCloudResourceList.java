package eu.h2020.symbiote.cloud.model.internal;

import eu.h2020.symbiote.core.internal.RDFInfo;

import java.util.HashMap;
import java.util.Map;

public class RdfCloudResourceList {
  
  private Map<String, CloudResource> idMappings = new HashMap<>();
  private RDFInfo rdfInfo;
  
  public Map<String, CloudResource> getIdMappings() {
    return idMappings;
  }
  
  public void setIdMappings(Map<String, CloudResource> idMappings) {
    this.idMappings = idMappings;
  }
  
  public RDFInfo getRdfInfo() {
    return rdfInfo;
  }
  
  public void setRdfInfo(RDFInfo rdfInfo) {
    this.rdfInfo = rdfInfo;
  }
}
