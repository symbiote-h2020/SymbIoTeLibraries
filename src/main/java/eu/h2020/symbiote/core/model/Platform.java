package eu.h2020.symbiote.core.model;

import org.springframework.data.annotation.Id;


public class Platform {

    @Id
    private String platformId;
    private String name;
    private String description;
    private String url;
    private String informationModelId;

    public Platform() {

    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInformationModelId() {
        return informationModelId;
    }

    public void setInformationModelId(String informationModelId) {
        this.informationModelId = informationModelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Platform platform = (Platform) o;

        if (platformId != null ? !platformId.equals(platform.platformId) : platform.platformId != null) return false;
        if (name != null ? !name.equals(platform.name) : platform.name != null) return false;
        if (description != null ? !description.equals(platform.description) : platform.description != null)
            return false;
        if (url != null ? !url.equals(platform.url) : platform.url != null) return false;
        return informationModelId != null ? informationModelId.equals(platform.informationModelId) : platform
                .informationModelId == null;
    }

    @Override
    public int hashCode() {
        int result = platformId != null ? platformId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (informationModelId != null ? informationModelId.hashCode() : 0);
        return result;
    }
}