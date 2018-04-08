package com.harish.leave;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Composite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class LeaveApply {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Text reason;
	private static  int CONST_WIDTH =550;
	private static  int CONST_HEIGHT =350;
	private static Text Empname;
	private static Text Empid;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		String id = args[0];
		String name = args[1];
		
		Display display = Display.getDefault();
		Shell shlEmployeeLogin = new Shell();
		shlEmployeeLogin.setSize(550, 350);
		shlEmployeeLogin.setText("Employee Login");
		
		Composite composite = formToolkit.createComposite(shlEmployeeLogin, SWT.BORDER);
		composite.setBounds(10, 37, 514, 264);
		formToolkit.paintBordersFor(composite);
		
		Label lblFromDate = formToolkit.createLabel(composite, "From date", SWT.NONE);
		lblFromDate.setBounds(67, 99, 55, 15);
		
		Label lblToDate = formToolkit.createLabel(composite, "To date", SWT.NONE);
		lblToDate.setBounds(275, 99, 55, 15);
		
		Label lblReason = formToolkit.createLabel(composite, "Reason", SWT.NONE);
		lblReason.setBounds(67, 129, 55, 15);
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(144, 90, 80, 24);
		formToolkit.adapt(dateTime);
		formToolkit.paintBordersFor(dateTime);
		
		DateTime dateTime_1 = new DateTime(composite, SWT.BORDER);
		dateTime_1.setBounds(336, 90, 80, 24);
		formToolkit.adapt(dateTime_1);
		formToolkit.paintBordersFor(dateTime_1);
		
		reason = formToolkit.createText(composite, "New Text", SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		reason.setText("");
		reason.setBounds(144, 129, 272, 64);
		
		Label lblEnterAValid = formToolkit.createLabel(composite, "Enter a valid reason.", SWT.NONE);
		lblEnterAValid.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblEnterAValid.setBounds(144, 199, 117, 15);
		lblEnterAValid.setVisible(false);
		
		Button btnSubmit = formToolkit.createButton(composite, "Submit", SWT.NONE);
		btnSubmit.setBounds(185, 220, 75, 25);
		
		Label lblName = formToolkit.createLabel(composite, "Name", SWT.NONE);
		lblName.setBounds(67, 59, 55, 15);
		
		Empname = formToolkit.createText(composite, "New Text", SWT.NONE);
		Empname.setEditable(false);
		Empname.setText(name);
		Empname.setBounds(144, 59, 151, 15);
		
		Label lblEmpId = formToolkit.createLabel(composite, "Emp ID", SWT.NONE);
		lblEmpId.setBounds(67, 25, 55, 15);
		
		Empid = formToolkit.createText(composite, "New Text", SWT.NONE);
		Empid.setEditable(false);
		Empid.setText(id);
		Empid.setBounds(144, 25, 151, 15);
		
		Button btnCancel = formToolkit.createButton(composite, "Cancel", SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlEmployeeLogin.close();
			}
		});
		btnCancel.setBounds(275, 220, 75, 25);
		
		Label lblLeaveApply = formToolkit.createLabel(shlEmployeeLogin, "Leave Apply", SWT.NONE);
		lblLeaveApply.setBounds(22, 16, 96, 15);
		lblLeaveApply.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblLeaveApply.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String rsn = reason.getText().toString();
				String fromDate = dateTime.getYear()+"-"+dateTime.getMonth()+"-"+dateTime.getDay();
				String toDate = dateTime_1.getYear()+"-"+dateTime_1.getMonth()+"-"+dateTime_1.getDay();
				
				if(rsn.isEmpty())
				{
					lblEnterAValid.setVisible(true);
				}
					else
				{
					lblEnterAValid.setVisible(false);
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						java.sql.PreparedStatement pr = cn.prepareStatement("insert into leaves(id,name,fromdate,todate,reason,status) values(?,?,?,?,?,?)");
						pr.setInt(1,Integer.parseInt(args[0]));
						pr.setString(2, name);
						pr.setDate(3, Date.valueOf(fromDate));
						pr.setDate(4, Date.valueOf(toDate));
						pr.setString(5, rsn);
						pr.setString(6, "Requested");
						int count = pr.executeUpdate();
	
						JOptionPane.showMessageDialog(null,count+" leave applied.");
						reason.setText("");
						cn.close();
					}
					catch(Exception exp1)
					{
						JOptionPane.showMessageDialog(null,"Something went wrong!");
					}
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
