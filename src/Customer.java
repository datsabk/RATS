import java.util.ArrayList;
/**
 * This class carries required attributes of the customer 
 * necessary to handle the customer entity for the application
 * 
 * @author A.A.Marczyk 
 * @version 07/03/08
 */

public class Customer {
	private double creditLimit;
	private double costOwed;
	private String type;
	private String name;
	
	
	/**
	 * @return the costOwed
	 */
	public double getCostOwed() {
		return costOwed;
	}
	/**
	 * @param costOwed the costOwed to set
	 */
	public void setCostOwed(double costOwed) {
		this.costOwed = costOwed;
	}
	/**
	 * @return the creditLimit
	 */
	public double getCreditLimit() {
		return creditLimit;
	}
	/**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
}
