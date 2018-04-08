package com.harish.admin;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.harish.main.LoginPage;
import com.ibm.icu.text.SimpleDateFormat;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class AdminLogin {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Table table;
	private static  int CONST_WIDTH =590;
	private static  int CONST_HEIGHT =380;
	private static String cnt="";
	private static String id;
	private static String fromD;
	private static String toD;
	private static int leaves;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		String TopName = args[1];
		Display display = Display.getDefault();
		Shell shlAdminLogin = new Shell();
		shlAdminLogin.setSize(590, 380);
		shlAdminLogin.setText("Admin Login");
		shlAdminLogin.setLayout(new FormLayout());
		
		Menu menu = new Menu(shlAdminLogin, SWT.BAR);
		shlAdminLogin.setMenuBar(menu);
		
		MenuItem mntmAction = new MenuItem(menu, SWT.CASCADE);
		mntmAction.setText("Action");
		
		Menu menu_1 = new Menu(mntmAction);
		mntmAction.setMenu(menu_1);
		
		MenuItem mntmAdd = new MenuItem(menu_1, SWT.NONE);
		mntmAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddEmployee.main(null);
			}
		});
		mntmAdd.setText("Add Employee");
		
		MenuItem mntmViewEmployee = new MenuItem(menu_1, SWT.NONE);
		mntmViewEmployee.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ViewEmployee.main(null);
			}
		});
		mntmViewEmployee.setText("View Employee");
		
		MenuItem mntmLogout = new MenuItem(menu, SWT.NONE);
		mntmLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlAdminLogin.close();
				LoginPage.main(null);
			}
		});
		mntmLogout.setText("Logout");
		
		Composite composite = formToolkit.createComposite(shlAdminLogin, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(0, 281);
		fd_composite.right = new FormAttachment(0, 564);
		composite.setLayoutData(fd_composite);
		formToolkit.paintBordersFor(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Leave Management System");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		label.setBounds(158, 29, 226, 23);
		formToolkit.adapt(label, true, true);
		
		Label label_1 = formToolkit.createLabel(composite, "Harish banote", SWT.RIGHT);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_1.setBounds(122, 10, 418, 15);
		label_1.setText(TopName);
		
		table = formToolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL | SWT.MULTI);
		table.setBounds(45, 91, 455, 166);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(59);
		tableColumn.setText("Emp ID");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(66);
		tableColumn_1.setText("Name");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(64);
		tableColumn_2.setText("From");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(55);
		tableColumn_3.setText("To");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(124);
		tableColumn_4.setText("Reason");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(86);
		tableColumn_5.setText("Status");
		
		TableColumn tblclmnCount = new TableColumn(table, SWT.NONE);
		tblclmnCount.setWidth(38);
		tblclmnCount.setText("Count");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from leaves where status ='Requested'");
			while(rs.next())
			{
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(0,Integer.toString(rs.getInt(1)));
				tableItem.setText(1,rs.getString(3));
				tableItem.setText(2,rs.getDate(4).toString());
				tableItem.setText(3,rs.getDate(5).toString());
				tableItem.setText(4,rs.getString(6));
				tableItem.setText(5,rs.getString(7));
				tableItem.setText(6,Integer.toString(rs.getInt(2)));
			}
			cn.close();
		}
		catch(Exception exp)
		{
			System.out.println(exp);
		}

		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tb[] = table.getSelection();
				 id = tb[0].getText(0);
				 fromD = tb[0].getText(2);
				 toD = tb[0].getText(3);
				cnt  = tb[0].getText(6);
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
					java.sql.PreparedStatement pr = cn.prepareStatement("select * from employees where empid =? ");
				
					pr.setString(1, id);
					ResultSet rs = pr.executeQuery();
					while(rs.next())
					{
						leaves = rs.getInt(7);
					}
					cn.close();
				}
				catch(Exception exp4)
				{
					System.out.println(exp4);
				}
				 
			}
		
		});

		
		Button btnAccept = formToolkit.createButton(shlAdminLogin, "Accept", SWT.NONE);
		btnAccept.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	
				if(cnt.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please select any record first.");
				}
				else
				{
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						java.sql.PreparedStatement pr = cn.prepareStatement("update leaves set  status ='Accepted' where count =? ");
				
						pr.setInt(1,Integer.parseInt(cnt));
						int count = pr.executeUpdate();
						
						JOptionPane.showMessageDialog(null,count+" leave is  aproved.");
						cn.close();
						
					
					}
					catch(Exception exp1)
					{
						JOptionPane.showMessageDialog(null,"Error in record! Please try again.");
					}
					
					SimpleDateFormat myFormate = new SimpleDateFormat("yyyy-mm-dd");
					
					try
					{
						java.util.Date d1 = myFormate.parse(fromD);
						java.util.Date d2 = myFormate.parse(toD);
						long diff = d2.getTime()- d1.getTime();
						int remain = (int) (leaves - (diff/(1000*60*60*24)));
						Class.forName("com.mysql.jdbc.Driver");
						Connection	cn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						java.sql.PreparedStatement pr3 = cn3.prepareStatement("update employees set  leaves =? where empid =? ");
						pr3.setInt(1, remain);
						pr3.setInt(2, Integer.parseInt(id));
						pr3.executeUpdate();
					}
					catch(Exception exp2)
					{
						JOptionPane.showMessageDialog(null, "Something went wrong try again.");
					}
					
					table.removeAll();
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	 cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						Statement st = cn1.createStatement();
						ResultSet rs = st.executeQuery("select * from leaves where status ='Requested'");
						while(rs.next())
						{
							TableItem tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText(0,Integer.toString(rs.getInt(1)));
							tableItem.setText(1,rs.getString(3));
							tableItem.setText(2,rs.getDate(4).toString());
							tableItem.setText(3,rs.getDate(5).toString());
							tableItem.setText(4,rs.getString(6));
							tableItem.setText(5,rs.getString(7));
							tableItem.setText(6,rs.getString(2));
						}
						cn1.close();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "No records found!");
					}
				}
				cnt ="";
			}
		});
		FormData fd_btnAccept = new FormData();
		fd_btnAccept.left = new FormAttachment(0, 215);
		fd_btnAccept.top = new FormAttachment(composite, 6);
		
		Label lblLeaveRequest = formToolkit.createLabel(composite, "Leave Request", SWT.NONE);
		lblLeaveRequest.setBounds(44, 70, 91, 15);
		btnAccept.setLayoutData(fd_btnAccept);
		
		Button btnReject = formToolkit.createButton(shlAdminLogin, "Reject", SWT.NONE);
		btnReject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(cnt.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please select any record first.");
				}
				else
				{
					
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						java.sql.PreparedStatement pr = cn.prepareStatement("update leaves set  status ='Rejected' where count =? ");
				
						pr.setInt(1,Integer.parseInt(cnt));
						int count = pr.executeUpdate();
						
						JOptionPane.showMessageDialog(null,count+" leave is  rejected.");
					
						cn.close();
					}
					catch(Exception exp1)
					{
						//System.out.println(exp1);
						JOptionPane.showMessageDialog(null,"Error in record! Please try again.");
					}
					
					table.removeAll();
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	 cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						Statement st = cn1.createStatement();
						ResultSet rs = st.executeQuery("select * from leaves where status ='Requested'");
						while(rs.next())
						{
							TableItem tableItem = new TableItem(table, SWT.NONE);
							tableItem.setText(0,Integer.toString(rs.getInt(1)));
							tableItem.setText(1,rs.getString(3));
							tableItem.setText(2,rs.getDate(4).toString());
							tableItem.setText(3,rs.getDate(5).toString());
							tableItem.setText(4,rs.getString(6));
							tableItem.setText(5,rs.getString(7));
							tableItem.setText(6,rs.getString(2));
						}
						cn1.close();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "No records found!");
					}
				}}
		});
		fd_btnAccept.right = new FormAttachment(100, -300);
		FormData fd_btnReject = new FormData();
		fd_btnReject.top = new FormAttachment(btnAccept, 0, SWT.TOP);
		fd_btnReject.left = new FormAttachment(btnAccept, 17);
		fd_btnReject.right = new FormAttachment(100, -224);
		btnReject.setLayoutData(fd_btnReject);

		
		shlAdminLogin.addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				// TODO Auto-generated method stub
				Rectangle rect = shlAdminLogin.getBounds();
				if(rect.width != CONST_WIDTH || rect.height != CONST_WIDTH){
					shlAdminLogin.setBounds(rect.x, rect.y, CONST_WIDTH,CONST_HEIGHT);
				}
				
			}
			@Override
			public void controlMoved(ControlEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		shlAdminLogin.open();
		shlAdminLogin.layout();
		while (!shlAdminLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
