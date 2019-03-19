package maintenance;

import java.util.Date;

/**
 * Implementation of maintenance for oil changes
 */
public class OilChange implements Maintenance {
	public static final String MAINTENANCE_TYPE = "oilchange";
	private Date date;

	/**
	 * Constructs an Oil Change with a date passed as input
	 *
	 * @param date Date of maintenance
	 */
	public OilChange(Date date) {
		this.date = date;
	}

	/**
	 * Constructs an empty maintenance
	 */
	public OilChange() {
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
