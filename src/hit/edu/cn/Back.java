package hit.edu.cn;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Back extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private JTextField path;
	private JLabel progress;
	private JProgressBar progressBar;
	private JLabel value;
	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private Show frame;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Back dialog = new Back();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Back(Show show) {
		
		frame = show;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("文件还原");
		label.setFont(new Font("黑体", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(164, 13, 91, 37);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("备份位置：");
		label_1.setBounds(14, 78, 91, 18);
		contentPanel.add(label_1);
		
		path = new JTextField();
		path.setBounds(106, 75, 190, 24);
		contentPanel.add(path);
		path.setColumns(10);
		
		JButton choose = new JButton("选择");
		choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int option = jFileChooser.showOpenDialog(Back.this);
				if (option != JFileChooser.APPROVE_OPTION)
		            return;
				File file = jFileChooser.getSelectedFile();
				path.setText(file.toString());
			}
		});
		choose.setBounds(310, 74, 105, 27);
		choose.setIcon(Util.getIcon2("/image/path.png", choose, (float) 0.8));
		choose.setHorizontalTextPosition(SwingConstants.RIGHT); 
		contentPanel.add(choose);
		
		JButton copy = new JButton("开始还原");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (path.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPanel, "请先选择备份所在位置！");
					return;
				}
				Thread thread = new Thread(Back.this);
			    thread.start();
			}
		});
		copy.setBounds(151, 131, 113, 27);
		copy.setIcon(Util.getIcon2("/image/reset.png", copy, (float) 0.8));
		copy.setHorizontalTextPosition(SwingConstants.RIGHT); 
		contentPanel.add(copy);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(106, 197, 212, 18);
		contentPanel.add(progressBar);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		
		progress = new JLabel("进度：");
		progress.setBounds(14, 197, 72, 18);
		contentPanel.add(progress);
		
		
		value = new JLabel("");
		value.setBounds(332, 197, 72, 18);
		contentPanel.add(value);
		
		progress.setVisible(false);
		progressBar.setVisible(false);
		value.setVisible(false);
		
		setResizable(false);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			File file = new File("message.txt");
			/*if (file.exists()) {
				file.delete();
			}*/
			fileOutputStream = new FileOutputStream("message.txt");
			fileInputStream = new FileInputStream(path.getText());
			DecimalFormat df=new DecimalFormat("#.##%");

			progress.setVisible(true);
			progressBar.setVisible(true);
			value.setVisible(true);
			value.setText(df.format(0));
			
			int len = fileInputStream.available();
			byte[] bs = new byte[Math.min(len / 10, 100)];
			double temp = 0.0;
			int i = 0;
			while((i = fileInputStream.read(bs)) != -1){
				fileOutputStream.write(bs);
				temp += i;
				double d = temp / len;
				value.setText(df.format(d));
				progressBar.setValue((int) (100 * d));
				System.out.println(df.format(d));
				Thread.sleep(100);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPanel, "文件还原出错！");
			
		}
		
		progress.setVisible(false);
		progressBar.setVisible(false);
		value.setVisible(false);
		if (progressBar.getValue() == progressBar.getMaximum()) {
			Back.this.dispose();
			frame.showBackOk();
		}
	}

}
