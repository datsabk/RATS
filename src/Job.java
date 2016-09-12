import java.sql.Timestamp;
/**
 * This class is created to be used as a Java Value bean for the application
 * specified in the requirement provided for the task
 * 
 * @author A.A.Marczyk 
 * @version 07/03/08
 */

public class Job {
	private int jobId;
	private int hours;
	private String assignedStaffId;
	private String customer;
	private boolean sHand;
	private boolean onsite;
	private String lang;
	private String state;
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the sHand
	 */
	public boolean issHand() {
		return sHand;
	}
	/**
	 * @param sHand the sHand to set
	 */
	public void setsHand(boolean sHand) {
		this.sHand = sHand;
	}
	/**
	 * @return the onsite
	 */
	public boolean isOnsite() {
		return onsite;
	}
	/**
	 * @param onsite the onsite to set
	 */
	public void setOnsite(boolean onsite) {
		this.onsite = onsite;
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}
	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}
	/**
	 * @return the assignedStaffId
	 */
	public String getAssignedStaffId() {
		return assignedStaffId;
	}
	/**
	 * @param assignedStaffId the assignedStaffId to set
	 */
	public void setAssignedStaffId(String assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}	
}
