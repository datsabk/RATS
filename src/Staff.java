
/**
 * @author A
 *
 */
public class Staff {
	private String uniqueID;
	private String name;
	private boolean occupied;
	private String workStatus;
	private double earnedWage;
	private String email;
	private double rate;
	/**
	 * @return the uniqueID
	 */
	public String getUniqueID() {
		return uniqueID;
	}
	/**
	 * @param uniqueID the uniqueID to set
	 */
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	/**
	 * @return the workStatus
	 */
	public String getWorkStatus() {
		return workStatus;
	}
	/**
	 * @param workStatus the workStatus to set
	 */
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	/**
	 * @return the earnedWage
	 */
	public double getEarnedWage() {
		return earnedWage;
	}
	/**
	 * @param earnedWage the earnedWage to set
	 */
	public void setEarnedWage(double earnedWage) {
		this.earnedWage = earnedWage;
	}
	/**
	 * @return the occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}
	/**
	 * @param occupied the occupied to set
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
