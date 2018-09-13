package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

/**
 * POJO describing a getResourceUrls for resources.
 */
public class CoreQueryRequest {

    private String platform_id;
    private String platform_name;
    private String owner;
    private String name;
    private String id;
    private String description;
    private String location_name;
    private Double location_lat;
    private Double location_long;
    private Integer max_distance;
    private List<String> observed_property;
    private List<String> observed_property_iri;
    private String resource_type;
    private SecurityRequest securityRequest;
    private Boolean should_rank;

    /**
     * Default empty constructor.
     */
    public CoreQueryRequest() {
        // Needed for Jackson serialization
    }

    public CoreQueryRequest(String platform_id, String platform_name, String owner, String name,
                            String id, String description, String location_name, Double location_lat,
                            Double location_long, Integer max_distance, List<String> observed_property,
                            List<String> observed_property_iri, String resource_type,
                            SecurityRequest securityRequest, Boolean should_rank) {
        // Needed for Builder
        setPlatform_id(platform_id);
        setPlatform_name(platform_name);
        setOwner(owner);
        setName(name);
        setId(id);
        setDescription(description);
        setLocation_name(location_name);
        setLocation_lat(location_lat);
        setLocation_long(location_long);
        setMax_distance(max_distance);
        setObserved_property(observed_property);
        setObserved_property_iri(observed_property_iri);
        setResource_type(resource_type);
        setSecurityRequest(securityRequest);
        setShould_rank(should_rank);
    }

    public static CoreQueryRequest newInstance(CoreQueryRequest coreQueryRequest) {
        return new Builder()
                .platformId(coreQueryRequest.getPlatform_id())
                .platformName(coreQueryRequest.getPlatform_name())
                .owner(coreQueryRequest.getOwner())
                .name(coreQueryRequest.getName())
                .id(coreQueryRequest.getId())
                .description(coreQueryRequest.getDescription())
                .locationName(coreQueryRequest.getLocation_name())
                .locationLat(coreQueryRequest.getLocation_lat())
                .locationLong(coreQueryRequest.getLocation_long())
                .maxDistance(coreQueryRequest.getMax_distance())
                .observedProperty(coreQueryRequest.getObserved_property())
                .observedPropertyIri(coreQueryRequest.getObserved_property_iri())
                .resourceType(coreQueryRequest.getResource_type())
                .securityRequest(coreQueryRequest.getSecurityRequest())
                .shouldRank(coreQueryRequest.getShould_rank())
                .build();
    }

    public String getPlatform_id() {
        return platform_id;
    }
    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_name() {
        return platform_name;
    }
    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation_name() {
        return location_name;
    }
    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Double getLocation_lat() {
        return location_lat;
    }
    public void setLocation_lat(Double location_lat) {
        this.location_lat = location_lat;
    }

    public Double getLocation_long() {
        return location_long;
    }
    public void setLocation_long(Double location_long) {
        this.location_long = location_long;
    }

    public Integer getMax_distance() {
        return max_distance;
    }
    public void setMax_distance(Integer max_distance) {
        this.max_distance = max_distance;
    }

    public List<String> getObserved_property_iri() {
        return observed_property_iri;
    }
    public void setObserved_property_iri(List<String> observed_property_iri) {
        this.observed_property_iri = observed_property_iri;
    }

    public List<String> getObserved_property() {
        return observed_property;
    }
    public void setObserved_property(List<String> observed_property) {
        this.observed_property = observed_property;
    }

    public String getResource_type() {
        return resource_type;
    }
    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public SecurityRequest getSecurityRequest() {
        return securityRequest;
    }
    public void setSecurityRequest(SecurityRequest securityRequest) {
        this.securityRequest = securityRequest;
    }

    public Boolean getShould_rank() {
        return should_rank;
    }
    public void setShould_rank(Boolean should_rank) {
        this.should_rank = should_rank;
    }

    public String buildQuery(String symbioteCoreUrl) {
           return symbioteCoreUrl + "/query?" + buildRequestParameters();
    }

    public String buildRequestParameters() {
        StringBuilder url = new StringBuilder();

        for (Map.Entry<String, String> entry : buildRequestParametersMap().entrySet())
            url.append(entry.getKey()).append('=').append(entry.getValue()).append('&');

        if (url.length() > 0)
            url.deleteCharAt(url.length() - 1);
        return url.toString();
    }

    public Map<String, String> buildRequestParametersMap() {
        Field[] allFields = this.getClass().getDeclaredFields();
        Map<String, String> map = new LinkedHashMap<>(allFields.length, 1, false);

        for (Field field : allFields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();

            if (fieldName.startsWith("$"))
                continue;


            if (fieldType == List.class) {
                try {
                    String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getterMethod = this.getClass().getMethod(getterName, (Class<?>[])null);
                    @SuppressWarnings("unchecked")
                    List<Object> listOfObjects = (List<Object>) getterMethod.invoke(this, (Object[])null);

                    if (listOfObjects != null && listOfObjects.size() > 0) {
                        StringBuilder sb = new StringBuilder();

                        for (Object o : listOfObjects) {
                            try {
                                sb.append(URLEncoder.encode(o.toString(), "UTF-8")).append(',');
                            } catch (UnsupportedEncodingException e) {
                                throw new IllegalArgumentException("When trying to encode the URL:", e);	// Should only be triggered when UTF-8 is misspelled :-)
                            }

                        }
                        sb.deleteCharAt(sb.length() - 1);
                        map.put(fieldName, sb.toString());
                    }

                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getterMethod = this.getClass().getMethod(getterName, (Class<?>[])null);
                    Object value = getterMethod.invoke(this, (Object[])null);

                    if (value != null){

                        try {
                            map.put(fieldName, URLEncoder.encode(value.toString(), "UTF-8"));
                            /* URL encoding, the forgotten art?
                             * Note, that when you encode a complete URL you will also encode special char's like
                             * & or ?. They have a special meaning in URLs so you might have a certain interest to keep them as they are.
                             * But when they come by in a payload they shall NOT have that special meaning. So you have to URNEncode
                             * your payload but not the complete URL.
                             * Why use URLEncode and not some hand made thingy?
                             * 'cause there are a few more char's than you think about.
                             * Look at the specs of something so simple as an URL :-)
                             *
                             */
                        } catch (UnsupportedEncodingException e) {
                            throw new IllegalArgumentException("When trying to encode the URL:", e);	// Should only be triggered when UTF-8 is misspelled :-)
                            // Throw something that does not need any declaration.
                        }
                    }
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();	// Really? Don't we have a logging concept? This will most probably end in nirwana when logged to stdout so it#s only good during an interactive debug session
                }
            }

        }
        return map;
    }

    public static class Builder {

        private String builder_platform_id;
        private String builder_platform_name;
        private String builder_owner;
        private String builder_name;
        private String builder_id;
        private String builder_description;
        private String builder_location_name;
        private Double builder_location_lat;
        private Double builder_location_long;
        private Integer builder_max_distance;
        private List<String> builder_observed_property;
        private List<String> builder_observed_property_iri;
        private String builder_resource_type;
        private SecurityRequest builder_securityRequest;
        private Boolean builder_should_rank;

        public Builder() {
            // empty constructor
        }

        public Builder platformId(String platform_id) {
            if (platform_id != null)
                this.builder_platform_id = platform_id;
            return this;
        }

        public Builder platformName(String platform_name) {
            if (platform_name != null)
                this.builder_platform_name = platform_name;
            return this;
        }

        public Builder owner(String owner) {
            if (owner != null)
            this.builder_owner = owner;
            return this;
        }

        public Builder name(String name) {
            if (name != null)
                this.builder_name = name;
            return this;
        }

        public Builder id(String id) {
            if (id != null)
                this.builder_id = id;
            return this;
        }

        public Builder description(String description) {
            if (description != null)
                this.builder_description = description;
            return this;
        }

        public Builder locationName(String location_name) {
            if (location_name != null)
                this.builder_location_name = location_name;
            return this;
        }

        public Builder locationLat(Double location_lat) {
            if (location_lat != null)
                this.builder_location_lat = location_lat;
            return this;
        }

        public Builder locationLong(Double location_long) {
            if (location_long != null)
                this.builder_location_long = location_long;
            return this;
        }

        public Builder maxDistance(Integer max_distance) {
            if (max_distance != null)
                this.builder_max_distance = max_distance;
            return this;
        }

        public Builder observedProperty(List<String> observed_property) {
            if (observed_property != null)
                this.builder_observed_property = observed_property;
            return this;
        }

        public Builder observedPropertyIri(List<String> observed_property_iri) {
            if (observed_property_iri != null)
                this.builder_observed_property_iri = observed_property_iri;
            return this;
        }

        public Builder resourceType(String resource_type) {
            if (resource_type != null)
                this.builder_resource_type = resource_type;
            return this;
        }

        public Builder securityRequest(SecurityRequest securityRequest) {
            if (securityRequest != null)
                this.builder_securityRequest = securityRequest;
            return this;
        }

        public Builder shouldRank(Boolean should_rank) {
            if (should_rank != null)
                this.builder_should_rank = should_rank;
            return this;
        }

        public CoreQueryRequest build() {
            return new CoreQueryRequest(
                    builder_platform_id,
                    builder_platform_name,
                    builder_owner,
                    builder_name,
                    builder_id,
                    builder_description,
                    builder_location_name,
                    builder_location_lat,
                    builder_location_long,
                    builder_max_distance,
                    builder_observed_property,
                    builder_observed_property_iri,
                    builder_resource_type,
                    builder_securityRequest,
                    builder_should_rank);
        }
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        
        // null check
        if (o == null)
            return false;
        
        // type check and cast
        if (!(o instanceof CoreQueryRequest))
            return false;
        
        CoreQueryRequest coreQueryRequest = (CoreQueryRequest) o;
        // field comparison
        return Objects.equals(platform_id, coreQueryRequest.platform_id)
                && Objects.equals(platform_name, coreQueryRequest.platform_name)
                && Objects.equals(owner, coreQueryRequest.owner)
                && Objects.equals(name, coreQueryRequest.name)
                && Objects.equals(id, coreQueryRequest.id)
                && Objects.equals(description, coreQueryRequest.description)
                && Objects.equals(location_name, coreQueryRequest.location_name)
                && Objects.equals(location_lat, coreQueryRequest.location_lat)
                && Objects.equals(location_long, coreQueryRequest.location_long)
                && Objects.equals(max_distance, coreQueryRequest.max_distance)
                && Objects.equals(observed_property, coreQueryRequest.observed_property)
                && Objects.equals(observed_property_iri, coreQueryRequest.observed_property_iri)
                && Objects.equals(resource_type, coreQueryRequest.resource_type)
                && Objects.equals(securityRequest, coreQueryRequest.securityRequest)
                && Objects.equals(should_rank,coreQueryRequest.should_rank);
    }
}
