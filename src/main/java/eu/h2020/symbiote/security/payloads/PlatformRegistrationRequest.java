package eu.h2020.symbiote.security.payloads;

/**
 * Describes platform registration in AAM payload.
 *
 * @author Mikołaj Dobski (PSNC)
 * @author Maksymilian Marcinowski (PSNC)
 */
public class PlatformRegistrationRequest {
    private Credentials AAMOwnerCredentials = new Credentials();
    private UserDetails platformOwnerDetails = new UserDetails();
    private String platformAAMURL = "";
    private String platformInstanceId = "";
    private String platformInstanceFriendlyName;


    public PlatformRegistrationRequest() {
        // required for serialization
    }

    /**
     * For use when a Platform Owner is fine with generated platform identifier
     *
     * @param AAMOwnerCredentials          used to authorize this request
     * @param platformOwnerDetails         used to register the platform owner in the database
     * @param platformAAMURL               used to point symbiote users to possible login entrypoints
     * @param platformInstanceFriendlyName a label for the end user to be able to identify the login endrypoint
     */
    public PlatformRegistrationRequest(Credentials AAMOwnerCredentials, UserDetails platformOwnerDetails, String
            platformAAMURL, String platformInstanceFriendlyName) {
        this.AAMOwnerCredentials = AAMOwnerCredentials;
        this.platformOwnerDetails = platformOwnerDetails;
        this.platformAAMURL = platformAAMURL;
        this.platformInstanceFriendlyName = platformInstanceFriendlyName;
    }

    /**
     * For use when a Platform Owner wants a preferred platform identifier
     * * @param AAMOwnerCredentials used to authorize this request
     *
     * @param platformOwnerDetails         used to register the platform owner in the database
     * @param platformAAMURL               used to point symbiote users to possible login entrypoints
     * @param platformInstanceFriendlyName a label for the end user to be able to identify the login endrypoint
     * @param preferredPlatformInstanceID  when a Platform Owner preferres his own platform identifier
     */
    public PlatformRegistrationRequest(Credentials AAMOwnerCredentials, UserDetails platformOwnerDetails, String
            platformAAMURL, String platformInstanceFriendlyName, String preferredPlatformInstanceID) {
        this.AAMOwnerCredentials = AAMOwnerCredentials;
        this.platformInstanceFriendlyName = platformInstanceFriendlyName;
        this.platformInstanceId = preferredPlatformInstanceID;
        this.platformAAMURL = platformAAMURL;
        this.platformOwnerDetails = platformOwnerDetails;
    }

    public UserDetails getPlatformOwnerDetails() {
        return platformOwnerDetails;
    }

    public void setPlatformOwnerDetails(UserDetails platformOwnerDetails) {
        this.platformOwnerDetails = platformOwnerDetails;
    }

    public String getPlatformInstanceId() {
        return platformInstanceId;
    }

    public void setPlatformInstanceId(String platformInstanceId) {
        this.platformInstanceId = platformInstanceId;
    }

    public String getPlatformAAMURL() {
        return platformAAMURL;
    }

    public void setPlatformAAMURL(String platformAAMURL) {
        this.platformAAMURL = platformAAMURL;
    }

    public Credentials getAAMOwnerCredentials() {
        return AAMOwnerCredentials;
    }

    public void setAAMOwnerCredentials(Credentials AAMOwnerCredentials) {
        this.AAMOwnerCredentials = AAMOwnerCredentials;
    }

    public String getPlatformInstanceFriendlyName() {
        return platformInstanceFriendlyName;
    }

    public void setPlatformInstanceFriendlyName(String platformInstanceFriendlyName) {
        this.platformInstanceFriendlyName = platformInstanceFriendlyName;
    }
}