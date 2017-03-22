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
	  logger.debug("invoked get token public");
	  String pemFile =
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

