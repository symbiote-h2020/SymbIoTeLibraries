package eu.h2020.symbiote.security.payloads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.security.certificate.Certificate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that defines the structure of a user registration response sent by AAM.
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 * @author Mikołaj Dobski (PSNC)
 */
public class UserRegistrationResponse {

    private static Log log = LogFactory.getLog(UserRegistrationResponse.class);

    // TODO Release 3 fix to support CertificateSignRequests
    private Certificate userCertificate = new Certificate();
    private String userPrivateKey = "";

    /**
     * required for JSON serialization
     */
    public UserRegistrationResponse() {
        // required by JSON
    }

    public UserRegistrationResponse(Certificate userCertificate, String userPrivateKey) {
        this.userCertificate = userCertificate;
        this.userPrivateKey = userPrivateKey;
    }

    /**
     * @return user certificate to be used for interaction with symbIoTe core components.
     */
    public Certificate getUserCertificate() {
        return userCertificate;
    }

    public void setUserCertificate(Certificate userCertificate) {
        this.userCertificate = userCertificate;
    }

    /**
     * @return in PEM format
     */
    public String getUserPrivateKey() {
        return userPrivateKey;
    }

    public void setUserPrivateKey(String userPrivateKey) {
        this.userPrivateKey = userPrivateKey;
    }

    public String toJson() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Error converting UserRegistrationResponse to JSON", e);
            return null;
        }
    }

}
