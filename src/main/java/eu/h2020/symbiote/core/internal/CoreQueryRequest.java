package eu.h2020.symbiote.core.internal;

import java.util.List;

/**
 * POJO describing a query for resources.
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
    private String resource_type;
    private String token;

    /**
     * Default empty constructor.
     */
    public CoreQueryRequest() {
        // Needed for Jackson serialization
    }

    public CoreQueryRequest(String platform_id, String platform_name, String owner, String name,
                            String id, String description, String location_name, Double location_lat,
                            Double location_long, Integer max_distance, List<String> observed_property,
                            String resource_type, String token) {
        // Needed for Builder
        this.platform_id = platform_id;
        this.platform_name = platform_name;
        this.owner = owner;
        this.name = name;
        this.id = id;
        this.description = description;
        this.location_name = location_name;
        this.location_lat = location_lat;
        this.location_long = location_long;
        this.max_distance = max_distance;
        this.observed_property = observed_property;
        this.resource_type = resource_type;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String buildQuery(String symbioteCoreUrl) {
        String url = symbioteCoreUrl + "/query?";
        boolean isFirstParameter = true;

        if (platform_id != null) {
            if (isFirstParameter) {
                isFirstParameter = false;
            }

            url += "platform_id=" + platform_id;
        }

        if (platform_name != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "platform_name=" + platform_name;
        }

        if (owner != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "owner=" + owner;
        }

        if (name != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "name=" + name;
        }

        if (id != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "id=" + id;
        }

        if (description != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "description=" + description;
        }

        if (location_name != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "location_name=" + location_name;
        }

        if (location_lat != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "location_lat=" + location_lat;
        }

        if (location_long != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "location_long=" + location_long;
        }

        if (max_distance != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "max_distance=" + max_distance;
        }

        if (observed_property != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "observed_property=";

            for (String property : observed_property)
                url += property + ',';

            url = url.substring(0, url.length() - 1);
        }

        if (resource_type != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "resource_type=" + resource_type;
        }

        if (token != null) {
            if (isFirstParameter)
                isFirstParameter = false;
            else
                url += "&";

            url += "token=" + token;
        }

        return url;
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
        private String builder_resource_type;
        private String builder_token;

        public Builder() {
            // empty constructor
        }

        public Builder platformId(String platform_id) {
            this.builder_platform_id = platform_id;
            return this;
        }

        public Builder platformName(String platform_name) {
            this.builder_platform_name = platform_name;
            return this;
        }

        public Builder owner(String owner) {
            this.builder_owner = owner;
            return this;
        }

        public Builder name(String name) {
            this.builder_name = name;
            return this;
        }

        public Builder id(String id) {
            this.builder_id = id;
            return this;
        }

        public Builder description(String description) {
            this.builder_description = description;
            return this;
        }

        public Builder locationName(String location_name) {
            this.builder_location_name = location_name;
            return this;
        }

        public Builder locationLat(Double location_lat) {
            this.builder_location_lat = location_lat;
            return this;
        }

        public Builder locationLong(Double location_long) {
            this.builder_location_long = location_long;
            return this;
        }

        public Builder maxDistance(Integer max_distance) {
            this.builder_max_distance = max_distance;
            return this;
        }

        public Builder observedProperty(List<String> observed_property) {
            this.builder_observed_property = observed_property;
            return this;
        }

        public Builder resourceType(String resource_type) {
            this.builder_resource_type = resource_type;
            return this;
        }

        public Builder token(String token) {
            this.builder_token = token;
            return this;
        }

        public CoreQueryRequest build() {
            return new CoreQueryRequest(builder_platform_id, builder_platform_name, builder_owner, builder_name,
                    builder_id, builder_description, builder_location_name, builder_location_lat,
                    builder_location_long, builder_max_distance, builder_observed_property,
                    builder_resource_type, builder_token);
        }
    }
}
