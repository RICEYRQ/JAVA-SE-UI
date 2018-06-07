package hit.edu.cn;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;




public class Show extends JFrame implements Runnable {

	private static JPanel contentPanel;
	private static JPanel ForTable;
	private static JPanel ForMenu;
	private String tableRows[] = {"姓名","性别","工号","成绩"};//表头
	private int ROW_MAX = 100; //表格最大行数
	private JTable table;
	private DefaultTableModel model;
	private static Show frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Show();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Show() {
		
		ForTable();
		ForMenu();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 900);
		contentPanel = new JPanel();
		
		contentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0; 
		c1.gridy = 0; 
		c1.gridheight = 2;
		c1.weightx = 1.0; 
		c1.weighty = 0; 
		c1.ipady = 10;
		c1.fill = GridBagConstraints.HORIZONTAL ; 
		
		GridBagConstraints c3 = new GridBagConstraints(); 
		c3.gridx = 0; 
		c3.gridy = 10; 
		c3.weightx = 1.0; 
		c3.weighty = 1.0; 
		c3.fill = GridBagConstraints.BOTH ; 
		
		contentPanel.add(ForMenu,c1);
		contentPanel.add(ForTable, c3);
		
		/*contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));*/
		setContentPane(contentPanel);
		/*JFrame frame = new JFrame();  
		contentPanel.setOpaque(true); 
		frame.setSize(new Dimension(980, 520)); 
		frame.setLocation(200,120);
		frame.setContentPane(contentPanel); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	
	private void ForTable() {
		ForTable = new JPanel();
		String tableColumn[][] = new String[ROW_MAX][tableRows.length];
		model = new DefaultTableModel(tableColumn, tableRows);
		table = new JTable(model);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		/*StudentDao studentDao = new StudentDao();
		List<Student> list = studentDao.readSql();
		for(Student student:list){
			String[] tableColunms = new String[4];
        	tableColunms[0]=student.getName();
        	tableColunms[1]=student.getSex();
        	tableColunms[2]=student.getNum();
        	tableColunms[3]=student.getResult();
        	tableModel.addRow(tableColunms);
		}*/
		table.invalidate();
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		ForTable.setLayout(new BoxLayout(ForTable, BoxLayout.Y_AXIS));
		
		ForTable.add(scrollPane);
		
		ForTable.add(Box.createVerticalStrut(1)); 
		
	}
	
	private void ForMenu() {
		ForMenu = new JPanel();
		//ForMenu.setLayout(new BoxLayout(ForMenu, BoxLayout.X_AXIS )); 
		//ForMenu.SET
		ForMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel forMenu = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		forMenu.add(menuBar);
		
		JMenu message = new JMenu("学生信息管理");
		menuBar.add(message);
		
		JMenuItem addItem = new JMenuItem("添加学生信息");
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Add C11 = new Add();
				C11.show();*/
			}
		});
		message.add(addItem);
		
		/*JMenuItem deleteItem = new JMenuItem("删除学生信息");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		message.add(deleteItem);*/
		
		/*JMenuItem findItem = new JMenuItem("搜索学生信息");
		findItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		message.add(findItem);*/
		
		/*JMenuItem changeItem = new JMenuItem("修改学生信息");
		changeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		message.add(changeItem);*/
		
		JMenuItem showItem = new JMenuItem("刷新学生信息");
		showItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.setRowCount(0);
				StudentDao studentDao = new StudentDao();
				List<Student> list = studentDao.readSql();
				for(Student student:list){
					String[] tableColunms = new String[4];
		        	tableColunms[0]=student.getName();
		        	tableColunms[1]=student.getSex();
		        	tableColunms[2]=student.getNum();
		        	tableColunms[3]=student.getResult();
		        	tableModel.addRow(tableColunms);
				}
				table.invalidate();*/
				Thread thread = new Thread(frame);
				thread.start();
			}
		});
		message.add(showItem);
		
		JMenu action = new JMenu("操作");
		menuBar.add(action);
		
		JMenuItem delAllItem = new JMenuItem("清除文件内容");
		delAllItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelAll delAll = new DelAll(frame);
				delAll.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				delAll.setVisible(true);
			}
		});
		action.add(delAllItem);
		
		JMenuItem copyItem = new JMenuItem("备份");
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Copy copy = new Copy(frame);
				copy.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				copy.setVisible(true);
			}
		});
		action.add(copyItem);
		
		JMenuItem aboutItem = new JMenuItem("关于");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPanel, "作者：\r\n	1153710303	杨荣奇\r\n	1153710312	张迪\r\n	1153710322	潘俊舟");
			}
		});
		action.add(aboutItem);
		
		JMenuItem exitItem = new JMenuItem("退出");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		action.add(exitItem);
		
		
		//ForMenu.add(Box.createHorizontalStrut(-240));
		ForMenu.add(forMenu);
		//ForMenu = forMenu;
		
		ForMenu.add(Box.createVerticalStrut(0));
	}
	
	public static void delAll() {
		try {
			Util.delAllInMes();
			JOptionPane.showMessageDialog(contentPanel, "清除成功！");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(contentPanel, "写文件出错！");
		}
	}
	
	public static void showProgressOk() {
		JOptionPane.showMessageDialog(contentPanel, "文件备份成功！");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}