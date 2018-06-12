package hit.edu.cn;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CancelAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static boolean enter = false;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CancelAdd dialog = new CancelAdd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CancelAdd(Add add) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager(); 
		manager.addKeyEventPostProcessor(new KeyEventPostProcessor() {  
	        public boolean postProcessKeyEvent(KeyEvent event) {  
	            if (event.getKeyCode() == KeyEvent.VK_ENTER) {  
	                CancelAdd.this.dispose();
	            }  
	            
	            return false;  
	        }  
	    });
		contentPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					enter = true;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (enter) {
					CancelAdd.this.dispose();
				}
				enter = false;
			}
		});
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("确定取消添加？");
			label.setBounds(14, 46, 404, 42);
			label.setFont(new Font("黑体", Font.BOLD, 18));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(label);
		}
		
		JButton confirm = new JButton("确定");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelAdd.this.dispose();
				add.cancelAdd();
			}
		});
		confirm.setBounds(219, 152, 113, 27);
		confirm.setIcon(Util.getIcon2("image/OK.png", confirm, (float) 0.8));
		confirm.setHorizontalTextPosition(SwingConstants.RIGHT); 
		
		contentPanel.add(confirm);
		
		JButton cancel = new JButton("取消");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelAdd.this.dispose();
			}
		});
		cancel.setBounds(51, 152, 113, 27);
		cancel.setIcon(Util.getIcon2("image/cancel.png", cancel, (float) 0.8));
		cancel.setHorizontalTextPosition(SwingConstants.RIGHT); 
		
		cancel.requestDefaultFocus();
		
		
		contentPanel.add(cancel);
		
		setResizable(false);
	}

}
