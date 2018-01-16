package eu.h2020.symbiote.cloud.monitoring.model;

public enum AggregationOperation {
  SUM("sum"),
  AVG("avg"),
  MAX("max"),
  MIN("min"),
  STD_DEV_POP("stdDevPop"),
  STD_DEV_SAMP("stdDevSamp");
  
  private String value;
  
  AggregationOperation(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return value;
  }
  
  public static AggregationOperation fromValue(String opValue) {
    for (AggregationOperation op : AggregationOperation.values()) {
      // Use equalsIgnoreCase to make the getValue method a little more robust
      if (op.toString().equalsIgnoreCase(opValue)) {
        return op;
      }
    }
    return null;
  }
}
