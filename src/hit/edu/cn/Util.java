package hit.edu.cn;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Util {
	//压缩图片适应不带文字button
	public static ImageIcon getIcon(String file, JButton iconButton) {  
        ImageIcon icon = new ImageIcon(file);  
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),  
                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
        icon = new ImageIcon(temp);
		return icon;  
    } 
	
	//压缩图片适应带文字button
	public static ImageIcon getIcon2(String file, JButton iconButton, float percent) {  
        ImageIcon icon = new ImageIcon(file);  
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
	
	

}
