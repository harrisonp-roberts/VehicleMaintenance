package maintenance;

import java.util.Date;

/**
 * Defines how maintenance is assigned to a car
 */
public interface Maintenance {
	public String getMaintenanceType();

	public Date getDate();
}
