package eu.h2020.symbiote.security.exceptions.aam;

import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.exceptions.AAMException;
import org.springframework.http.HttpStatus;

/**
 * Custom exception thrown when user registration over AMQP fails
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class UserRegistrationException extends AAMException {

    public final static String errorMessage = "USER_REGISTRATION_ERROR";
    public final static HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    private static final long serialVersionUID = AAMConstants.serialVersionUID;

    public UserRegistrationException() {
        super(errorMessage);
    }

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(Throwable cause) {
        super(cause);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
