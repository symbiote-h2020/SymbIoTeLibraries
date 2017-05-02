package eu.h2020.symbiote.security.token.jwt;

import java.util.HashMap;
import java.util.Map;

/**
 * Placeholder for jwt claims
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 * @author Mikolaj Dobski (PSNC)
 */
public class JWTClaims {

    private String jti;
    private String alg;
    private String iss;
    private String sub;
    private Long iat;
    private Long exp;
    private String ipk;
    private String spk;
    private Map<String, String> att = new HashMap<>();
    private String ttyp;

    public JWTClaims() {
        // used by serializer
    }

    public JWTClaims(Object jti, Object alg, Object iss, Object sub, Object iat, Object exp, Object ipk, Object spk,
                     Object ttyp, Map<String, String> att) {
        this.jti = (String) jti;
        this.alg = (String) alg;
        this.iss = (String) iss;
        this.sub = (String) sub;
        this.iat = new Long((Integer) iat) * 1000;
        this.exp = new Long((Integer) exp) * 1000;
        this.ipk = (String) ipk;
        this.spk = (String) spk;
        this.att = att;
        this.ttyp = (String) ttyp;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public String getSpk() {
        return spk;
    }

    public void setSpk(String spk) {
        this.spk = spk;
    }

    public Map<String, String> getAtt() {
        return att;
    }

    public void setAtt(Map<String, String> att) {
        this.att = att;
    }

    public String getTtyp() {
        return ttyp;
    }

    public void setTtyp(String ttyp) {
        this.ttyp = ttyp;
    }

    @Override
    public String toString() {
        return "JWTClaims{" +
                "jti='" + jti + '\'' +
                ", alg='" + alg + '\'' +
                ", iss='" + iss + '\'' +
                ", sub='" + sub + '\'' +
                ", iat='" + iat + '\'' +
                ", exp='" + exp + '\'' +
                ", ipk='" + ipk + '\'' +
                ", spk='" + spk + '\'' +
                ", att='" + att + '\'' +
                ", ttyp'" + ttyp + '\'' +
                '}';
    }
}
