package eu.h2020.symbiote.security;

import eu.h2020.symbiote.security.amqp.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.security.amqp.platform.home.PlatformAAMMessageHandler;
import eu.h2020.symbiote.security.certificate.CertificateValidator;
import eu.h2020.symbiote.security.certificate.CertificateVerificationException;
import eu.h2020.symbiote.security.certificate.ECDSAHelper;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.exceptions.aam.TokenValidationException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.session.SessionInformation;
import eu.h2020.symbiote.security.token.Token;
import eu.h2020.symbiote.security.token.TokenHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class exposing the library from security handler
 *
 * @author Elena Garrido (Atos)
 * @author Mikołaj Dobski (PSNC)
 * @version 08/03/2017
 *          ! \class SecurityHandler
 *          \brief This class implement the methods to be used by the component in order to integrate with the
 *          security from symbIoTe
 **/

public class SecurityHandler {
    private static Log log = LogFactory.getLog(SecurityHandler.class);
    private PlatformAAMMessageHandler platformMessageHandler = null;
    private CoreAAMMessageHandler coreMessageHandler = null;
    private SessionInformation sessionInformation = null;
    private TokenHandler tokenHandler = null;
    private CertificateValidator certificateValidator = null;

    /**
     * Initializes the Security Handler for 3rd Party applications
     *
     * @param symbioteCoreInterfaceAddress used to access exposed Core AAM services
     */
    public SecurityHandler(String symbioteCoreInterfaceAddress) {
        ECDSAHelper.enableECDSAProvider();
        this.coreMessageHandler = new CoreAAMMessageHandler(symbioteCoreInterfaceAddress);
        this.sessionInformation = new SessionInformation();
        this.tokenHandler = new TokenHandler(this.coreMessageHandler);
        this.certificateValidator = new CertificateValidator(this.coreMessageHandler);
    }

    /**
     * Initializes the Security Handler for platform components
     *
     * @param symbioteCoreInterfaceAddress used to access exposed Core AAM services
     * @param rabbitMQHostIP               used to access platform AAM over AMQP
     * @param rabbitMQUsername             used to access platform AAM over AMQP
     * @param rabbitMQPassword             used to access platform AAM over AMQP
     */
    public SecurityHandler(String symbioteCoreInterfaceAddress, String rabbitMQHostIP, String rabbitMQUsername, String
            rabbitMQPassword) {
        this(symbioteCoreInterfaceAddress);
        this.platformMessageHandler = new PlatformAAMMessageHandler(rabbitMQHostIP, rabbitMQUsername,
                rabbitMQPassword);
    }

    /**
     * Request core token using one's Symbiote Core Account
     * <p>
     * TODO R3 rework/add new method so that user can actually request Home Token from any AAM that is his home AAM.
     *
     * @param userName username in Symbiote Core
     * @param password password in Symbiote Core
     * @return Token issued for your user in Symbiote Core
     */
    public Token requestCoreToken(String userName, String password) {
        Token coreToken = sessionInformation.getCoreToken();
        if (coreToken == null) {
            //not logged in
            Credentials credentials = new Credentials();
            credentials.setUsername(userName);
            credentials.setPassword(password);
            coreToken = coreMessageHandler.login(credentials);
            sessionInformation.setCoreToken(coreToken);
            if (sessionInformation.getCoreToken() == null) {
                String message = "It was not possible to validate you with the give credentials. Please " +
                        "check them";
                log.error(message);
                throw new SecurityException(message);
            }
        }
        return coreToken;
    }

    /**
     * Request federated core token using your home platform token
     *
     * @param userName home platform username
     * @param password home platform password
     * @return Token issued according to you Home Platform and Core Federation
     * @apiNote JUST FOR INTERNAL PLATFORM USAGE
     */
    public Token requestFederatedCoreToken(String userName, String password) throws SecurityHandlerException {
        if (platformMessageHandler == null) {
            throw new SecurityHandlerException("Security Handler wasn't configured to access Platform AAM over AMQP, " +
                    "use the 4 parameters constructor for that");
        }
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
                String message = "It was not possible to validate you with the give credentials. " +
                        "Please " +
                        "check them";
                log.error(message);
                throw new SecurityException(message);
            }

        }

        try {
            tokenHandler.validateCoreToken(coreToken);
        } catch (TokenValidationException e) {
            log.error(e);
            throw new SecurityException(e);
        }
        return coreToken;
    }

    /**
     * Requests federated Platform tokens using acquired Core token.
     * TODO R3 review and update
     *
     * @param aamUrls
     * @return
     */
    public Map<String, Token> requestForeignTokens(List<String> aamUrls) {
        HashMap<String, Token> foreignTokens = null;
        Token coreToken = sessionInformation.getCoreToken();
        if (coreToken != null) {
            //logged in
            foreignTokens = new HashMap<>();
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

    /**
     * Clears the token wallet (home and core)
     */
    public void logout() {
        sessionInformation.setHomeToken(null);
        sessionInformation.setCoreToken(null);
    }

    /**
     * @return home token from the local token wallet
     */
    public Token getHomeToken() {
        return sessionInformation.getHomeToken();
    }

    /**
     * @return core token from the local token wallet
     */
    public Token getCoreToken() {
        return sessionInformation.getCoreToken();
    }

    /**
     * Validates the certificate used by the user in challenge-response operations against the exposed Core AAM root
     * CA certificate
     *
     * @param p12Certificate the local certificate store (either issued by Platform or Core AAM)
     * @return true if valid
     * @throws CertificateVerificationException on validation error
     */
    public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException {
        return certificateValidator.validate(p12Certificate);
    }

    /**
     * Validates the given token against the exposed Core AAM certificate
     * <p>
     * TODO R3 rework to return {@link eu.h2020.symbiote.security.enums.TokenValidationStatus}
     *
     * @param token to be validated
     * @throws TokenValidationException on error
     */
    public void verifyCoreToken(Token token) throws TokenValidationException {
        tokenHandler.validateCoreToken(token);
    }

    /**
     * Validates the given token against the exposed relevant AAM certificate
     * <p>
     * TODO R3 rework to return {@link eu.h2020.symbiote.security.enums.TokenValidationStatus}
     *
     * @param platformInterworkingInterfaceAddress the address where one can access the exposed Platform AAM interfaces
     * @param token                                to be validated
     * @throws TokenValidationException on error
     */
    public void verifyPlatformToken(String platformInterworkingInterfaceAddress, Token token) throws
            TokenValidationException {
        tokenHandler.validateForeignPlatformToken(platformInterworkingInterfaceAddress, token);
    }
}
