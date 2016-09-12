
public class Tester {
	static Manager mmm;
	public static void main(String[] args) {
		mmm = new Branch();

		System.out.println("---------------Test adding of staff--------------");
		mmm.addStaff("CL1", "Ann");
		System.out.println(((Branch)mmm).getStaffList().get(0).getName()+" added to staff");
		mmm.addStaff("CL2", "Bob");
		System.out.println(((Branch)mmm).getStaffList().get(1).getName()+" added to staff");
		mmm.addStaff("TY1", "Che",true,true);
		System.out.println(((Branch)mmm).getStaffList().get(2).getName()+" added to staff");
		mmm.addStaff("TY2", "Dan",true,false);
		System.out.println(((Branch)mmm).getStaffList().get(3).getName()+" added to staff");
		mmm.addStaff("TY3", "Eve",false,true);
		System.out.println(((Branch)mmm).getStaffList().get(4).getName()+" added to staff");
		mmm.addStaff("TY4", "Fez",false,false);
		System.out.println(((Branch)mmm).getStaffList().get(5).getName()+" added to staff");
		mmm.addStaff("TR1", "Gil",true,true,"French",12.0);
		System.out.println(((Branch)mmm).getStaffList().get(6).getName()+" added to staff");
		mmm.addStaff("TR2", "Han",true,false,"French",12.0);
		System.out.println(((Branch)mmm).getStaffList().get(7).getName()+" added to staff");
		mmm.addStaff("TR3", "Kit",false,true,"German",12.0);
		System.out.println(((Branch)mmm).getStaffList().get(8).getName()+" added to staff");
		mmm.addStaff("TR4", "Lil",false,false,"German",12.0);
		System.out.println(((Branch)mmm).getStaffList().get(9).getName()+" added to staff");
		System.out.println();
		System.out.println("-------------Testing adding a job-----------------");
		System.out.println();
		System.out.println(mmm.addJob("Customer 1",true,false,""));
		System.out.println();
		System.out.println(mmm.addJob("Customer 2",false,true,""));
		System.out.println();
		System.out.println(mmm.addJob("Customer 3",true,false,"French"));
		System.out.println();
		System.out.println(mmm.addJob("Customer 4",true,true,""));
		System.out.println();
		System.out.println(mmm.addJob("Customer 5",false,false,"English"));
		System.out.println();

		System.out.println();

		System.out.println("------------Test Job completion--------------");

		System.out.println("\n----Test with valid job id 100 for 10 hours----\n");
		int jNo = 100;
		int hrs = 10;
		if(mmm.setJobDone(jNo,hrs)== -1)
		{
			System.out.println( "No such job");
		}
		else
		{
			System.out.println("Job Done.Cost of job is :£" +(mmm.getJobCost(jNo)));
		}
		System.out.println("\n----Test with valid job id 101 for 10 hours----\n");
		jNo = 101;
		hrs = 10;
		if(mmm.setJobDone(jNo,hrs)== -1)
		{
			System.out.println( "No such job");
		}
		else
		{
			System.out.println("Job Done.Cost of job is :£" +(mmm.getJobCost(jNo)));
		}

		System.out.println("\n----Test with invalid job id 1001----\n");

		jNo = 1000;
		if(mmm.setJobDone(jNo,hrs)== -1)
		{
			System.out.println( "No such job");
		}
		else
		{
			System.out.println("Job Done.Cost of job is :£" +(mmm.getJobCost(jNo)));
		}

	}
}
