package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.http.HttpStatus;

/**
 * Response of the service and actuator execution task requests. Contains HttpStatus of the communication between
 * Platform Proxy component and platform's RAP plugin for accessing service/actuator as well as output message returned
 * from the platform side (if applicable).
 *
 * Created by Szymon Mueller on 05/04/2018.
 */
public class ServiceExecutionTaskResponse {

    private HttpStatus status;

    private String output;


    @JsonCreator
    @PersistenceConstructor
    public ServiceExecutionTaskResponse( @JsonProperty("status") HttpStatus status,
                                         @JsonProperty("output") String output) {
        this.status = status;
        this.output = output;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceExecutionTaskResponse that = (ServiceExecutionTaskResponse) o;

        if (status != that.status) return false;
        return output != null ? output.equals(that.output) : that.output == null;

    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (output != null ? output.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceExecutionTaskResponse{" +
                "status=" + status +
                ", output='" + output + '\'' +
                '}';
    }
}
