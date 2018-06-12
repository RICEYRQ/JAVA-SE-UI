package hit.edu.cn;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Add extends JDialog implements Runnable {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField num;
	private JTextField rank;
	private JTextField year;
	private JTextField otherThing;
	private Student student;
	private boolean name_ctrl = false;
	private boolean name_down = false;
	private boolean name_right = false;
	
	private boolean num_ctrl = false;
	private boolean num_left = false;
	private boolean num_down = false;
	
	private boolean rank_ctrl = false;
	private boolean rank_right = false;
	private boolean rank_up = false;
	
	private boolean year_ctrl = false;
	private boolean year_left = false;
	private boolean year_up = false;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Add dialog = new Add();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Add(Show show) {
		setBounds(100, 100, 450, 556);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("添加");
		label.setFont(new Font("黑体", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(164, 13, 91, 37);
		//label.setBounds(14, 27, 404, 18);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("姓名：");
		label_1.setBounds(14, 60, 72, 18);
		contentPanel.add(label_1);
		
		name = new JTextField();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					name_ctrl = true;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					name_down = true;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					name_right = true;
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (name_ctrl && name_right && !name_down) {
					num.requestFocus();
				} else if (name_down && !name_ctrl && !name_right) {
					rank.requestFocus();
				}
				name_ctrl = false;
				name_down = false;
				name_right = false;
				
			}
		});
		name.setBounds(65, 57, 86, 24);
		contentPanel.add(name);
		name.setColumns(10);
		
		JLabel label_2 = new JLabel("学号：");
		label_2.setBounds(238, 60, 72, 18);
		contentPanel.add(label_2);

		
		num = new JTextField();
		num.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					num_ctrl = true;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					num_down = true;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					num_left = true;
				}
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (num_ctrl && num_left && !num_down) {
					name.requestFocus();
				} else if (num_down && !num_ctrl && !num_left) {
					year.requestFocus();
				}
				num_ctrl = false;
				num_down = false;
				num_left = false;
			}
		});
		num.setBounds(290, 57, 86, 24);
		contentPanel.add(num);
		num.setColumns(10);
		num.setText("1153710101");
		
		JLabel rank1 = new JLabel("排名：");
		rank1.setBounds(14, 95, 72, 18);
		contentPanel.add(rank1);
		
		rank = new JTextField();
		rank.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if (e.getKeyChar() == KeyEvent.VK_0 ||
						e.getKeyChar() == KeyEvent.VK_1 ||
						e.getKeyChar() == KeyEvent.VK_2 ||
						e.getKeyChar() == KeyEvent.VK_3 ||
						e.getKeyChar() == KeyEvent.VK_4 ||
						e.getKeyChar() == KeyEvent.VK_5 ||
						e.getKeyChar() == KeyEvent.VK_6 ||
						e.getKeyChar() == KeyEvent.VK_7 ||
						e.getKeyChar() == KeyEvent.VK_8 ||
						e.getKeyChar() == KeyEvent.VK_9) {
					
				} /*else if (e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyCode() == KeyEvent.VK_RIGHT ||
						e.getKeyCode() == KeyEvent.VK_DOWN) {
					e.consume();
				}*/ else {
					e.consume();
				}
				rank_ctrl = false;
				rank_right = false;
				rank_up = false;
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (rank_ctrl && rank_right && !rank_up) {
					year.requestFocus();
				} else if (rank_up && !rank_ctrl && !rank_right) {
					name.requestFocus();
				}
				rank_ctrl = false;
				rank_right = false;
				rank_up = false;
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					rank_ctrl = true;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					rank_up = true;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					rank_right = true;
				}
				
			}
		});
		rank.setColumns(10);
		rank.setBounds(65, 92, 86, 24);
		contentPanel.add(rank);
		
		JLabel year1 = new JLabel("年龄：");
		year1.setBounds(238, 95, 72, 18);
		contentPanel.add(year1);
		
		year = new JTextField();
		year.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar(); 
				if (
						keyChar == KeyEvent.VK_0 ||
						keyChar == KeyEvent.VK_1 ||
						keyChar == KeyEvent.VK_2 ||
						keyChar == KeyEvent.VK_3 ||
						keyChar == KeyEvent.VK_4 ||
						keyChar == KeyEvent.VK_5 ||
						keyChar == KeyEvent.VK_6 ||
						keyChar == KeyEvent.VK_7 ||
						e.getKeyChar() == KeyEvent.VK_8 ||
						e.getKeyChar() == KeyEvent.VK_9
						) {
					
				} /*else if (e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyCode() == KeyEvent.VK_LEFT ||
						e.getKeyCode() == KeyEvent.VK_UP) {
					e.consume();
				}*/ else {
					e.consume();
				}
				year_ctrl = false;
				year_left = false;
				year_up = false;
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
					year_ctrl = true;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					year_up = true;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					year_left = true;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (year_ctrl && year_left && !year_up) {
					rank.requestFocus();
				} else if (year_up && !year_ctrl && !year_left) {
					num.requestFocus();
				}
				year_ctrl = false;
				year_left = false;
				year_up = false;
			}
		});
		year.setColumns(10);
		year.setBounds(290, 92, 86, 24);
		contentPanel.add(year);
		
		JRadioButton male = new JRadioButton("男");
		male.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		male.setBounds(65, 139, 54, 27);
		contentPanel.add(male);
		
		male.setSelected(true);
		
		JLabel label_3 = new JLabel("性别：");
		label_3.setBounds(14, 143, 72, 18);
		contentPanel.add(label_3);
		
		JRadioButton female = new JRadioButton("女");
		female.setBounds(125, 139, 54, 27);
		contentPanel.add(female);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(male);
		buttonGroup.add(female);
		
		
		JLabel label_4 = new JLabel("爱好：");
		label_4.setBounds(14, 189, 72, 18);
		contentPanel.add(label_4);
		
		JCheckBox basketball = new JCheckBox("篮球");
		basketball.setBounds(65, 185, 66, 27);
		contentPanel.add(basketball);
		
		JCheckBox football = new JCheckBox("足球");
		football.setBounds(136, 185, 66, 27);
		contentPanel.add(football);
		
		JCheckBox music = new JCheckBox("音乐");
		music.setBounds(200, 185, 66, 27);
		contentPanel.add(music);
		
		JCheckBox film = new JCheckBox("电影");
		film.setBounds(272, 185, 66, 27);
		contentPanel.add(film);
		
		JCheckBox cartoon = new JCheckBox("动漫");
		cartoon.setBounds(65, 216, 66, 27);
		contentPanel.add(cartoon);
		
		JCheckBox other = new JCheckBox("其他");
		other.setBounds(136, 217, 66, 27);
		contentPanel.add(other);
		
		otherThing = new JTextField();
		otherThing.setBounds(210, 217, 86, 24);
		contentPanel.add(otherThing);
		otherThing.setColumns(10);
		
		JComboBox classes = new JComboBox();
		classes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				//
				if (e.getStateChange() == ItemEvent.SELECTED) {
					//System.out.println(classes.getSelectedItem());
					//System.out.println(e.getItem());
					String cla = Data.classes[classes.getSelectedIndex()];
					//System.out.println(cla);
					String num11 = num.getText();
					if (!num11.contains(cla)) {
						num.setText("1" + cla + "01");
						num.requestFocus();
						num.selectAll();
					}
				}
				
			}
		});
		
		classes.setModel(new DefaultComboBoxModel(Data.classes));
		classes.setMaximumRowCount(7);
		classes.setBounds(290, 140, 86, 24);
		contentPanel.add(classes);
		
		JLabel label_5 = new JLabel("班级：");
		label_5.setBounds(238, 143, 72, 18);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("专业方向：");
		label_6.setBounds(14, 252, 105, 18);
		contentPanel.add(label_6);
		
		JList direction = new JList();
		direction.setBounds(150, 252, 150, 94);
		ListModel listModel = new DefaultComboBoxModel(Data.directionList);
		direction.setModel(listModel);
		direction.setSelectedIndex(0);
		contentPanel.add(direction);
		
		JButton confirm = new JButton("添加");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPanel, "请输入姓名！");
				}else if (num.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPanel, "请输入学号！");
				}else if (rank.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPanel, "请输入排名！");
				}else if (year.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPanel, "请输入年龄！");
				}else if (other.isSelected() && otherThing.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPanel, "请输入其他爱好！");
				}else if (Integer.valueOf(rank.getText()) > 150) {
					JOptionPane.showMessageDialog(contentPanel, "我们学院最多150人！");
				}else if (Integer.valueOf(year.getText()) > 150) {
					JOptionPane.showMessageDialog(contentPanel, "年龄最多100岁！");
				} else {
					student = new Student();
					
					student.setName(name.getText());
					
					student.setNum(num.getText());
					
					student.setRank(Integer.valueOf(rank.getText()));
					
					student.setAge(Integer.valueOf(year.getText()));
					
					student.setClasses(Data.classes[classes.getSelectedIndex()]);
					
					student.setDirection(Data.directionList[direction.getSelectedIndex()]);
					
					if (male.isSelected()) {
						student.setSex("男");
					} else if (female.isSelected()) {
						student.setSex("女");
					} else {
						student.setSex("未知");
					}
					
					List<String> loveList = new ArrayList<>();
					if (basketball.isSelected()) {
						loveList.add("篮球");
					}
					if (football.isSelected()) {
						loveList.add("足球");
					}
					if (music.isSelected()) {
						loveList.add("音乐");
					}
					if (film.isSelected()) {
						loveList.add("电影");
					}
					if (cartoon.isSelected()) {
						loveList.add("动漫");
					}
					if (other.isSelected()) {
						loveList.add(otherThing.getText());
					}
					if (loveList.isEmpty()) {
						student.setLoveThings("无");
					} else {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append(loveList.get(0));
						for(int i = 1; i < loveList.size(); i++) {
							if (i >= loveList.size()) {
								continue;
							}
							stringBuilder.append("，");
							stringBuilder.append(loveList.get(i));
						}
						student.setLoveThings(stringBuilder.toString());
					}
					
					Thread thread = new Thread(Add.this);
					thread.start();
					show.refreshTable();
				}
			}
		});
		confirm.setBounds(219, 400, 113, 27);
		confirm.setIcon(Util.getIcon2("image/OK.png", confirm, (float) 0.8));
		confirm.setHorizontalTextPosition(SwingConstants.RIGHT); 
		
		contentPanel.add(confirm);
		
		JButton cancel = new JButton("取消");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelAdd cancelAdd = new CancelAdd(Add.this);
				cancelAdd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				cancelAdd.setVisible(true);
			}
		});
		cancel.setBounds(51, 400, 113, 27);
		cancel.setIcon(Util.getIcon2("image/cancel.png", cancel, (float) 0.8));
		cancel.setHorizontalTextPosition(SwingConstants.RIGHT); 
		
		contentPanel.add(cancel);
		
		setResizable(false);
	}
	
	public void cancelAdd() {
		Add.this.dispose();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Util.addToFile(student);
			Add.this.dispose();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(contentPanel, "添加失败，请重试！");
			
		}
	}
}
