package eu.h2020.symbiote.barteringAndTrading;

public class FilterResponse {

	private String couponId;
	private String issuer;
	private Long usedDuringPeriod;

	public FilterResponse(){
	}

	public FilterResponse(String couponId, String issuer,
			long usedDuringPeriod) {
		this.couponId = couponId;
		this.issuer = issuer;
		this.usedDuringPeriod = usedDuringPeriod;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Long getUsedDuringPeriod() {
		return usedDuringPeriod;
	}

	public void setUsedDuringPeriod(Long usedDuringPeriod) {
		this.usedDuringPeriod = usedDuringPeriod;
	}
}
