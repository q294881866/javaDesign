package javase.example.randomWalk;
/**
 * 随机游走界面<br>
 * 模仿醉汉随机走路问题
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI {
	//场地长与宽
	int length = 500;
	int width = 500;
	
	//记录醉汉位置
	int x = width/2;
	int y = length/2;
	
	int count;	//用于记录文本框中输入的步数
	
	private JTextField txt = new JTextField(10);
	private JButton runButton = new JButton("运行");
	
	public UI() {
		final JFrame frame = new JFrame("Random Walk");
		frame.setSize(width, length);
		frame.setVisible(true);
		
		frame.add(txt);
		txt.setHorizontalAlignment(JTextField.RIGHT);
		
		frame.setLayout(new FlowLayout());
		frame.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = Integer.parseInt(txt.getText().toString());
				
				int[] point = new int[2];
				point[0] = x;
				point[1] = y;
				
				frame.getGraphics().drawOval(point[0], point[1], 3, 3);	//画出初始位置
				
				for(int i=1; i<=count; i++) {
					int randomDirection = new Direction().direction();
					
					point = new NextStep(point, randomDirection).nextStep();
					
					frame.getGraphics().drawOval(point[0], point[1], 3, 3);
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter(){  //设置窗口关闭事件处理,缺少了关闭按钮不会响应，程序没法关闭
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}

