package edu.handong.csee.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Selector extends JComponent implements ActionListener {

	private JFrame selector;
	private JButton OKButton;
	private ButtonGroup group ;
	private JRadioButton blackRadio, whiteRadio;
	private int userRole;
	

	public Selector() {
		selector = new JFrame("알림");
		selector.getContentPane().setBackground(Color.WHITE);
		selector.setBackground(Color.WHITE);
		selector.getContentPane().setForeground(Color.WHITE);
		selector.getContentPane().setLayout(null);

		JLabel blackIcon = new JLabel(" ");
		JLabel whiteIcon = new JLabel(" ");

		whiteIcon.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/whiteCatStone.png"));
		blackIcon.setIcon(new ImageIcon("/Users/suhyun/git/SSC_Project6/Source/blackCatStone.png"));
		blackIcon.setBounds(42, 46, 51, 50);
		whiteIcon.setBounds(146, 6, 51, 50);

		selector.getContentPane().add(whiteIcon);
		selector.getContentPane().add(blackIcon);

		OKButton = new JButton("확인");
		OKButton.setFont(
				new Font("DX\uACBD\uD544\uACE0\uB515B", OKButton.getFont().getStyle(), OKButton.getFont().getSize()));
		OKButton.setBounds(72, 95, 65, 29);
		selector.getContentPane().add(OKButton);
		
		 whiteRadio = new JRadioButton("백돌 (후공)");
		whiteRadio.setFont(new Font("DX경필고딕B", Font.PLAIN, 13));
		whiteRadio.setBounds(42, 20, 95, 23);
		selector.getContentPane().add(whiteRadio);
		
		 blackRadio = new JRadioButton("흑돌 (선공)");
		blackRadio.setSelected(true);
		blackRadio.setFont(new Font("DX경필고딕B", Font.PLAIN, 13));
		blackRadio.setBounds(88, 60, 100, 23);
		selector.getContentPane().add(blackRadio);
		selector.setSize(222, 149);
		selector.setLocation(500, 300);
		
		
		
		group = new ButtonGroup();
		group.add(whiteRadio);
		group.add(blackRadio);


		selector.setVisible(true);

		OKButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(OKButton)) {
			if(blackRadio.isSelected()) {
				userRole = Stone.BLACK;
			}
			else if(whiteRadio.isSelected()) {
				userRole = Stone.WHITE;
			}
			PlayFrame.getInstance().setUserRole(userRole);
			PlayFrame.getInstance().gameStart();
			selector.dispose();
		}
	}

	public int getUserRole() {
		return userRole;
	}
}
