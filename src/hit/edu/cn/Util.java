package hit.edu.cn;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Util {
	public static ImageIcon getIcon(String file, JButton iconButton) {  
        ImageIcon icon = new ImageIcon(file);  
        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),  
                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
        icon = new ImageIcon(temp);
		return icon;  
    } 
	
	public static ImageIcon getIcon2(String file, JButton iconButton, float percent) {  
        ImageIcon icon = new ImageIcon(file);  
        Image temp = icon.getImage().getScaledInstance((int)(iconButton.getHeight() * percent),  
        		(int)(iconButton.getHeight() * percent), icon.getImage().SCALE_DEFAULT); 
        icon = new ImageIcon(temp);
		return icon;  
    } 
	
	

}
