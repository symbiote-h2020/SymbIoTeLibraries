package eu.h2020.symbiote.client;

import eu.h2020.symbiote.cloud.monitoring.model.AggregatedMetrics;
import eu.h2020.symbiote.cloud.monitoring.model.DeviceMetric;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

public interface MonitoringClient {
  
  @RequestLine("POST " + ClientConstants.METRICS_DATA)
  @Headers("Content-Type: application/json")
  List<DeviceMetric> postMetrics(List<DeviceMetric> metrics);
  
  @RequestLine("GET " + ClientConstants.METRICS_DATA)
  @Headers("Content-Type: application/json")
  List<DeviceMetric> getMetrics(@QueryMap Map<String, String> parameters);
  
  @RequestLine("GET " + ClientConstants.AGGREGATED_DATA)
  @Headers("Content-Type: application/json")
  List<AggregatedMetrics> getAggregatedMetrics(@QueryMap Map<String, String> parameters);
  
  @RequestLine("GET " + ClientConstants.SUMMARY_DATA+"?federation={federation}&metric={metric}")
  @Headers("Content-Type: application/json")
  Map<String, Double> getSummaryMetric(@Param("federation") String federationId,
                                       @Param("metric") String inputMetric);
}
