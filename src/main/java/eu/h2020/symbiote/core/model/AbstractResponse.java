package eu.h2020.symbiote.core.model;

/**
 * Created by mateuszl on 09.08.2017.
 */
public abstract class AbstractResponse<T> {

    private int status;
    private String message;
    private T body;

    public AbstractResponse() {
        // Needed for Jackson serialization
    }

    public AbstractResponse(int status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
