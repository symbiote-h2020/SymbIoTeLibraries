package eu.h2020.symbiote.security.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.h2020.symbiote.security.constants.AAMConstants;
import io.jsonwebtoken.Claims;
import org.springframework.data.annotation.Id;

/**
 * Class that defines the SymbIoTe JWS token
 *
 * @author Daniele Caldarola (CNIT)
 * @author Mikołaj Dobski (PSNC)
 * @author Elena Garrido (ATOS)
 * @author Nemanja Ignjatov (UNIVIE)
 */
public class Token {

    public final static String JWT_CLAIMS_TTYPE = AAMConstants.CLAIM_NAME_TOKEN_TYPE;

    @Id
    private String token; //encrypted
    private Claims claims;

    public Token(String token) {
        this.token = token;
    }

    public Token() {
        // used by JPA
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
