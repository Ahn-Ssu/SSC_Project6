package edu.handong.csee.java;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ClearPopup extends JComponent implements ActionListener {

	private JFrame popUp; 
	private JButton OKButton;
	private int role;
	
	public ClearPopup(int role) {
		this.role = role;
		popUp = new JFrame ("알림");
		popUp.getContentPane().setBackground(Color.WHITE);
		popUp.setBackground(Color.WHITE);
		popUp.getContentPane().setForeground(Color.WHITE);
		popUp.getContentPane().setLayout(null);
		
		
		JLabel InfoMessageLabel = new JLabel("이겼어요!");
		InfoMessageLabel.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", InfoMessageLabel.getFont().getStyle(), InfoMessageLabel.getFont().getSize()));
		InfoMessageLabel.setBounds(104, 63, 105, 19);
		popUp.getContentPane().add(InfoMessageLabel);
		
		JLabel reactionLabel = new JLabel("으와아앙");
		reactionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reactionLabel.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", reactionLabel.getFont().getStyle(), 15));
		reactionLabel.setBounds(42, 23, 167, 16);
		popUp.getContentPane().add(reactionLabel);
		
		JLabel catIcon = new JLabel(" ");
		catIcon.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", catIcon.getFont().getStyle(), 14));
		if(role==Stone.WHITE) {
			catIcon.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/whiteCatStone.png"));
			InfoMessageLabel = new JLabel("하양이 이겼어요!");
		}
		else if (role == Stone.BLACK) {
			catIcon.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/blackCatStone.png"));
			InfoMessageLabel = new JLabel("까망이 이겼어요!");
		}
		else if(role == 99) {
			catIcon.setText(" ㅠㅅㅠ " );
			InfoMessageLabel.setText("15초가 지났어요");
			reactionLabel.setText("시간 초과");
		}
		catIcon.setBounds(42, 46, 51, 50);
		popUp.getContentPane().add(catIcon);
		
		
		
		
		
		OKButton = new JButton("확인");
		OKButton.setFont(new Font("DX\uACBD\uD544\uACE0\uB515B", OKButton.getFont().getStyle(), OKButton.getFont().getSize()));
		OKButton.setBounds(88, 93, 65, 29);
		popUp.getContentPane().add(OKButton);
		popUp.setSize(250, 150);
		popUp.setLocation(500, 300);
		
		popUp.setVisible(true);
		
		OKButton.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(OKButton)) {
			PlayFrame.clearActivate();
			popUp.dispose();
		}
		
	}
}
