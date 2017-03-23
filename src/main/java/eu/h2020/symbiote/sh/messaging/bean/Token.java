package eu.h2020.symbiote.sh.messaging.bean;

public class Token {
	String token; //encrypted
	
	public Token(){
	}

	public Token(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
