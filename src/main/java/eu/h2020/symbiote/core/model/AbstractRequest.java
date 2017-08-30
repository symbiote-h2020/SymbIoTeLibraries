package eu.h2020.symbiote.core.model;

/**
 * Created by mateuszl on 30.08.2017.
 */
abstract public class AbstractRequest<T> {

    private String token;
    private T body;

    public AbstractRequest(String token, T body) {
        this.token = token;
        this.body = body;
    }

    public AbstractRequest() {
        // Needed for Jackson serialization
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
