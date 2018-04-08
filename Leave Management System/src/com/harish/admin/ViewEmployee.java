package com.harish.admin;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Table;
import java.sql.*;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.wb.swt.SWTResourceManager;
public class ViewEmployee {
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
		Shell shlAdminLogin = new Shell();
		shlAdminLogin.setSize(550, 350);
		shlAdminLogin.setText("Admin Login");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlAdminLogin, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(26, 43, 478, 244);
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = formToolkit.createTable(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL | SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		formToolkit.paintBordersFor(table);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE );
		tblclmnId.setMoveable(true);
		tblclmnId.setWidth(37);
		tblclmnId.setText("Id");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setMoveable(true);
		tableColumn_1.setWidth(108);
		tableColumn_1.setText("Name");
		
		TableColumn tblclmnUsername = new TableColumn(table, SWT.NONE);
		tblclmnUsername.setMoveable(true);
		tblclmnUsername.setWidth(86);
		tblclmnUsername.setText("Username");
		
		TableColumn tblclmnPassword = new TableColumn(table, SWT.NONE);
		tblclmnPassword.setMoveable(true);
		tblclmnPassword.setWidth(76);
		tblclmnPassword.setText("Password");
		
		TableColumn tblclmnNoOfLeaves = new TableColumn(table, SWT.NONE);
		tblclmnNoOfLeaves.setMoveable(true);
		tblclmnNoOfLeaves.setWidth(61);
		tblclmnNoOfLeaves.setText("Gender");
		
		TableColumn tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setWidth(60);
		tblclmnType.setText("Type");
		
		TableColumn tblclmnGender = new TableColumn(table, SWT.NONE);
		tblclmnGender.setMoveable(true);
		tblclmnGender.setWidth(88);
		tblclmnGender.setText("No. of Leaves");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from employees");
			while(rs.next())
			{
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(0,Integer.toString(rs.getInt(1)));
				tableItem.setText(1,rs.getString(2));
				tableItem.setText(2,rs.getString(3));
				tableItem.setText(3,rs.getString(4));
				tableItem.setText(4,rs.getString(5));
				tableItem.setText(5,rs.getString(6));
				tableItem.setText(6,Integer.toString(rs.getInt(7)));
			}
			cn.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, "No records found.");
		}
		
		Label lblEmployeDetails = formToolkit.createLabel(shlAdminLogin, "Employe Details", SWT.NONE);
		lblEmployeDetails.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblEmployeDetails.setBounds(27, 22, 96, 15);

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
