package eu.h2020.symbiote.sh.messaging.restAAM;

import eu.h2020.symbiote.sh.constants.SHConstants;
import eu.h2020.symbiote.sh.messaging.bean.Credential;
import eu.h2020.symbiote.sh.messaging.bean.Status;
import eu.h2020.symbiote.sh.messaging.bean.Token;
import feign.Headers;
import feign.RequestLine;

public interface AAMRestService {
	@RequestLine("GET "+SHConstants.GET_CORE_AAM_CA_CERTIFICATE)
	@Headers("Accept: multipart/form-data")
    public byte[] getRootCertificate();
	
	@RequestLine("POST "+SHConstants.DO_CORE_AAM_LOGIN)
	@Headers("Content-Type: application/json")
    public Token login(Credential credential);
	
	@RequestLine("POST "+SHConstants.DO_CORE_AAM_CHECK_TOKEN_REVOCATION)
	@Headers({"Content-Type: application/json", "Accept: application/json"})
    public Status checkTokenRevocation(Token token); 
}
