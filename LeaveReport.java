package com.harish.leave;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class LeaveReport {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Table table;
	private static  int CONST_WIDTH =550;
	private static  int CONST_HEIGHT =350;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlEmployeeLogin = new Shell();
		shlEmployeeLogin.setSize(550, 350);
		shlEmployeeLogin.setText("Employee Login");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlEmployeeLogin, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(38, 48, 459, 225);
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = formToolkit.createTable(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL | SWT.MULTI);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(59);
		tblclmnNewColumn.setText("Emp ID");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(66);
		tblclmnNewColumn_1.setText("Name");
		
		TableColumn tblclmnFrom = new TableColumn(table, SWT.NONE);
		tblclmnFrom.setWidth(64);
		tblclmnFrom.setText("From");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(55);
		tblclmnNewColumn_2.setText("To");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(124);
		tblclmnNewColumn_3.setText("Reason");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(86);
		tblclmnNewColumn_5.setText("Status");
		
		scrolledComposite.setContent(table);
		
		Label lblLeaveReport = formToolkit.createLabel(shlEmployeeLogin, "Leave Report", SWT.NONE);
		lblLeaveReport.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblLeaveReport.setBounds(38, 27, 81, 15);
		//scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
			java.sql.PreparedStatement pr = cn.prepareStatement("select * from leaves where id =?");
			pr.setInt(1,Integer.valueOf(args[0]));
			
			ResultSet rs = pr.executeQuery();
			while(rs.next())
			{
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(0,Integer.toString(rs.getInt(1)));
				tableItem.setText(1,rs.getString(3));
				tableItem.setText(2,rs.getDate(4).toString());
				tableItem.setText(3,rs.getDate(5).toString());
				tableItem.setText(4,rs.getString(6));
				tableItem.setText(5,rs.getString(7));
			}
			cn.close();
			
		}
		catch(Exception exp1)
		{
			JOptionPane.showMessageDialog(null,"No record found.");
		}
		
		
		
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
