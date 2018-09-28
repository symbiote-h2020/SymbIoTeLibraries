package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

/**
 * POJO describing a getResourceUrls for resources.
 */
public class PlatformRegistryQuery {

    private final List<String> name;
    private final List<String> description;
    private final List<String> id;
    private final List<String> federationId;
    private final List<String> observes_property;
    private final String resource_type;
    private final List<String> location_name;
    private final Double location_lat;
    private final Double location_long;
    private final Double max_distance;
    private final Double resource_trust;
    private final Double adaptive_trust;
    private final String sort;

    @JsonCreator
    public PlatformRegistryQuery(@JsonProperty("name") List<String> name,
                                 @JsonProperty("description") List<String> description,
                                 @JsonProperty("id") List<String> id,
                                 @JsonProperty("federationId") List<String> federationId,
                                 @JsonProperty("observes_property") List<String> observes_property,
                                 @JsonProperty("resource_type") String resource_type,
                                 @JsonProperty("location_name") List<String> location_name,
                                 @JsonProperty("location_lat") Double location_lat,
                                 @JsonProperty("location_long") Double location_long,
                                 @JsonProperty("max_distance") Double max_distance,
                                 @JsonProperty("resource_trust") Double resource_trust,
                                 @JsonProperty("adaptive_trust") Double adaptive_trust,
                                 @JsonProperty("sort") String sort) {
        this.name = name == null ? null : new ArrayList<>(name);
        this.description = description == null ? null : new ArrayList<>(description);
        this.id = id == null ? null : new ArrayList<>(id);
        this.federationId = federationId == null ? null : new ArrayList<>(federationId);
        this.observes_property = observes_property == null ? null : new ArrayList<>(observes_property);
        this.resource_type = resource_type;
        this.location_name = location_name == null ? null : new ArrayList<>(location_name);
        this.location_lat = location_lat;
        this.location_long = location_long;
        this.max_distance = max_distance;
        this.resource_trust = resource_trust;
        this.adaptive_trust = adaptive_trust;
        this.sort = sort;
    }

    public static PlatformRegistryQuery newInstance(PlatformRegistryQuery platformRegistryQuery) {
        return new Builder()
                .names(platformRegistryQuery.getName())
                .descriptions(platformRegistryQuery.getDescription())
                .ids(platformRegistryQuery.getId())
                .federationIds(platformRegistryQuery.getFederationId())
                .observesProperties(platformRegistryQuery.getObserves_property())
                .resourceType(platformRegistryQuery.getResource_type())
                .locationNames(platformRegistryQuery.getLocation_name())
                .locationLat(platformRegistryQuery.getLocation_lat())
                .locationLong(platformRegistryQuery.getLocation_long())
                .maxDistance(platformRegistryQuery.getMax_distance())
                .resourceTrust(platformRegistryQuery.getResource_trust())
                .adaptiveTrust(platformRegistryQuery.getAdaptive_trust())
                .sort(platformRegistryQuery.getSort())
                .build();
    }

    public List<String> getName() { return name; }
    public List<String> getDescription() { return description; }
    public List<String> getId() { return id; }
    public List<String> getFederationId() { return federationId; }
    public List<String> getObserves_property() { return observes_property; }
    public String getResource_type() { return resource_type; }
    public List<String> getLocation_name() { return location_name; }
    public Double getLocation_lat() { return location_lat; }
    public Double getLocation_long() { return location_long; }
    public Double getMax_distance() { return max_distance; }
    public Double getResource_trust() { return resource_trust; }
    public Double getAdaptive_trust() { return adaptive_trust; }
    public String getSort() { return sort; }

    public String buildQuery(String platformUrl) {
           return platformUrl + "/pr/search?" + buildRequestParameters();
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

        private List<String> builder_names;
        private List<String> builder_descriptions;
        private List<String> builder_ids;
        private List<String> builder_federationIds;
        private List<String> builder_observes_properties;
        private String builder_resource_type;
        private List<String> builder_location_names;
        private Double builder_location_lat;
        private Double builder_location_long;
        private Double builder_max_distance;
        private Double builder_resource_trust;
        private Double builder_adaptive_trust;
        private String builder_sort;

        public Builder() {
            // empty constructor
        }

        public Builder names(List<String> names) {
            if (names != null)
                this.builder_names = new ArrayList<>(names);
            return this;
        }

        public Builder descriptions(List<String> descriptions) {
            if (descriptions != null)
                this.builder_descriptions = new ArrayList<>(descriptions);
            return this;
        }
        public Builder ids(List<String> ids) {
            if (ids != null)
                this.builder_ids = ids;
            return this;
        }

        public Builder federationIds(List<String> federationIds) {
            if (federationIds != null)
                this.builder_federationIds = new ArrayList<>(federationIds);
            return this;
        }

        public Builder observesProperties(List<String> observesProperties) {
            if (observesProperties != null)
                this.builder_observes_properties = new ArrayList<>(observesProperties);
            return this;
        }

        public Builder resourceType(String resourceType) {
            if (resourceType != null)
                this.builder_resource_type = resourceType;
            return this;
        }

        public Builder locationNames(List<String> locationNames) {
            if (locationNames != null)
                this.builder_location_names = new ArrayList<>(locationNames);
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

        public Builder maxDistance(Double max_distance) {
            if (max_distance != null)
                this.builder_max_distance = max_distance;
            return this;
        }

        public Builder resourceTrust(Double resourceTrust) {
            if (resourceTrust != null)
                this.builder_resource_trust = resourceTrust;
            return this;
        }

        public Builder adaptiveTrust(Double adaptiveTrust) {
            if (adaptiveTrust != null)
                this.builder_adaptive_trust = adaptiveTrust;
            return this;
        }

        public Builder sort(String sort) {
            if (sort != null)
                this.builder_sort = sort;
            return this;
        }


        public PlatformRegistryQuery build() {
            return new PlatformRegistryQuery(
                    builder_names,
                    builder_descriptions,
                    builder_ids,
                    builder_federationIds,
                    builder_observes_properties,
                    builder_resource_type,
                    builder_location_names,
                    builder_location_lat,
                    builder_location_long,
                    builder_max_distance,
                    builder_resource_trust,
                    builder_adaptive_trust,
                    builder_sort);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlatformRegistryQuery that = (PlatformRegistryQuery) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(id, that.id) &&
                Objects.equals(federationId, that.federationId) &&
                Objects.equals(observes_property, that.observes_property) &&
                Objects.equals(resource_type, that.resource_type) &&
                Objects.equals(location_name, that.location_name) &&
                Objects.equals(location_lat, that.location_lat) &&
                Objects.equals(location_long, that.location_long) &&
                Objects.equals(max_distance, that.max_distance) &&
                Objects.equals(resource_trust, that.resource_trust) &&
                Objects.equals(adaptive_trust, that.adaptive_trust) &&
                Objects.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, id, federationId, observes_property, resource_type, location_name, location_lat, location_long, max_distance, resource_trust, adaptive_trust, sort);
    }
}
