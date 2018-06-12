package hit.edu.cn;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Util {
	//压缩图片适应不带文字button
	public static ImageIcon getIcon(String file, JButton iconButton) {  
        ImageIcon icon = new ImageIcon(Login.class.getResource(file));  
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),  
                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
        icon = new ImageIcon(temp);
		return icon;  
    } 
	
	//压缩图片适应带文字button
	public static ImageIcon getIcon2(String file, JButton iconButton, float percent) {  
        ImageIcon icon = new ImageIcon(Login.class.getResource(file));  
        Image temp = icon.getImage().getScaledInstance((int)(iconButton.getHeight() * percent),  
        		(int)(iconButton.getHeight() * percent), icon.getImage().SCALE_DEFAULT); 
        icon = new ImageIcon(temp);
		return icon;  
    } 
	
	public static void delAllInMes() throws IOException {
		File file = new File("message.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		bufferedWriter.write("");
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
	public static void addToFile(Student student) throws IOException {
		File file = new File("message.txt");
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(getStudentString(student));
		printWriter.println("\r\n");
		printWriter.flush();
		
		fileWriter.flush();
		printWriter.close();
		fileWriter.close();
	}
	
	public static List<String> readFile() throws IOException {
		List<String> list = new ArrayList<>();
		
		File file = new File("message.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String temp = null;
		while((temp = bufferedReader.readLine()) != null) {
			//Student student = getStringStudent(temp);
			if (temp.equals("")) {
				continue;
			}
			list.add(temp);
			System.out.println(temp);
		}
		bufferedReader.close();
		
		return list;
	}
	
	public static Student getStringStudent(String temp) {
		String[] string = temp.split("$");
		StringTokenizer strToken=new StringTokenizer(temp,"$");
		Student student = new Student();
		List<String> list = new ArrayList<>();
		while(strToken.hasMoreElements()){ 
			   list.add(strToken.nextToken());
		}
		if (list.size() != 8) {
			return null;
		}
		student.setName(list.get(0));
		student.setNum(list.get(1));
		student.setRank(Integer.valueOf(list.get(2)));
		student.setAge(Integer.valueOf(list.get(3)));
		student.setSex(list.get(4));
		student.setClasses(list.get(5));
		student.setLoveThings(list.get(6));
		student.setDirection(list.get(7));
		return student;
	}
	
	private static String getStudentString(Student student) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(student.getName());
		stringBuilder.append("$");
		stringBuilder.append(student.getNum());
		stringBuilder.append("$");
		stringBuilder.append(student.getRank());
		stringBuilder.append("$");
		stringBuilder.append(student.getAge());
		stringBuilder.append("$");
		stringBuilder.append(student.getSex());
		stringBuilder.append("$");
		stringBuilder.append(student.getClasses());
		stringBuilder.append("$");
		stringBuilder.append(student.getLoveThings());
		stringBuilder.append("$");
		stringBuilder.append(student.getDirection());
		return stringBuilder.toString();
	}
	
	

}
