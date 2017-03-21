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
  
  
  @RequestMapping(method = RequestMethod.GET, path = "/tokenPublicKey")
  public byte[] getTokenPublicKey() {
	  logger.debug("invoked get token public");
	  String pemFile =
		 "-----BEGIN PUBLIC KEY-----\n"+
		 "  MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuPjU1Qrw9sZWYztUTiC\n"+
		 "psqU8lZpIg414LzGXbK/W7oe3njDoE1P5S1y3nNJgksNzzGNunR84OrEb5A6BND2\n"+
		 "otYfblEBJsFs/ziqC94EatOQTpiy09P8GMtG4fTrImAMQOTK/Qa06K1KvNgUNg9O\n"+
		 "rOlLE7M+sYEaCJE97ftoiQWoCXf9hEPLOv6f1j/Wt5atJ72Ebs3DGOl1EP4JLrxV\n"+
		 "FlVhvdwjv5Z7SQ9AEvkzK7guKggwwYs1zzjxb9iexvDzHNq/td1NL5i7ULhDQg+c\n"+
		 "WjqDYwRk2Qb2sscVcn+8q8fLV3xD+q5FZSFDNG8odmClbk90tYpVadzMfSKLBFBl\n"+
		 "cwIDAQAB\n"+
		 "-----END PUBLIC KEY-----\n"+
		 "-----BEGIN CERTIFICATE-----\n"+
		 "MIIDczCCAlugAwIBAgIEXPARpzANBgkqhkiG9w0BAQsFADBqMQswCQYDVQQGEwJF\n"+
		 "UzESMBAGA1UECBMJQmFyY2Vsb25hMRIwEAYDVQQHEwlCYXJjZWxvbmExDTALBgNV\n"+
		 "BAoTBEF0b3MxDDAKBgNVBAsTA0FSSTEWMBQGA1UEAxMNVGVzdCBTeW1iaW90ZTAe\n"+
		 "Fw0xNzAzMjExMzU4NTVaFw0xNzA2MTkxMzU4NTVaMGoxCzAJBgNVBAYTAkVTMRIw\n"+
		 "EAYDVQQIEwlCYXJjZWxvbmExEjAQBgNVBAcTCUJhcmNlbG9uYTENMAsGA1UEChME\n"+
		 "QXRvczEMMAoGA1UECxMDQVJJMRYwFAYDVQQDEw1UZXN0IFN5bWJpb3RlMIIBIjAN\n"+
		 "BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuPjU1Qrw9sZWYztUTiCpsqU8lZp\n"+
		 "Ig414LzGXbK/W7oe3njDoE1P5S1y3nNJgksNzzGNunR84OrEb5A6BND2otYfblEB\n"+
		 "JsFs/ziqC94EatOQTpiy09P8GMtG4fTrImAMQOTK/Qa06K1KvNgUNg9OrOlLE7M+\n"+
		 "sYEaCJE97ftoiQWoCXf9hEPLOv6f1j/Wt5atJ72Ebs3DGOl1EP4JLrxVFlVhvdwj\n"+
		 "v5Z7SQ9AEvkzK7guKggwwYs1zzjxb9iexvDzHNq/td1NL5i7ULhDQg+cWjqDYwRk\n"+
		 "2Qb2sscVcn+8q8fLV3xD+q5FZSFDNG8odmClbk90tYpVadzMfSKLBFBlcwIDAQAB\n"+
		 "oyEwHzAdBgNVHQ4EFgQUytEsSR/Kckc1UeVCAg86yrJVOCswDQYJKoZIhvcNAQEL\n"+
		 "BQADggEBAFfDTeR3lkWHlSnGWLPCDLAsWIXUTgLGpHao2fsGXR1kxzaYOUlv9HhV\n"+
		 "/bqF5aPsnjKl8JrxZJ1mVUvz7V2qIvmtmmqrqbEh1GF6qhOgmbM/nyx6K/BvESKx\n"+
		 "+XMVjInj5SND1RhrjR2AOzGHnC/BybsPknaGJ7C8gxFnA6qOXolH0+vtZ/d1hNCG\n"+
		 "Hz/cckIa10GYFTJacr4Maj/MLvYOS8VEN19LM1hFxCooKJQH70L5kzCAyI7omWt1\n"+
		 "AzWTKOPAB+BYXbgr/vX316qSVYT6T5pF13DY5lva1mXPOes8pnR53EA5IqvMzKfX\n"+
		 "wTy8cAqkLUUrJoARWuoocn5B9qpZMAE=\n"+
		 "-----END CERTIFICATE-----\n";
	  	logger.debug("invoked get token certificate");
		return pemFile.getBytes();
	  
  }

}

