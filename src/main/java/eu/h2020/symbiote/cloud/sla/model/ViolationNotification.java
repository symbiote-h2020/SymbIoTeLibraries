/**
 * Copyright 2017 Atos
 * Contact: Atos <jose.sanchezm@atos.net>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package eu.h2020.symbiote.cloud.sla.model;

import java.util.ArrayList;
import java.util.List;

public class ViolationNotification {
  
  private String federationId;
  
  private List<Violation> violations = new ArrayList<>();
  
  public String getFederationId() {
    return federationId;
  }
  
  public void setFederationId(String federationId) {
    this.federationId = federationId;
  }
  
  public List<Violation> getViolations() {
    return violations;
  }
  
  public void setViolations(List<Violation> violations) {
    this.violations = violations;
  }
}
