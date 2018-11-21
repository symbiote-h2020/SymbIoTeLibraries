package eu.h2020.symbiote.client.feign;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class ModelA {

    public static final String URI = "http://example.org/A";
    public static final String NS = URI + "#";
    public static final String Person = NS + "Person";
    public static final String name = NS + "name";

    private ModelA() {
    }
}
