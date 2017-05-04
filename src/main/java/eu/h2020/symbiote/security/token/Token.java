package eu.h2020.symbiote.security.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.enums.IssuingAuthorityType;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static Log log = LogFactory.getLog(Token.class);
    @Id
    private String id = "";
    private String token = "";
    private IssuingAuthorityType type = IssuingAuthorityType.NULL;
    private Claims claims;

    public Token() {
        // used by JPA
    }

    public Token(String token) {
        this.token = token;
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
        this.setType(IssuingAuthorityType.valueOf((String) claims.get(AAMConstants.CLAIM_NAME_TOKEN_TYPE)));
    }

    /**
     * stored in JWT_CLAIMS_TTYPE attribute
     *
     * @return the type of this token stating if it was issued by Core or a Platform
     */
    public IssuingAuthorityType getType() {
        return type;
    }

    public void setType(IssuingAuthorityType type) {
        this.type = type;
    }
}
