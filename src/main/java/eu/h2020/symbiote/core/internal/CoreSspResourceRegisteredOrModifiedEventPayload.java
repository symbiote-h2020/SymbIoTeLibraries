package eu.h2020.symbiote.core.internal;

/**
 * Extension of the {@link CoreResourceRegisteredOrModifiedEventPayload} for the Ssp resources.
 * {@link CoreResourceRegisteredOrModifiedEventPayload#platformId} is identifying symbIoTe cloud entity, so it should
 * be equal to ssp's id.
 * The sdev whose resources are being registered is represented by sdevId;
 *
 * Created by Szymon Mueller on 18/04/2017.
 */
public class CoreSspResourceRegisteredOrModifiedEventPayload extends CoreResourceRegisteredOrModifiedEventPayload {

    private String sdevId;

    public CoreSspResourceRegisteredOrModifiedEventPayload() {
    }

    public String getSdevId() {
        return sdevId;
    }

    public void setSdevId(String sdevId) {
        this.sdevId = sdevId;
    }
}
