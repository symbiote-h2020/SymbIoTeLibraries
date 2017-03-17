package eu.h2020.symbiote;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/*
 * @author: Elena Garrido
 * @version: 12/02/2017
 */
@RestController
@WebAppConfiguration
public class CoreAAMDummyServer {
  private static final Log logger = LogFactory.getLog(CoreAAMDummyServer.class);
  
  @RequestMapping(method = RequestMethod.GET, path = "/rootCertificate")
  public byte[] getRootCertificate() {
	  String pemFile =
			  "-----BEGIN CERTIFICATE-----\n"+
			  "MIIDojCCAoqgAwIBAgIEMKX1dzANBgkqhkiG9w0BAQUFADCBiTELMAkGA1UEBhMC\n"+
			  "R0IxETAPBgNVBAgTCFNjb3RsYW5kMRAwDgYDVQQHEwdHbGFzZ293MRkwFwYDVQQK\n"+
			  "ExBTcHJpbmcgRnJhbWV3b3JrMRgwFgYDVQQLEw9TcHJpbmcgU2VjdXJpdHkxIDAe\n"+
			  "BgNVBAMTF1NwcmluZyBTZWN1cml0eSBUZXN0IENBMB4XDTA4MDEyNTExMTIyMVoX\n"+
			  "DTE4MDIyNTAwMDAwMFowgYkxCzAJBgNVBAYTAkdCMREwDwYDVQQIEwhTY290bGFu\n"+
			  "ZDEQMA4GA1UEBxMHR2xhc2dvdzEZMBcGA1UEChMQU3ByaW5nIEZyYW1ld29yazEY\n"+
			  "MBYGA1UECxMPU3ByaW5nIFNlY3VyaXR5MSAwHgYDVQQDExdTcHJpbmcgU2VjdXJp\n"+
			  "dHkgVGVzdCBDQTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALzl/wEe\n"+
			  "snYrwqaGZuB8hmwACtptazh1+eXCfd66FkioxlLF7yTnjCC7DT+vmMgSuThIEIsN\n"+
			  "xlxLpEgyU3bU8GIuR8wyYIyvuSMcptdFJLV7NKYuRycxpDuqimTM7Br0nfNgKVEv\n"+
			  "1QwguGWr6YN3aZ68/xe/D5xyPhakKu++7VFXIXw9f0+nqojdrFTqQ6l9GAVRgfX6\n"+
			  "h4JOaV1VFx83y2pnFj0iFneVxRcvXyWnyXlcOvJDIyVuyS/hYxb+E5rtBvp5XQ0o\n"+
			  "5CP4OMwCZGx/jEqlL8oO7BwEgu9aEBxKvoIKJmHDTHgWIxgawTrKabmong4utnMI\n"+
			  "yNrhsI77bmh2U7UCAwEAAaMQMA4wDAYDVR0PBAUDAwcGADANBgkqhkiG9w0BAQUF\n"+
			  "AAOCAQEAuD8W9Ukkfyi0y65mwguFVAqBC3RSTMRXcjbLQV4rMDM/Q9kjA6acY4Ta\n"+
			  "WgxGTwNCydqaqwDVsmn+6Je8Lp2xm9KLDLypVdNopGs+Mlfo55dhwqymXkQw1oJI\n"+
			  "CPhR3nBmGEnSWW0UY9bPlpxRF2D5GDVwpuxDtXvWa4baPwRRI9MxwPWHA3ITl+fc\n"+
			  "s9QVKy+pRAnuP9MSIp755cJ1CODOn2ElNCqnxxsZmcWcmI3LkHAwTmegl3PVvhrk\n"+
			  "MKMEA/neshh/M/hWGNTFt77Hoa7pU9dv5RCWFvZPqsUgPrwGrmUvcmSDir3lSWQm\n"+
			  "SuSED2LKVo+BFqwWS+jp49AR9b8B/Q==\n"+
			  "-----END CERTIFICATE-----";
	  logger.debug("invoked get root certificate");
	  return pemFile.getBytes();
  }

}

