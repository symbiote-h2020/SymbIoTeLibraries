package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Abstract class extended by payload models. Consists of Security Request object and Business content.
 * For non-secured version of payload look at {@link AbstractRequest} implementations.
 * <p>
 *
 * @param <T> Type of class used in payload.
 *            Created by mateuszl on 04.09.2017.
 */
public abstract class AbstractRequestSecured<T> extends AbstractRequest<T> implements ISecurityRequestContent {

    private SecurityRequest securityRequest;

    /**
     * @param securityRequest Symbiote Security compiliant {@link SecurityRequest} object.
     * @param body            Business content of payload in given Type.
     */
    public AbstractRequestSecured(SecurityRequest securityRequest, T body) {
        this.securityRequest = securityRequest;
        super.setBody(body);
    }

    public AbstractRequestSecured() {
        // Needed for Jackson serialization
    }

    public T getBody() {
        return super.getBody();
    }

    public void setBody(T body) {
        super.setBody(body);
    }

    public SecurityRequest getSecurityRequest() {
        return securityRequest;
    }

    public void setSecurityRequest(SecurityRequest securityRequest) {
        this.securityRequest = securityRequest;
    }
}
