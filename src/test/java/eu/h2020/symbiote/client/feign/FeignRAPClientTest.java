package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.model.cim.*;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static eu.h2020.symbiote.client.feign.FeignRAPClient.RAPI;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FeignRAPClientTest {

    private FeignRAPClient feignRAPClient;
    private RAPI mockedClient;

    @Before
    public void initialize() throws SecurityHandlerException {

        // Spy on the FeignRAPClient client, because we just need to mock the getClient() method
        this.feignRAPClient = spy(new FeignRAPClient(null, null, null));

        // Mock the RAPI
        this.mockedClient = mock(RAPI.class);

        // Use doReturn syntax, because when().thenReturn() does not work when spying
        doReturn(mockedClient).when(feignRAPClient).getClient(any());
    }

    @Test
    public void getTopObservations() {

        List<Observation> mockedObs = getObservationList();

        // Mock the response of the RAPI
        when(mockedClient.getTopObservations(anyInt(), anyBoolean(), anySet())).thenReturn(mockedObs);

        // The resourceUrl is empty because the resource is mocked anyway
        List<Observation> response = feignRAPClient.getTopObservationsAsGuest("", 2, false);
        assertEquals(mockedObs.size(), response.size());
    }

    private List<Observation> getObservationList() {
        String resourceId = "id";
        return new ArrayList<>(Arrays.asList(
                createObservation(resourceId), createObservation(resourceId)
        ));
    }

    private Observation createObservation(String sensorId) {
        Location loc = new WGS84Location(48.2088475, 16.3734492, 158, "Stephansdome", Collections.singletonList("City of Wien"));

        TimeZone zoneUTC = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(zoneUTC);
        Date date = new Date();
        String timestamp = dateFormat.format(date);

        long ms = date.getTime() - 1000;
        date.setTime(ms);
        String samplet = dateFormat.format(date);

        ObservationValue obsval =
                new ObservationValue(
                        "7",
                        new Property("Temperature", "TempIRI", Collections.singletonList("Air temperature")),
                        new UnitOfMeasurement("C", "degree Celsius", "C_IRI", null));
        ArrayList<ObservationValue> obsList = new ArrayList<>();
        obsList.add(obsval);

        return new Observation(sensorId, loc, timestamp, samplet , obsList);
    }
}