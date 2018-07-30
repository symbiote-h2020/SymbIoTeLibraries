package eu.h2020.symbiote.ssp.constants;

/**
 * Created by vasgl on 8/24/2017.
 */
public final class InnkeeperRestControllerConstants {

    public static final String INNKEEPER_BASE_PATH = "/innkeeper";
    public static final String INNKEEPER_REGISTER_REQUEST_PATH = "register";
    public static final String INNKEEPER_JOIN_REQUEST_PATH = "/join";
    public static final String INNKEEPER_SDEV_REGISTER_REQUEST_PATH = "/sdev/"+INNKEEPER_REGISTER_REQUEST_PATH;
    public static final String INNKEEPER_SDEV_JOIN_REQUEST_PATH = "/sdev/"+INNKEEPER_JOIN_REQUEST_PATH;
    public static final String INNKEEPER_PLATFORM_REGISTER_REQUEST_PATH = "/platform/"+INNKEEPER_REGISTER_REQUEST_PATH;
    public static final String INNKEEPER_PLATFORM_JOIN_REQUEST_PATH = "/platform/"+INNKEEPER_JOIN_REQUEST_PATH;

    public static final String INNKEEPER_UNREGISTER_REQUEST_PATH = "/un"+INNKEEPER_REGISTER_REQUEST_PATH;
    public static final String INNKEEPER_SDEV_UNREGISTER_REQUEST_PATH = "/sdev/"+"/un"+INNKEEPER_REGISTER_REQUEST_PATH;
    public static final String INNKEEPER_PLATFORM_UNREGISTER_REQUEST_PATH = "/platform/"+"/un"+INNKEEPER_REGISTER_REQUEST_PATH;

    public static final String INNKEEPER_LIST_RESOURCES_REQUEST_PATH = "/list_resources";
    public static final String INNKEEPER_KEEP_ALIVE_REQUEST_PATH = "/keep_alive";
    public static final String INNKEEPER_PUBLIC_RESOURCES = "/public_resources";
    public static final String INNKEEPER_GET_SSP_INFO = "/ssp";

    public static final String SET_INNK_ONLINE = "/set_innk_online";
    public static final String SET_INNK_OFFLINE = "/set_innk_offline";
    public static final String SANDBOX = "/sandbox";

    public static final String REGISTRATION_OK = "OK";
    public static final String REGISTRATION_ALREADY_REGISTERED = "ALREADY_REGISTERED";
    public static final String REGISTRATION_OFFLINE = "OFFLINE";
    public static final String REGISTRATION_REJECTED = "REJECTED";
    public static final String REGISTRATION_ERROR = "ERROR";

    public static final String REJECTED_SYMID = "REJECTED";
    public static final String OFFLINE_SYMID = "OFFLINE";
    public static final String SDEV = "SDEV";
    public static final String PLATFORM = "PLATFORM";

    private InnkeeperRestControllerConstants() {
    }
}
