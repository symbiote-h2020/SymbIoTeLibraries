package eu.h2020.symbiote.client;

public interface ClientConstants {
    String PUBLISH_MONITORING_DATA = "/crm/Monitoring/{platformId}/devices/status";
    String PUBLISH_ACCESS_DATA = "/accessNotifications";
    String METRICS_DATA = "/monitoring/metrics/raw";
    String AGGREGATED_DATA = "/monitoring/metrics/aggregated";
    String SUMMARY_DATA = "/monitoring/metrics/summary";
    String RH_RESOURCES_PATH = "/resources";
    String RH_RESOURCE_PATH = "/resource";
    String RH_RDF_RESOURCES_PATH = "/rdf-resources";
    String RH_SYNC_PATH = "/sync";
    String RH_CLEAR_PATH = "/clear";
    String RH_LOCAL_RESOURCES_PATH = "/local/resources";
    String RH_LOCAL_RESOURCES_SHARE_PATH = RH_LOCAL_RESOURCES_PATH+"/share";
    String RH_UPDATE_INTERWORKING_API = RH_RESOURCES_PATH+"/interworkingURL";
}
