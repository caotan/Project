/**
 * 
 */
package fr.epita.iam.launcher;
/**
 * @author NgocTanCAO
 *
 */
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Login {
	private static Text txtUid;
	private static Text txtPass;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell shlLoginToSystem = new Shell();

		shlLoginToSystem.setSize(384, 210);
		shlLoginToSystem.setText("Login to system");
		
		org.eclipse.swt.graphics.Rectangle screenSize = display.getPrimaryMonitor().getBounds();
		shlLoginToSystem.setLocation((screenSize.width - shlLoginToSystem.getBounds().width) / 2, (screenSize.height - shlLoginToSystem.getBounds().height) / 2);
		
		Label label = new Label(shlLoginToSystem, SWT.NONE);
		label.setText("User Id");
		label.setBounds(45, 36, 55, 15);
		
		txtUid = new Text(shlLoginToSystem, SWT.BORDER);
		txtUid.setBounds(132, 33, 158, 21);
		
		Label label_1 = new Label(shlLoginToSystem, SWT.NONE);
		label_1.setText("Password");
		label_1.setBounds(45, 74, 55, 15);
		
		txtPass = new Text(shlLoginToSystem, SWT.BORDER);
		txtPass.setBounds(132, 71, 158, 21);
		
		Button btnLogin = new Button(shlLoginToSystem, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String id = txtUid.getText();
				String pass = txtPass.getText();
				if ( id.equals("admin") && pass.equals("password")) {
					shlLoginToSystem.setVisible(false);
					Main main = new Main();
					main.open();
					
				}
				else{
					
					JOptionPane.showMessageDialog(null, "Id or password incorect!");
				}
			}
		});
		btnLogin.setBounds(132, 107, 75, 25);
		btnLogin.setText("Login");

		shlLoginToSystem.open();
		shlLoginToSystem.layout();
		while (!shlLoginToSystem.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}