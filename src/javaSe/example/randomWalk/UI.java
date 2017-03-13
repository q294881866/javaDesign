package javaSe.example.randomWalk;
/**
 * ������߽���<br>
 * ģ���������·����
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI {
	//���س����
	int length = 500;
	int width = 500;
	
	//��¼��λ��
	int x = width/2;
	int y = length/2;
	
	int count;	//���ڼ�¼�ı���������Ĳ���
	
	private JTextField txt = new JTextField(10);
	private JButton runButton = new JButton("����");
	
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
				
				frame.getGraphics().drawOval(point[0], point[1], 3, 3);	//������ʼλ��
				
				for(int i=1; i<=count; i++) {
					int randomDirection = new Direction().direction();
					
					point = new NextStep(point, randomDirection).nextStep();
					
					frame.getGraphics().drawOval(point[0], point[1], 3, 3);
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter(){  //���ô��ڹر��¼�����,ȱ���˹رհ�ť������Ӧ������û���ر�
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}

