package com.harish.leave;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.harish.main.LoginPage;

import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class EmployeeLogin {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Text totalLeave;
	private static Text leaveCons;
	private static Text leaveUnap;
	private static Text leaveApro;
	private static Text leaveRem;
	private static  int CONST_WIDTH =549;
	private static  int CONST_HEIGHT =350;

	public static void main(String[] args) {
		String ID = args[0];
		String TopName = args[1];
		String l = args[2];
		
		Display display = Display.getDefault();
		Shell shlEmployeeLogin = new Shell();
		shlEmployeeLogin.setSize(549, 350);
		shlEmployeeLogin.setText("Employee Login");
		shlEmployeeLogin.setLayout(new FormLayout());
		
		Label label = new Label(shlEmployeeLogin, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.left = new FormAttachment(0, 149);
		fd_label.right = new FormAttachment(0, 375);
		label.setLayoutData(fd_label);
		label.setText("Leave Management System");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		
		Composite composite = formToolkit.createComposite(shlEmployeeLogin, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(label, 22);
		fd_composite.right = new FormAttachment(100, -54);
		fd_composite.left = new FormAttachment(0, 50);
		composite.setLayoutData(fd_composite);
		formToolkit.paintBordersFor(composite);
		
		Label lblTotalLeaves = new Label(composite, SWT.NONE);
		lblTotalLeaves.setBounds(82, 39, 103, 15);
		formToolkit.adapt(lblTotalLeaves, true, true);
		lblTotalLeaves.setText("1. Total Leaves");
		
		Label lblLeaveConsumed = formToolkit.createLabel(composite, "2. Leave Consumed", SWT.NONE);
		lblLeaveConsumed.setBounds(82, 60, 114, 15);
		
		Label lblLeaveUnder = formToolkit.createLabel(composite, "3. Leave Under Approvale", SWT.NONE);
		lblLeaveUnder.setBounds(82, 81, 145, 15);
		
		Label lblLeaveApproved = formToolkit.createLabel(composite, "4. Leave Approved", SWT.NONE);
		lblLeaveApproved.setBounds(82, 102, 114, 15);
		
		Label lblLeaveRemains = formToolkit.createLabel(composite, "5. leave Remains", SWT.NONE);
		lblLeaveRemains.setBounds(82, 123, 103, 15);
		
		totalLeave = formToolkit.createText(composite, "New Text", SWT.NONE);
		totalLeave.setEditable(false);
		totalLeave.setText("26");
		totalLeave.setBounds(285, 39, 54, 15);
		
		leaveCons = formToolkit.createText(composite, "New Text", SWT.NONE);
		leaveCons.setEditable(false);
		int ConL = 26 - (Integer.parseInt(l));
		leaveCons.setText(String.valueOf(ConL));
		leaveCons.setBounds(285, 60, 54, 15);
		
		leaveUnap = formToolkit.createText(composite, "New Text", SWT.NONE);
		leaveUnap.setEditable(false);
		leaveUnap.setText("0");
		leaveUnap.setBounds(285, 81, 54, 15);
		
		leaveApro = formToolkit.createText(composite, "New Text", SWT.NONE);
		leaveApro.setEditable(false);
		leaveApro.setBounds(285, 102, 54, 15);
		leaveUnap.setText("0");
		
		leaveRem = formToolkit.createText(composite, "New Text", SWT.NONE);
		leaveRem.setEditable(false);
		leaveRem.setBounds(285, 123, 54, 15);
		leaveRem.setText(l);
		
		Label lblHarishBanote = formToolkit.createLabel(shlEmployeeLogin, "Harish banote", SWT.RIGHT);
		lblHarishBanote.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		fd_label.top = new FormAttachment(lblHarishBanote, 6);
		FormData fd_lblHarishBanote = new FormData();
		fd_lblHarishBanote.top = new FormAttachment(0, 10);
		fd_lblHarishBanote.right = new FormAttachment(100, -10);
		fd_lblHarishBanote.left = new FormAttachment(0, 53);
		lblHarishBanote.setLayoutData(fd_lblHarishBanote);
		lblHarishBanote.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblHarishBanote.setText(TopName);
		
		Button btnApplyLeave = formToolkit.createButton(shlEmployeeLogin, "Apply Leave ", SWT.NONE);
	
		fd_composite.bottom = new FormAttachment(100, -59);
		FormData fd_btnApplyLeave = new FormData();
		fd_btnApplyLeave.top = new FormAttachment(composite, 6);
		
		Label lblLeaveStatus = formToolkit.createLabel(composite, "Leave Status", SWT.NONE);
		lblLeaveStatus.setBounds(10, 10, 73, 15);
		lblLeaveStatus.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblLeaveStatus.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		btnApplyLeave.setLayoutData(fd_btnApplyLeave);
		
		Button btnLeaveReport = formToolkit.createButton(shlEmployeeLogin, "Leave Report", SWT.NONE);
		btnLeaveReport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LeaveReport.main(args);
			}
		});
		fd_btnApplyLeave.right = new FormAttachment(100, -275);
		FormData fd_btnLeaveReport = new FormData();
		fd_btnLeaveReport.top = new FormAttachment(btnApplyLeave, 0, SWT.TOP);
		fd_btnLeaveReport.left = new FormAttachment(btnApplyLeave, 6);
		btnLeaveReport.setLayoutData(fd_btnLeaveReport);
		
		Menu menu = new Menu(shlEmployeeLogin, SWT.BAR);
		shlEmployeeLogin.setMenuBar(menu);
		
		MenuItem mntmLogout = new MenuItem(menu, SWT.NONE);
		mntmLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				shlEmployeeLogin.close();
				LoginPage.main(null);
			
				
			}
		});
		mntmLogout.setText("Logout");
		int ApL =0;
		int UaL = 0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
			java.sql.PreparedStatement pr = cn.prepareStatement("select * from leaves where id =? and status = 'Accepted'");
			pr.setInt(1, Integer.parseInt(ID));
			ResultSet rs = pr.executeQuery();
			while(rs.next())
			{
				ApL++;
			}
			leaveApro.setText(Integer.toString(ApL));
			cn.close();
			
		}
		catch(Exception exp)
		{

		}
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection	cn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
			java.sql.PreparedStatement pr2 = cn2.prepareStatement("select * from leaves where id =? and status = 'Requested'");
			pr2.setInt(1, Integer.parseInt(ID));
			ResultSet rs2 = pr2.executeQuery();
			while(rs2.next())
			{
				UaL++;
			}
			leaveUnap.setText(Integer.toString(UaL));
			cn2.close();
		}
		catch(Exception exp2)
		{
			System.out.println(exp2);
		}
		btnApplyLeave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	
				if(l.equals("0"))
				{
					JOptionPane.showMessageDialog(null, "No leave remains you cannot apply for a leave.");
				}
				else
				{
					LeaveApply.main(args);
				}
			}
		});
		
		shlEmployeeLogin.addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				// TODO Auto-generated method stub
				Rectangle rect = shlEmployeeLogin.getBounds();
				if(rect.width != CONST_WIDTH || rect.height != CONST_WIDTH){
					shlEmployeeLogin.setBounds(rect.x, rect.y, CONST_WIDTH,CONST_HEIGHT);
				}
				
			}
			@Override
			public void controlMoved(ControlEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		shlEmployeeLogin.open();
		shlEmployeeLogin.layout();
		
		
		while (!shlEmployeeLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
