package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/** Interface used in Requests payloads that are supposed to have SecurityRequest object in content.
 *
 * Created by mateuszl on 04.09.2017.
 */
public interface ISecurityRequestContent {

    SecurityRequest getSecurityRequest();
}
