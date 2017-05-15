package eu.h2020.symbiote.security.payloads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.security.enums.ValidationStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that defines the status of the checked certificate/token sent for revocation to
 * AAM as part of a request.
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class CheckRevocationResponse {

    private static Log log = LogFactory.getLog(CheckRevocationResponse.class);

    private String status;

    public CheckRevocationResponse() {
        this.status = null;
    }

    public CheckRevocationResponse(ValidationStatus token) {
        this.status = token.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatus(ValidationStatus validationStatus) {
        this.status = validationStatus.toString();
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
        return "CheckRevocationResponse [status=" + status + "]";
    }

}
