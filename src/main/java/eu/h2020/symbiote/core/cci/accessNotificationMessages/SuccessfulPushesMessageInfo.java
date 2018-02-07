/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.cci.accessNotificationMessages;

import java.util.Date;
import java.util.List;

/**
 *
 * @author <a href="mailto:l.tomaselli@nextworks.it">Luca Tomaselli</a>
 */
public class SuccessfulPushesMessageInfo extends MessageInfo{

    public SuccessfulPushesMessageInfo() {
        // empty constructor
    }

    public SuccessfulPushesMessageInfo(String symbIoTeId, List<Date> timestamps) {
        this.symbIoTeId = symbIoTeId;
        this.timestamps = timestamps;
    }
    
}
