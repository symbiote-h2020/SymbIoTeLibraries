package eu.h2020.symbiote.security.exceptions.aam;

import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.exceptions.AAMException;
import org.springframework.http.HttpStatus;

/**
 * Custom exception thrown when JWT token creation fails
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class JWTCreationException extends AAMException {

    private static final long serialVersionUID = AAMConstants.serialVersionUID;
    private final static String errorMessage = "UNABLE_CREATE_JWT_TOKEN";
    private final static HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public JWTCreationException() {
        super(errorMessage);
    }

    public JWTCreationException(String message) {
        super(message);
    }

    public JWTCreationException(Throwable cause) {
        super(cause);
    }

    public JWTCreationException(String message, Throwable cause) {
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