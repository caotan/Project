/**
 * 
 */
package fr.epita.iam.launcher;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
/**
 * @author Administrator
 *
 */
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.services.UserDAO;


public class Main {

	protected Shell shlIdentityManagement;
	private Table tableUser;
	private Text txtUserId;
	private Text txtDisplayName;
	private Text txtAddress;
	private Text txtEmail;
	private UserDAO userDAO = new UserDAO();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void FillData(){
		try {
			tableUser.removeAll();
			for (User u : userDAO.findAll()) {
				TableItem tableItem = new TableItem(tableUser, SWT.None);
				tableItem.setText(new String[]{String.valueOf(u.getuId()), u.getdisplayName(), u.getaddress(), u.getEmail()});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(display);
		FillData();
		shlIdentityManagement.open();
		shlIdentityManagement.layout();
		while (!shlIdentityManagement.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	
		
	}

	private void ClearDataOfForm(){
		txtDisplayName.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtUserId.setText("");
	}
	
	private User GetDataOfForm(){
		User u = new User();
		u.setdisplayName(txtDisplayName.getText());
		u.setEmail(txtEmail.getText());
		u.setaddress(txtAddress.getText());
		if( txtUserId.getText()!= "") {
			u.setuId(Integer.parseInt(txtUserId.getText()));
		}
		
		return u;
	}
	
	private void SetDataForForm(User u){
		txtDisplayName.setText(u.getdisplayName());
		txtEmail.setText(u.getEmail());
		txtAddress.setText(u.getaddress());
		txtUserId.setText(String.valueOf(u.getuId()));
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents(Display display) {
		shlIdentityManagement = new Shell();
		shlIdentityManagement.setSize(586, 362);
		shlIdentityManagement.setText("Identity Management");
		
		org.eclipse.swt.graphics.Rectangle screenSize = display.getPrimaryMonitor().getBounds();
		shlIdentityManagement.setLocation((screenSize.width - shlIdentityManagement.getBounds().width) / 2, (screenSize.height - shlIdentityManagement.getBounds().height) / 2);
		
		
		final Button btnDelete = new Button(shlIdentityManagement, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = Integer.parseInt(txtUserId.getText());
				if (userDAO.delete(id)) {
					JOptionPane.showMessageDialog(null, "Delete user successfull");
					FillData();
					ClearDataOfForm();
					btnDelete.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Delete user successfull");
				}
			}
		});
		btnDelete.setBounds(91, 286, 75, 25);
		btnDelete.setText("Delete");
		btnDelete.setVisible(false);
		tableUser = new Table(shlIdentityManagement, SWT.BORDER | SWT.FULL_SELECTION);
		tableUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDelete.setVisible(true);
				TableItem[] selection = tableUser.getSelection();
				int id = Integer.parseInt(selection[0].getText());
				SetDataForForm(userDAO.find(id));
				
			}
		});
		tableUser.setBounds(10, 42, 550, 143);
		tableUser.setHeaderVisible(true);
		tableUser.setLinesVisible(true);
		
		TableColumn tblclmnUserId = new TableColumn(tableUser, SWT.NONE);
		tblclmnUserId.setWidth(100);
		tblclmnUserId.setText("User Id");
		
		TableColumn tblclmnNewColumn = new TableColumn(tableUser, SWT.NONE);
		tblclmnNewColumn.setWidth(112);
		tblclmnNewColumn.setText("Display Name");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(tableUser, SWT.NONE);
		tblclmnNewColumn_1.setWidth(147);
		tblclmnNewColumn_1.setText("Address");
		
		TableColumn tblclmnEmail = new TableColumn(tableUser, SWT.NONE);
		tblclmnEmail.setWidth(187);
		tblclmnEmail.setText("Email");
		
		Group grpInfomation = new Group(shlIdentityManagement, SWT.NONE);
		grpInfomation.setText("Infomation");
		grpInfomation.setBounds(10, 191, 550, 84);
		
		Label lblUserId = new Label(grpInfomation, SWT.NONE);
		lblUserId.setBounds(23, 22, 55, 15);
		lblUserId.setText("User Id");
		
		Label lblName = new Label(grpInfomation, SWT.NONE);
		lblName.setText("Name");
		lblName.setBounds(284, 22, 55, 15);
		
		Label lblAddress = new Label(grpInfomation, SWT.NONE);
		lblAddress.setText("Address");
		lblAddress.setBounds(23, 54, 79, 15);
		
		Label lblEmail = new Label(grpInfomation, SWT.NONE);
		lblEmail.setText("Email");
		lblEmail.setBounds(284, 54, 55, 15);
		
		txtUserId = new Text(grpInfomation, SWT.BORDER);
		txtUserId.setEditable(false);
		txtUserId.setBounds(103, 19, 158, 21);
		
		txtAddress = new Text(grpInfomation, SWT.BORDER);
		txtAddress.setBounds(103, 51, 158, 21);
		
		txtDisplayName = new Text(grpInfomation, SWT.BORDER);
		txtDisplayName.setBounds(345, 16, 158, 21);
		
		txtEmail = new Text(grpInfomation, SWT.BORDER);
		txtEmail.setBounds(345, 48, 158, 21);
		
		
		
		Button btnCreateNewUser = new Button(shlIdentityManagement, SWT.NONE);
		btnCreateNewUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDelete.setVisible(false);
				ClearDataOfForm();
				txtAddress.setFocus();
				
			}
		});
		btnCreateNewUser.setBounds(10, 10, 116, 25);
		btnCreateNewUser.setText("Create new user");
		
		Button btnSave = new Button(shlIdentityManagement, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				User u = GetDataOfForm();
				
				if (u.getuId() == 0) {
					if (userDAO.create(u)){
						JOptionPane.showMessageDialog(null, "Create user successfull");
						FillData();
						btnDelete.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Create user fail");
					}
				}
				else{
					if (userDAO.edit(u)){
						JOptionPane.showMessageDialog(null, "Edit user successfull");
						FillData();
						btnDelete.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Edit user fail");
					}
				}
				
				ClearDataOfForm();
			}
		});
		btnSave.setBounds(10, 286, 75, 25);
		btnSave.setText("Save");
		
		

	}
}
