package eu.h2020.symbiote.util;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

/**
 * Created by vasgl on 8/13/2017.
 */
public class IntervalFormatter {

    private Period period;
    private static final Integer MILLIS_IN_SECOND = 1000;
    private static final Integer MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60;
    private static final Integer MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;
    private static final Integer MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    private static final Long MILLIS_IN_MONTH = MILLIS_IN_DAY * 30L;
    private static final Long MILLIS_IN_YEAR = MILLIS_IN_DAY * 365L;


    public IntervalFormatter(String periodString) {
        PeriodFormatter periodFormatter = ISOPeriodFormat.alternateExtended();
        this.period = periodFormatter.parsePeriod(periodString);
    }

    public Long getMillis() {
        return new Long(period.getYears()) * MILLIS_IN_YEAR +
                new Long(period.getMonths()) * MILLIS_IN_MONTH +
                new Long(period.getDays()) * MILLIS_IN_DAY +
                new Long(period.getHours()) * MILLIS_IN_HOUR +
                new Long(period.getMinutes()) * MILLIS_IN_MINUTE +
                new Long(period.getSeconds()) * MILLIS_IN_SECOND +
                new Long(period.getMillis());
    }


}
