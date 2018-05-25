package eu.h2020.symbiote.core.cci;

/**
 * Class extended by Requests payload models. Consists of Business content of type @param
 * For Secured version of payload look at {@link AbstractRequestSecured} implementations.
 * <p>
 * @param <T> Type of class used in payload.
 * Created by mateuszl on 30.08.2017.
 */
public class AbstractRequest<T> {

    private T body;

    /**
     * @param body Business content of payload in given Type.
     */
    public AbstractRequest(T body) {
        this.body = body;
    }

    public AbstractRequest() {
        // Needed for Jackson serialization
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}