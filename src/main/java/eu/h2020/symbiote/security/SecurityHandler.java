package eu.h2020.symbiote.security;

import eu.h2020.symbiote.security.amqp.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.security.amqp.platform.home.PlatformAAMMessageHandler;
import eu.h2020.symbiote.security.certificate.CertificateValidator;
import eu.h2020.symbiote.security.certificate.CertificateVerificationException;
import eu.h2020.symbiote.security.certificate.ECDSAHelper;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.exceptions.aam.TokenValidationException;
import eu.h2020.symbiote.security.exceptions.sh.SecurityHandlerDisabledException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.session.SessionInformation;
import eu.h2020.symbiote.security.token.Token;
import eu.h2020.symbiote.security.token.TokenHandler;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;

/**
 * Class exposing the library from security handler
 *
 * @author Elena Garrido
 * @version 08/03/2017
 *          ! \class SecurityHandler
 *          \brief This class implement the methods to be used by the component in order to integrate with the
 *          security from symbIoTe
 **/

public class SecurityHandler {
    private PlatformAAMMessageHandler platformMessageHandler;
    private CoreAAMMessageHandler coreMessageHandler;
    private SessionInformation sessionInformation;
    private TokenHandler tokenHandler;
    private CertificateValidator certificateValidator;
    private boolean enabled;

    public SecurityHandler(String coreAAMUrl, String rabbitMQHostIP, boolean enabled) {
        ECDSAHelper.enableECDSAProvider();
        this.enabled = enabled;

        if (this.enabled) {
            this.platformMessageHandler = new PlatformAAMMessageHandler(rabbitMQHostIP);
            this.coreMessageHandler = new CoreAAMMessageHandler(coreAAMUrl);
            this.sessionInformation = new SessionInformation();
            this.tokenHandler = new TokenHandler(this.coreMessageHandler);
            this.certificateValidator = new CertificateValidator(this.coreMessageHandler);
        }
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Token appRequestCoreToken(String userName, String password) throws SecurityException,
            SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        Token coreToken = sessionInformation.getCoreToken();
        if (coreToken == null) {
            //not logged in
            Credentials credentials = new Credentials();
            credentials.setUsername(userName);
            credentials.setPassword(password);
            coreToken = coreMessageHandler.login(credentials);
            sessionInformation.setCoreToken(coreToken);
            if (sessionInformation.getCoreToken() == null) {
                throw new SecurityException("It was not possible to vaildate you with the give credentials. Please " +
                        "check them");
            }
        }
        return coreToken;
    }

    public Token requestCoreToken(String userName, String password) throws SecurityException,
            SecurityHandlerException, TokenValidationException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        Token coreToken = sessionInformation.getCoreToken();
        if (coreToken == null) {
            //not logged in
            Credentials credentials = new Credentials();
            credentials.setUsername(userName);
            credentials.setPassword(password);
            Token homeToken = platformMessageHandler.login(credentials);
            //TODO challenge response procedure??
            coreToken = tokenHandler.requestCoreToken(homeToken);
            sessionInformation.setHomeToken(homeToken);
            sessionInformation.setCoreToken(coreToken);
            if (sessionInformation.getHomeToken() == null) {
                throw new SecurityException("It was not possible to vaildate you with the give credentials. Please " +
                        "check them");
            }

        }
        tokenHandler.validateCoreToken(coreToken);
        return coreToken;
    }


    public HashMap<String, Token> requestForeignTokens(List<String> aamUrls) throws SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        HashMap<String, Token> foreignTokens = null;
        Token coreToken = sessionInformation.getCoreToken();
        if (coreToken != null) {
            //logged in
            foreignTokens = new HashMap<String, Token>();
            for (String url : aamUrls) {
                Token foreignToken = sessionInformation.getForeignToken(url);
                if (foreignToken == null) {
                    foreignToken = tokenHandler.requestForeignToken(url, coreToken);
                    sessionInformation.setForeignToken(url, foreignToken);
                }
                foreignTokens.put(url, foreignToken);
            }
        }
        return foreignTokens;
    }

    public void logout() throws SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        sessionInformation.setHomeToken(null);
        sessionInformation.setCoreToken(null);
    }

    public Token getHomeToken() throws SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        return sessionInformation.getHomeToken();
    }

    public Token getCoreToken() throws SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        return sessionInformation.getCoreToken();
    }

    public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException,
            SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        return certificateValidator.validate(p12Certificate);
    }


    public Token verifyCoreToken(String encodedTokenString) throws TokenValidationException,
            SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        Token token = new Token(encodedTokenString);
        verifyCoreToken(token);
        return token;
    }

    public void verifyCoreToken(Token token) throws TokenValidationException, SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        tokenHandler.validateCoreToken(token);
    }

    public Token verifyForeignPlatformToken(String aamURL, String encodedTokenString) throws
            TokenValidationException, SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        Token token = new Token(encodedTokenString);
        verifyForeignPlatformToken(aamURL, token);
        return token;
    }

    public void verifyForeignPlatformToken(String aamURL, Token token) throws TokenValidationException,
            SecurityHandlerDisabledException {
        if (!enabled)
            throw new SecurityHandlerDisabledException("Security Handler is disabled!");

        tokenHandler.validateForeignPlatformToken(aamURL, token);
    }

}
