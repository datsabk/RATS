/**
 * This class inherited from the Staff class carries additional attributes necessary
 * for the Translator type of staff in the application
 * 
 * @author A.A.Marczyk 
 * @version 07/03/08
 */


public class Translator extends Staff {
	private boolean shorthand;
	private boolean workFromHome;
	private String language;
	
	/**
	 * @return the shorthand
	 */
	public boolean isShorthand() {
		return shorthand;
	}
	/**
	 * @param shorthand the shorthand to set
	 */
	public void setShorthand(boolean shorthand) {
		this.shorthand = shorthand;
	}
	/**
	 * @return the workFromHome
	 */
	public boolean isWorkFromHome() {
		return workFromHome;
	}
	/**
	 * @param workFromHome the workFromHome to set
	 */
	public void setWorkFromHome(boolean workFromHome) {
		this.workFromHome = workFromHome;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
