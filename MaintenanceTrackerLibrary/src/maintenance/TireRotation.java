package maintenance;

import java.util.Date;

/**
 * Implementation of maintenance for a tire rotation
 */
public class TireRotation implements Maintenance {
	public static final String MAINTENANCE_TYPE = "tirerotation";
	private Date date;

	/**
	 * Constructs a TireRotation with date as input
	 *
	 * @param date Date of maintenance
	 */
	public TireRotation(Date date) {
		this.date = date;
	}

	/**
	 * Default constructor for TIreRotation
	 */
	public TireRotation() {
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
