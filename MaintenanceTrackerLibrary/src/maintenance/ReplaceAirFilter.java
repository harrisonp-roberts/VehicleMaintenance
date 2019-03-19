package maintenance;

import java.util.Date;

/**
 * Implementation of maintenance for replacing an air filter
 */
public class ReplaceAirFilter implements Maintenance {
	public static final String MAINTENANCE_TYPE = "replacefilter";
	private Date date;

	/**
	 * Constructs a ReplaceAirFilter with date as input
	 *
	 * @param date Date of maintenance
	 */
	public ReplaceAirFilter(Date date) {
		this.date = date;
	}

	/**
	 * Constructs a ReplaceAirFilter
	 */
	public ReplaceAirFilter() {
		this.date = new Date();
	}

	@Override
	public String getMaintenanceType() {
		return MAINTENANCE_TYPE;
	}

	@Override
	public Date getDate() {
		return date;
	}
}
