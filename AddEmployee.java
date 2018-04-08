package com.harish.admin;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Composite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class AddEmployee {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Table table;
	private static Text id;
	private static Text name;
	private static Text uname;
	private static Text pass;
	private static  int CONST_WIDTH =734;
	private static  int CONST_HEIGHT =463;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
	
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(734, 463);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		Composite composite = formToolkit.createComposite(shell, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(100, -10);
		fd_composite.right = new FormAttachment(0, 708);
		composite.setLayoutData(fd_composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(null);
		
		table = formToolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table.setBounds(239, 41, 445, 327);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(37);
		tableColumn.setText("Id");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(139);
		tableColumn_1.setText("Name");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(109);
		tableColumn_2.setText("Username");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(87);
		tableColumn_3.setText("Password");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(53);
		tableColumn_5.setText("Gender");
		
		TableColumn tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setWidth(100);
		tblclmnType.setText("Type");
		
		Label label = formToolkit.createSeparator(composite, SWT.NONE);
		label.setBounds(221, 0, 12, 414);
		
		Label lblEmployeeDetails = formToolkit.createLabel(composite, "Employee Details", SWT.NONE);
		lblEmployeeDetails.setBounds(243, 20, 96, 15);
		
		Composite composite_1 = formToolkit.createComposite(composite, SWT.BORDER);
		composite_1.setBounds(10, 10, 194, 380);
		formToolkit.paintBordersFor(composite_1);
		
		Label lblNameOfEmployee = formToolkit.createLabel(composite_1, "Name of Employee", SWT.NONE);
		lblNameOfEmployee.setBounds(10, 64, 106, 15);
		
		name = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		name.setText("");
		name.setBounds(10, 85, 170, 15);
		
		Label lblUsernameOfEmployee = formToolkit.createLabel(composite_1, "Username of Employee", SWT.NONE);
		lblUsernameOfEmployee.setBounds(10, 117, 131, 15);
		
		uname = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		uname.setText("");
		uname.setBounds(10, 138, 170, 15);
		
		Label lblPassword = formToolkit.createLabel(composite_1, "New Password", SWT.NONE);
		lblPassword.setBounds(10, 201, 87, 15);
		
		pass = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		pass.setText("");
		pass.setBounds(10, 222, 170, 15);
		
		Label lblGender = formToolkit.createLabel(composite_1, "Gender", SWT.NONE);
		lblGender.setBounds(10, 175, 55, 15);
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(73, 172, 91, 23);
		combo.add("Male");
		combo.add("Female");
		combo.select(0);
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		Label lblNewEmployeeDetails = formToolkit.createLabel(composite_1, "New Employee Details", SWT.NONE);
		lblNewEmployeeDetails.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewEmployeeDetails.setBounds(33, 0, 131, 15);
		
		Button btnAdd = new Button(composite_1, SWT.NONE);
		
		btnAdd.setBounds(10, 310, 75, 25);
		formToolkit.adapt(btnAdd, true, true);
		btnAdd.setText("Add");
		
		Button btnEdit = formToolkit.createButton(composite_1, "Update", SWT.NONE);
	
		btnEdit.setBounds(89, 310, 75, 25);
		
		Button btnDelete = formToolkit.createButton(composite_1, "Delete", SWT.NONE);
	
		btnDelete.setBounds(48, 341, 75, 25);
		
		Label lblEmployeeId = formToolkit.createLabel(composite_1, "Employee ID", SWT.NONE);
		lblEmployeeId.setBounds(10, 34, 75, 15);
		
		id = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		id.setText("");
		id.setBounds(89, 34, 91, 15);
		
		Label lblType = formToolkit.createLabel(composite_1, "Type", SWT.NONE);
		lblType.setBounds(10, 257, 32, 15);
		
		Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setBounds(48, 254, 91, 23);
		combo_1.add("Employee");
		combo_1.add("Admin");
		combo_1.select(0);
		formToolkit.adapt(combo_1);
		formToolkit.paintBordersFor(combo_1);
		
		Label lblFillCompleteDetails = formToolkit.createLabel(composite_1, "Fill Complete details.", SWT.NONE);
		lblFillCompleteDetails.setBounds(10, 283, 154, 15);
		lblFillCompleteDetails.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblFillCompleteDetails.setVisible(false);
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection	 cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
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
			}
			cn.close();
		}
		catch(Exception exp)
		{
			JOptionPane.showMessageDialog(null, "No records found!");
		}
		
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				id.setEnabled(true);
				name.setEnabled(true);
				combo.setEnabled(true);
				String ids  = id.getText().toString();
				String nm = name.getText().toString();
				String unm = uname.getText().toString();
				String pss = pass.getText().toString();
				String type = combo_1.getText().toString();
				String gender = combo.getText();
				
				if(nm.isEmpty()|| unm.isEmpty()|| pss.isEmpty() || ids.isEmpty())
					lblFillCompleteDetails.setVisible(true);
				else
				{
					lblFillCompleteDetails.setVisible(false);
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						java.sql.PreparedStatement pr = cn.prepareStatement("insert into employees values(?,?,?,?,?,?,?)");
						pr.setInt(1,Integer.parseInt(ids));
						pr.setString(2, nm);
						pr.setString(3, unm);
						pr.setString(4, pss);
						pr.setString(5, gender);
						pr.setString(6, type);
						pr.setInt(7, 26);
						int count = pr.executeUpdate();
						
						JOptionPane.showMessageDialog(null,count+" record  added.");
						
						id.setText("");
						name.setText("");
						uname.setText("");
						pass.setText("");
						cn.close();
					}
					catch(Exception exp1)
					{
						//System.out.println(exp1);
						JOptionPane.showMessageDialog(null,"Somthing went wrong ! Please insure that Employee ID should be unique.");
					}
					table.removeAll();
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	 cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						Statement st = cn1.createStatement();
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
						}
						cn1.close();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "No records found!");
					}
					}
			}
		});
		
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tb[] = table.getSelection();

				id.setEnabled(false);
				name.setEnabled(false);
				combo.setEnabled(false);
				id.setText(tb[0].getText(0));
				name.setText(tb[0].getText(1));
				uname.setText(tb[0].getText(2));
				pass.setText(tb[0].getText(3));
				combo.setText(tb[0].getText(4));
				combo_1.setText(tb[0].getText(5));
				
				
			}
		});
		
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ids  = id.getText().toString();
				String nm = name.getText().toString();
				String unm = uname.getText().toString();
				String pss = pass.getText().toString();
				String type = combo_1.getText().toString();

				if(nm.isEmpty()|| unm.isEmpty()|| pss.isEmpty() || ids.isEmpty())
					lblFillCompleteDetails.setVisible(true);
				else
				{
					lblFillCompleteDetails.setVisible(false);
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
						java.sql.PreparedStatement pr = cn.prepareStatement("update employees set  uname =? , pass =? , type =? where empid =? ");
					
						pr.setString(1, unm);
						pr.setString(2, pss);
						pr.setString(3, type);
						pr.setInt(4,Integer.parseInt(ids));
						int count = pr.executeUpdate();
						
						JOptionPane.showMessageDialog(null,count+" record  updated.");
						
						id.setText("");
						name.setText("");
						uname.setText("");
						pass.setText("");
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
						}
						cn1.close();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "No records found!");
					}
				}
			}
		});
		
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ids  = id.getText().toString();
				String nm = name.getText().toString();
				String unm = uname.getText().toString();
				String pss = pass.getText().toString();
			//	String type = combo_1.getText().toString();
				if(nm.isEmpty()|| unm.isEmpty()|| pss.isEmpty() || ids.isEmpty())
					lblFillCompleteDetails.setVisible(true);
				else
				{
					lblFillCompleteDetails.setVisible(false);
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection	cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
					java.sql.PreparedStatement pr = cn.prepareStatement("delete from employees where empid =?  ");
				
					pr.setInt(1,Integer.parseInt(id.getText()));
					int count = pr.executeUpdate();
					
					JOptionPane.showMessageDialog(null,count+" record  deleted.");
					
					id.setText("");
					name.setText("");
					uname.setText("");
					pass.setText("");
					
				}
				catch(Exception exp1)
				{
					System.out.println(exp1);
					JOptionPane.showMessageDialog(null,"Error in record! Please try again.");
				}
				
				table.removeAll();
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection	 cn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
					Statement st = cn1.createStatement();
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
					}
					cn1.close();
				}
				catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, "No records found!");
				}
				}
			}
		});
		
		shell.addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				// TODO Auto-generated method stub
				Rectangle rect = shell.getBounds();
				if(rect.width != CONST_WIDTH || rect.height != CONST_WIDTH){
					shell.setBounds(rect.x, rect.y, CONST_WIDTH,CONST_HEIGHT);
				}
				
			}
			@Override
			public void controlMoved(ControlEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		shell.open();
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
