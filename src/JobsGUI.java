import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Provide a GUI interface for the job submission system.
 * Only shows a selection of possible functions: add job, list jobs 
 * waiting record job done.
 * 
 * @author A.A.Marczyk
 * @version 27/02/08
 */
public class JobsGUI 
{
	private Manager mmm ;
	private JFrame myFrame = new JFrame("Jobs GUI");
	private Container contentPane = myFrame.getContentPane();

	private JButton quitButton = new JButton("Quit");
	private JButton addButton = new JButton("Add Job");
	private JButton clearButton = new JButton("Clear List");

	private JTextArea jobInfoArea = new JTextArea();

	private JLabel lblCustomerName = new JLabel("Customer's name");
	private JTextField txtCustomerName = new JTextField();

	private JLabel lblJobReq = new JLabel("Job Requirements");
	private JCheckBox chkOnsite = new JCheckBox("On site");
	private JCheckBox chkShorthand = new JCheckBox("Shorthand");
	private JCheckBox chkTranslation = new JCheckBox("Translation");



	private JPanel eastPanel = new JPanel();
	private JPanel westPanel = new JPanel(); 
	private JPanel centerPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JobsGUI j = new JobsGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JobsGUI()
	{
		mmm= new Branch();
		addAllStaff();
		makeFrame();
		makeMenus(myFrame);
	}

	private void addAllStaff()
	{
		// add all branch staff
		mmm.addStaff("CL1", "Ann");
		mmm.addStaff("CL2", "Bob");
		mmm.addStaff("TY1", "Che",true,true);
		mmm.addStaff("TY2", "Dan",true,false);
		mmm.addStaff("TY3", "Eve",false,true);
		mmm.addStaff("TY4", "Fez",false,false);
		mmm.addStaff("TR1", "Gil",true,true,"French",12.0);
		mmm.addStaff("TR2", "Han",true,false,"French",12.0);
		mmm.addStaff("TR3", "Kit",false,true,"German",12.0);
		mmm.addStaff("TR4", "Lil",false,false,"German",12.0);




	}

	/**
	 * Create the main frame's menu bar.
	 */
	private void makeMenus(JFrame frame)
	{
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		// create the File menu
		JMenu fileMenu = new JMenu("Jobs");
		menubar.add(fileMenu);

		JMenuItem addJob = new JMenuItem("Add Job");
		addJob.addActionListener(new AddJob());
		fileMenu.add(addJob);

		JMenuItem doneItem = new JMenuItem("Job Done");
		doneItem.addActionListener(new DoneHandler());
		fileMenu.add(doneItem);

		JMenuItem listWaiting = new JMenuItem("List waiting");
		listWaiting.addActionListener(new ListWaiting());
		fileMenu.add(listWaiting);

	}

	/**
	 * Create the Swing frame and its content.
	 */
	private void makeFrame()
	{    
		contentPane.setLayout(new BorderLayout());
		contentPane.add(eastPanel, BorderLayout.EAST);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		contentPane.add(westPanel, BorderLayout.WEST);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(northPanel, BorderLayout.NORTH);
		// set panel layout and add components
		eastPanel.setLayout(new GridLayout(4,1));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtCustomerName.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter company name", "Alert", JOptionPane.ERROR_MESSAGE);
				else if(!(chkOnsite.isSelected()||chkShorthand.isSelected()||chkTranslation.isSelected()))
					JOptionPane.showMessageDialog(null, "Please select a service", "Alert", JOptionPane.ERROR_MESSAGE);
				else
				{
					String lang ="";
					if(chkTranslation.isSelected())
					{
						lang= JOptionPane.showInputDialog("Enter translation language:");
					}
					String message = mmm.addJob(txtCustomerName.getText(),chkOnsite.isSelected(),chkShorthand.isSelected(),lang);
					JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jobInfoArea.setText(null);
				jobInfoArea.setVisible(false);
			}
		});

		eastPanel.add(addButton);
		eastPanel.add(clearButton);
		eastPanel.setVisible(false);
		centerPanel.add(jobInfoArea);

		westPanel.setLayout(new GridLayout(4,1));
		westPanel.add(lblJobReq);
		westPanel.add(chkOnsite);
		westPanel.add(chkShorthand);
		westPanel.add(chkTranslation);
		westPanel.setVisible(false);

		northPanel.setLayout(new GridLayout(2,1));
		northPanel.add(lblCustomerName);
		northPanel.add(txtCustomerName);
		northPanel.setMinimumSize(new Dimension(400, 100));
		northPanel.setVisible(false);

		jobInfoArea.setMinimumSize(new Dimension(40, 100));
		centerPanel.setLayout(new GridLayout(1,1));
		centerPanel.add(jobInfoArea);
		centerPanel.setVisible(false);

		southPanel.add(quitButton);
		quitButton.setVisible(true);
		quitButton.addActionListener(new QuitButtonHandler());

		eastPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		westPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		northPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// building is done - arrange the components and show        
		myFrame.pack();
		myFrame.setSize(300, 300);
		myFrame.setVisible(true);

	}

	private void makeTypes()
	{
		westPanel.setVisible(false);
		centerPanel.setVisible(false);
		northPanel.setVisible(false);
		contentPane.add(westPanel, BorderLayout.WEST);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(northPanel, BorderLayout.NORTH);
		// set panel layout and add components
		centerPanel.setLayout(new FlowLayout());
		northPanel.setLayout(new GridLayout(4,1));

	}


	private class DoneHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{ 
			String result = "";
			String inputValue = JOptionPane.showInputDialog("Job No ?");
			int jNo = Integer.parseInt(inputValue);
			String hrsValue = JOptionPane.showInputDialog("Hours ?: ");
			int hrs = Integer.parseInt(hrsValue);
			if(mmm.setJobDone(jNo,hrs)== -1)
			{
				result = "No such job";
			}
			else
			{
				result = "Job Done.Cost of job is :£" + 
						(mmm.getJobCost(jNo));
			}

			JOptionPane.showMessageDialog(myFrame,result);    
		}
	}


	private class QuitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{ 
			int answer = JOptionPane.showConfirmDialog(myFrame,
					"Are you sure you want to quit?","Finish",
					JOptionPane.YES_NO_OPTION);
			// closes the application
			if (answer == JOptionPane.YES_OPTION)
			{
				System.exit(0); //closes the application
			}              
		}
	}

	private class AddJob implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{ 

			eastPanel.setVisible(true);
			addButton.setVisible(true);
			clearButton.setVisible(false);
			westPanel.setVisible(true);
			northPanel.setVisible(true);
			lblCustomerName.setVisible(true);
			txtCustomerName.setVisible(true);

		}
	}

	private class ListWaiting implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{ 
			eastPanel.setVisible(true);
			addButton.setVisible(false);
			clearButton.setVisible(true);
			westPanel.setVisible(false);
			northPanel.setVisible(false);
			lblCustomerName.setVisible(false);
			txtCustomerName.setVisible(false);
			centerPanel.setVisible(true);
			jobInfoArea.setVisible(true);
			jobInfoArea.setText(mmm.getJobsWaiting());
		}
	}

}

