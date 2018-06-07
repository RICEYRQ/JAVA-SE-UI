package hit.edu.cn;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.ImageView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints.Key;

import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JButton reset;
	private final JButton login;
	private final NameBox name;
	private final PasBox pas;
	private boolean name_enter = false;
	//private final boolean name_ctrl = false;
	private boolean name_down = false;
	private boolean pas_enter = false;
	private boolean pas_up = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setBounds(88, 78, 72, 18);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("登录");
		label.setFont(new Font("黑体", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(164, 13, 91, 37);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(88, 126, 72, 18);
		contentPanel.add(label_1);
		
		name = new NameBox();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					name_enter = true;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					name_down = true;
				}
				//System.out.println(name_enter + " " + name_down);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (name_enter && !name_down) {
					toLogin();
				}
				if (name_down && !name_enter) {
					pas.requestFocus();
				}
				name_enter = false;
				name_down = false;
				//System.out.println(name_enter + " " + name_down);
			}
		});
		name.setBounds(174, 75, 132, 24);
		contentPanel.add(name);
		
		pas = new PasBox();
		pas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pas_enter = true;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					pas_up = true;
				}
				//System.out.println(pas_enter + " " + pas_up);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (pas_enter && !pas_up) {
					toLogin();
				}
				if (pas_up && !pas_enter) {
					name.requestFocus();
				}
				pas_enter = false;
				pas_up = false;
				//System.out.println(pas_enter + " " + pas_up);
			}
		});
		pas.setBounds(174, 120, 132, 24);
		contentPanel.add(pas);
		
		login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toLogin();
			}
		});
		login.setBounds(213, 192, 113, 27);
		login.setIcon(Util.getIcon2("image/OK.png", login, (float) 0.8));
		login.setHorizontalTextPosition(SwingConstants.RIGHT); 
		contentPanel.add(login);
		
		reset = new JButton("重置");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				pas.setText("");
			}
		});
		reset.setBounds(75, 192, 113, 27);
		reset.setIcon(Util.getIcon2("image/reset.png", reset, (float) 0.8));
		reset.setHorizontalTextPosition(SwingConstants.RIGHT); 
		contentPanel.add(reset);
		
		JButton showHidePas = new JButton();
		showHidePas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pas.setEchoChar((char) 0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pas.setEchoChar('*');
			}
		});
		showHidePas.setContentAreaFilled(true);
		showHidePas.setOpaque(false);
		//showHidePas.setBorder(null);
		showHidePas.setBounds(319, 117, 33, 27);
		showHidePas.setIcon(Util.getIcon("image/showpas.png", showHidePas));
		contentPanel.add(showHidePas);
		
		setResizable(false);
	}
	
	private void toLogin() {
		String na = name.getText();
		String pa = pas.getText();
		if (na.equals("")) {
			JOptionPane.showMessageDialog(contentPanel, "账号不能为空！");
			return;
		}
		if (pa.equals("")) {
			JOptionPane.showMessageDialog(contentPanel, "密码不能为空！");
			return;
		}
		if (na.equals("root") && pa.equals("123")) {
			Login.this.dispose();
			Show show = new Show();
			show.show();
		} else {
			JOptionPane.showMessageDialog(contentPanel, "用户名或密码错误！");
		}
	}
}
