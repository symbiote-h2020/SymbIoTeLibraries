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

import java.util.Date;

public class Violation {
  
  private String constraint;
  
  private String actualValue;
  
  private String deviceId;
  
  private Date date;
  
  public String getConstraint() {
    return constraint;
  }
  
  public void setConstraint(String constraint) {
    this.constraint = constraint;
  }
  
  public String getActualValue() {
    return actualValue;
  }
  
  public void setActualValue(String actualValue) {
    this.actualValue = actualValue;
  }
  
  public String getDeviceId() {
    return deviceId;
  }
  
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
}
