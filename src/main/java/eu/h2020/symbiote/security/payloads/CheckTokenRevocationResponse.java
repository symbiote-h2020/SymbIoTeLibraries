package eu.h2020.symbiote.security.payloads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.security.enums.TokenValidationStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that defines the status of the checked token sent for revocation to
 * CloudAAM as part of a request.
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class CheckTokenRevocationResponse {

    private static Log log = LogFactory.getLog(CheckTokenRevocationResponse.class);

    private String status;

    public CheckTokenRevocationResponse() {
        this.status = null;
    }

    public CheckTokenRevocationResponse(TokenValidationStatus token) {
        this.status = token.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatus(TokenValidationStatus tokenValidationStatus) {
        this.status = tokenValidationStatus.toString();
    }

    public String toJson() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public String toString() {
        return "CheckTokenRevocationResponse [status=" + status + "]";
    }

}
