package eu.h2020.symbiote.messaging.core;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

@Service
public class CoreAAMMessageHandler {
	@Value("${symbiote.coreaam.uri}")
	String coreAAMUri;
	CoreAAMRestService client;

	public CoreAAMMessageHandler(){
		
	}
	
	public byte[] getPublicRootCertificate(){
			coreAAMUri = "http://localhost:8080";
			client = Feign.builder()
			//.decoder(new GsonDecoder())
			//.encoder(new GsonEncoder())
	        .target(CoreAAMRestService.class, coreAAMUri);
			return client.getRootCertificate();
	}
	
	  public  PublicKey getPemPublicKey(String filename, String algorithm) throws Exception {
		  byte[]keyBytes = client.getRootCertificate();
	      String temp = new String(keyBytes);
	      String publicKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----\n", "");
	      publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");


	      Base64 b64 = new Base64();
	      byte [] decoded = b64.decode(publicKeyPEM);

	      X509EncodedKeySpec spec =
	            new X509EncodedKeySpec(decoded);
	      KeyFactory kf = KeyFactory.getInstance(algorithm);
	      return kf.generatePublic(spec);
	      }
}
