package eu.h2020.symbiote.security.constants;

/**
 * As in AAM Configuration
 * https://github.com/symbiote-h2020/CoreConfigProperties/blob/master/application.properties
 */
public class SecurityHandlerConstants {
    public static final String EXCHANGE_NAME = "symbIoTe.AuthenticationAuthorizationManager";
    public static final String HOME_PLATFORM_AAM_LOGIN_QUEUE =
            "symbIoTe-AuthenticationAuthorizationManager-login_request";
    public static final String HOME_PLATFORM_AAM_LOGIN_ROUTING_KEY = "symbIoTe.AuthenticationAuthorizationManager" +
            ".login_request";

    public static final String GET_CORE_AAM_CA_CERTIFICATE= "/get_ca_cert";
    public static final String DO_CORE_AAM_LOGIN="/login";
    public static final String DO_CORE_AAM_CHECK_TOKEN_REVOCATION = "/check_home_token_revocation";
    public static final String DO_REQUEST_CORE_TOKEN="/request_core_token";
    public static final String DO_REQUEST_FOREIGN_TOKEN = "/request_foreign_token";
}

