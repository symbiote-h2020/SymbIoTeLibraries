package eu.h2020.symbiote.security.commons.exceptions;

import eu.h2020.symbiote.security.commons.Constants;
import eu.h2020.symbiote.security.commons.CustomAAMException;
import org.springframework.http.HttpStatus;

/**
 * Custom exception thrown when user credentials are already present in DB during registration procedure
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class ExistingUserException extends CustomAAMException {

    public final static String errorMessage = "USER_ALREADY_REGISTERED";
    public final static HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    private static final long serialVersionUID = Constants.serialVersionUID;

    public ExistingUserException() {
        super(errorMessage);
    }

    public ExistingUserException(String message) {
        super(message);
    }

    public ExistingUserException(Throwable cause) {
        super(cause);
    }

    public ExistingUserException(String message, Throwable cause) {
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
