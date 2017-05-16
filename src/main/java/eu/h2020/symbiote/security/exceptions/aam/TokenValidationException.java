package eu.h2020.symbiote.security.exceptions.aam;

import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.exceptions.AAMException;
import org.springframework.http.HttpStatus;

/**
 * Custom exception thrown when JWT token creation fails
 *
 * TODO R3 rename for Validation Exception
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class TokenValidationException extends AAMException {

    private static final long serialVersionUID = AAMConstants.serialVersionUID;
    private final static String errorMessage = "INVALID_TOKEN";
    private final static HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public TokenValidationException() {
        super(errorMessage);
    }

    public TokenValidationException(String message) {
        super(message);
    }

    public TokenValidationException(Throwable cause) {
        super(cause);
    }

    public TokenValidationException(String message, Throwable cause) {
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