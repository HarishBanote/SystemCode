package com.harish.main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import com.harish.admin.AdminLogin;
import com.harish.leave.EmployeeLogin;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class LoginPage {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Text uname;
	private static Text pass;
	private static  int CONST_WIDTH =550;
	private static  int CONST_HEIGHT =350;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlLoginPage = new Shell();
		shlLoginPage.setSize(550, 350);
		shlLoginPage.setText("Login Page");
		shlLoginPage.setLayout(new FormLayout());
		
		Composite composite = formToolkit.createComposite(shlLoginPage, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0, 21);
		fd_composite.bottom = new FormAttachment(100, -18);
		fd_composite.right = new FormAttachment(100, -29);
		composite.setLayoutData(fd_composite);
		formToolkit.paintBordersFor(composite);
		
		Label lblLogin = new Label(composite, SWT.NONE);
		lblLogin.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblLogin.setBounds(219, 10, 48, 26);
		formToolkit.adapt(lblLogin, true, true);
		lblLogin.setText("Login");
		
		Label lblUserName = new Label(composite, SWT.NONE);
		lblUserName.setBounds(136, 50, 81, 15);
		formToolkit.adapt(lblUserName, true, true);
		lblUserName.setText("User Name    :");
		
		Label lblPassword = new Label(composite, SWT.NONE);
		lblPassword.setBounds(137, 82, 80, 15);
		formToolkit.adapt(lblPassword, true, true);
		lblPassword.setText("Password       :");
		
		Label lblUserType = new Label(composite, SWT.NONE);
		lblUserType.setBounds(136, 119, 81, 15);
		formToolkit.adapt(lblUserType, true, true);
		lblUserType.setText("User Type        :");
		
		uname = formToolkit.createText(composite, "New Text", SWT.NONE);
		uname.setText("");
		uname.setBounds(223, 50, 102, 15);
		
		pass = formToolkit.createText(composite, "New Text", SWT.PASSWORD);
		pass.setText("");
		pass.setBounds(223, 82, 102, 15);
		
		Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(223, 111, 102, 23);
		combo.add("Admin");
		combo.add("Employee");
		combo.select(0);
		formToolkit.adapt(combo);
		formToolkit.paintBordersFor(combo);
		
		Label lblFillCompleteDetails = formToolkit.createLabel(composite, "Fill complete details.", SWT.NONE);
		lblFillCompleteDetails.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblFillCompleteDetails.setBounds(223, 140, 108, 15);
		lblFillCompleteDetails.setVisible(false);
		
		Label lblNewLabel = new Label(shlLoginPage, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(composite, 126);
		
		Button btnLogin = formToolkit.createButton(composite, "Login", SWT.NONE);
		btnLogin.setBounds(223, 161, 51, 25);
		
		Button btnCancel = formToolkit.createButton(composite, "Cancel", SWT.NONE);
		btnCancel.setBounds(277, 161, 48, 25);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLoginPage.close();
			}
		});
		
			
			btnLogin.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String un = uname.getText().toString();
					String pa = pass.getText().toString();
					String type = combo.getText().toString();
					if(un.isEmpty()|| pa.isEmpty())
					{
						lblFillCompleteDetails.setVisible(true);
					}
					else
					{
						lblFillCompleteDetails.setVisible(false);
						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_sys","root","bunny");
							java.sql.PreparedStatement pr = cn.prepareStatement("select * from employees where uname =? and pass=? and type =?");
							pr.setString(1,un);
							pr.setString(2,pa);
							pr.setString(3, type);
							ResultSet rs = pr.executeQuery();
							if(rs.next())
							{
								String ty =rs.getString(6).toString();
								String nm []= {Integer.toString((rs.getInt(1))),rs.getString(2),Integer.toString(rs.getInt(7))};
								if(ty.equals("Admin"))
								{
									
									shlLoginPage.close();
									AdminLogin.main(nm);
								}
								else
								{
									shlLoginPage.close();
									EmployeeLogin.main(nm);
								}
							}
							else
							{
								pass.setText("");
								JOptionPane.showMessageDialog(null, "Wrong User name or Password ! Select proper Type.");
							}
						}
						catch(Exception exp)
						{
							System.out.println(exp);
						}
					}
				}
			});
		fd_lblNewLabel.right = new FormAttachment(100, -403);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("New Label");
		
		Composite composite_1 = formToolkit.createComposite(shlLoginPage, SWT.BORDER);
		fd_composite.top = new FormAttachment(composite_1, 18);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(0, 18);
		fd_composite_1.bottom = new FormAttachment(100, -243);
		fd_composite_1.left = new FormAttachment(0, 21);
		fd_composite_1.right = new FormAttachment(100, -29);
		composite_1.setLayoutData(fd_composite_1);
		formToolkit.paintBordersFor(composite_1);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(108, 10, 259, 23);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label.setText("Leave Management System");
		label.setFont(SWTResourceManager.getFont("SimSun-ExtB", 14, SWT.BOLD));
		formToolkit.adapt(label, true, true);

		shlLoginPage.addControlListener(new ControlListener() {
			
			@Override
			public void controlResized(ControlEvent e) {
				// TODO Auto-generated method stub
				Rectangle rect = shlLoginPage.getBounds();
				if(rect.width != CONST_WIDTH || rect.height != CONST_WIDTH){
					shlLoginPage.setBounds(rect.x, rect.y, CONST_WIDTH,CONST_HEIGHT);
				}
				
			}
			@Override
			public void controlMoved(ControlEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		shlLoginPage.open();
		shlLoginPage.layout();
		while (!shlLoginPage.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
