import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the RATS
 * system required for 2COM0057 Deferred/Referred Coursework 
 * 2007/8
 * 
 * @author A.A.Marczyk 
 * @version 07/03/08
 */
public class Branch implements Manager
{
	private String branch;
	private ArrayList<Customer> customerList=new ArrayList<Customer>();
	private ArrayList<Job> jobList=new ArrayList<Job>();
	private ArrayList<Staff> staffList=new ArrayList<Staff>();
	private long balance;
	@Override
	public String getBranch() {

		return branch;
	}

	@Override
	public String getAllCustomers() {

		return customerList.toString();
	}

	@Override
	public void addStaff(String id, String name) {
		Staff s = new Staff();
		s.setName(name);
		s.setUniqueID(id);
		s.setWorkStatus("available");
		s.setRate(8.0);
		staffList.add(s);
	}

	@Override
	public void addStaff(String id, String name, boolean hOnly, boolean sHand) {
		Typist s = new Typist();
		s.setName(name);
		s.setUniqueID(id);
		s.setWorkFromHome(hOnly);
		s.setShorthand(sHand);
		if(sHand)
			s.setRate(32.0);
		else
			s.setRate(12.0);
		staffList.add(s);

	}

	@Override
	public void addStaff(String id, String name, boolean hOnly, boolean sHand,
			String lang, double rate) {
		Translator s = new Translator();
		s.setName(name);
		s.setUniqueID(id);
		s.setWorkFromHome(hOnly);
		s.setShorthand(sHand);
		s.setLanguage(lang);
		if(sHand)
			s.setRate(rate+20.0);
		else
			s.setRate(rate);

		staffList.add(s);

	}

	@Override
	public void setEmail(String id, String email) {
		for(Staff s:staffList)
		{
			if(s.getUniqueID().equals(id))
				s.setEmail(email);
		}

	}

	@Override
	public boolean isStaff(String stfId) {

		for(Staff s:staffList)
		{
			if(s.getUniqueID().equals(stfId))
				return true;
		}
		return false;
	}

	@Override
	public void removeStaff(String depId) {

		for(Staff s:staffList)
		{
			if(s.getUniqueID().equals(depId))
				staffList.remove(s);
		}
	}

	@Override
	public String getAllStaff() {

		return staffList.toString();
	}

	@Override
	public String addJob(String cust, boolean onSite, boolean sHand, String lang) {

		int jobId = 100+jobList.size();
		String message="";
		Customer c = findCustomer(cust);
		if(c==null)
		{
			c = new Customer();
			c.setCostOwed(0);
			c.setCreditLimit(100);
			c.setName(cust);
			c.setType("non-regular");
			customerList.add(c);
		}
		if(c.getCreditLimit()>0)
		{
			message+= "Job No "+jobId+" ";
			Job j = new Job();
			j.setJobId(jobId);
			j.setCustomer(cust);
			j.setOnsite(onSite);
			j.setsHand(sHand);
			j.setLang(lang);
			Staff s = findStaff(j);
			if(s!=null)
			{
				message+="staff allocated:\n"+"Staff:"+s.getUniqueID()+" "+s.getName();
				message+="\nHome only:"+!j.isOnsite();
				message+="\nShortHand:" + j.issHand();
				message+="\nHourly Rate:" + s.getRate();
				message+="\nAvailability: on a job";
				message+="\nStaff Type:";
				if(s instanceof Translator)
					message+="Translator";
				else if(s instanceof Typist)
					message+= "Typist";
				else
					message+="Clerk";
				message+="\nLanguage:"+j.getLang();
				j.setCustomer(cust);
				j.setState("ongoing");
				j.setAssignedStaffId(s.getUniqueID());
				jobList.add(j);
			}
			else
			{
				j.setState("waiting");
				message+="waiting";
				jobList.add(j);
			}
		}
		else
		{
			return "Customer over credit limit";
		}
		return message;
	}
	
	/**Returns the staff member object if available for the job
     * @return the staff member object
     **/ 
	private Staff findStaff(Job j) {
		String requiredType = "";
		if(!j.getLang().isEmpty())
		{
			requiredType="Translator";
		}
		else if(j.issHand()&& j.getLang().isEmpty())
		{
			requiredType= "Typist";
		}
		else
		{
			requiredType="Clerk";
		}
		for(Staff s:staffList)
		{
			if(requiredType.equals("Translator") && s instanceof Translator && !s.isOccupied() && ((Translator)s).isWorkFromHome()!=j.isOnsite() &&  ((Translator)s).isShorthand()==j.issHand()&& ((Translator)s).getLanguage().equalsIgnoreCase(j.getLang()))
			{

				s.setOccupied(true);
				s.setWorkStatus("working");
				return s;
			}
			else if(requiredType.equals("Typist") && s instanceof Typist && !s.isOccupied()&& ((Typist)s).isWorkFromHome()!=j.isOnsite() &&  ((Typist)s).isShorthand()==j.issHand())
			{
				s.setOccupied(true);
				s.setWorkStatus("working");
				return s;
			}
			else if(!(requiredType.equals("Typist")||requiredType.equals("Translator"))&&!s.isOccupied())
			{
				s.setOccupied(true);
				s.setWorkStatus("working");
				return s;
			}
		}
		return null;
	}

	/*
	 * Retuns a customer object if customer with same name exists
	 */
	private Customer findCustomer(String cust) {
		for(Customer c:customerList)
		{
			if(c.getName().equalsIgnoreCase(cust))
				return c;
		}
		return null;
	}

	@Override
	public String getAllJobs() {

		String message = "";
		for(Job j : jobList)
		{
			message+="Job No: "+j.getJobId();
			message+="\nCustomer:\n";
			Customer c = findCustomer(j.getCustomer());
			message+="Customer: "+c.getName();
			message+="\nCredit Limit:"+c.getCreditLimit();
			message+="\nMoney owing:"+c.getCostOwed();
			message+="\nShorthand: "+j.issHand();
			message+="\nLanguage: "+j.getLang();
			message+="\nAt home:"+ !j.isOnsite();
			message+="\nState:"+ (j.getState().equals("waiting")?" on the waiting list":"In Progress");
			message="\n========================================================";

		}
		return message;
	}

	@Override
	public String getJobsWaiting() {
		String message = "";
		for(Job j : jobList)
		{
			if(j.getState().equals("waiting"))
			{
				message+="Job No: "+j.getJobId();
				message+="\nCustomer:\n";
				Customer c = findCustomer(j.getCustomer());
				message+="Customer: "+c.getName();
				message+="\nCredit Limit:"+c.getCreditLimit();
				message+="\nMoney owing:"+c.getCostOwed();
				message+="\nShorthand: "+j.issHand();
				message+="\nLanguage: "+j.getLang();
				message+="\nAt home:"+ !j.isOnsite();
				message+="\nState:"+ (j.getState().equals("waiting")?" on the waiting list":"In Progress");
				message+="\n================\n";
			}
		}
		return message;
	}

	@Override
	public double getJobCost(int jNo) {

		for(Job j:jobList)
		{
			if(j.getJobId()==jNo)
			{
				for(Staff s : staffList)
				{
					if(s.getUniqueID().equals(j.getAssignedStaffId()))
					{
						double cost = 0.0;
						cost = s.getRate();
						s.setEarnedWage(s.getEarnedWage()+cost*j.getHours());
						return cost*j.getHours();
					}
				}
			}
		}
		return 0;
	}

	@Override
	public int setJobDone(int jNo, int hours) {

		for(Job j:jobList){
			if(j.getJobId()==jNo)
			{
				for(Staff s : staffList)
				{
					if(s.getUniqueID().equals(j.getAssignedStaffId()))
					{
						s.setOccupied(false);
						s.setWorkStatus("available");
					}
				}
				for(Customer c:customerList)
				{
					if(c.getName().equals(j.getCustomer()))
					{
						if(c.getCreditLimit()-getJobCost(jNo)>0)
						{
						c.setCreditLimit(c.getCreditLimit()-getJobCost(jNo));
						}
						else
						{
							c.setCreditLimit(c.getCreditLimit()-getJobCost(jNo));
							c.setCostOwed(-1* c.getCreditLimit());
							c.setCreditLimit(0);
						}
						
					}
				}
				j.setHours(hours);
				return 1;
			}
		}
		return -1;
	}

	@Override
	public int checkJobsWaiting() {

		return 0;
	}
	// constructor

	/**
	 * @return the jobList
	 */
	public ArrayList<Job> getJobList() {
		return jobList;
	}

	/**
	 * @param jobList the jobList to set
	 */
	public void setJobList(ArrayList<Job> jobList) {
		this.jobList = jobList;
	}

	/**
	 * @return the staffList
	 */
	public ArrayList<Staff> getStaffList() {
		return staffList;
	}

	/**
	 * @param staffList the staffList to set
	 */
	public void setStaffList(ArrayList<Staff> staffList) {
		this.staffList = staffList;
	}




}



