package eu.h2020.symbiote.core.cci;

/**
 * Basic payload content for Responses used in core communication with Security content attached.
 * For Non-Secured version of payload look at {@link AbstractResponse} implementations.
 *
 * @param <T> Type of class used in payload.
 * Created by mateuszl on 09.08.2017.
 */
public abstract class AbstractResponseSecured<T> extends AbstractResponse<T> implements IServiceResponseContent {

    private String serviceResponse;

    public AbstractResponseSecured() {
    }

    public AbstractResponseSecured(int status, String message, T body) {
        super(status, message, body);
    }

    @Override
    public String getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(String serviceResponse) {
        this.serviceResponse = serviceResponse;
    }
}
