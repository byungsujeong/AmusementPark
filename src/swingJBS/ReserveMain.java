package swingJBS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dto.MembersDTO;
import swingLHL.TtableInfo;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

// 예약메뉴(스마트예약, 스마트예약확인, 놀이기구예약, 놀이기구예약확인)
public class ReserveMain {

	public ReserveMain(int x, int y, String m_id) {

		JFrame f = new JFrame();
		f.setSize(1200, 600);
		f.setTitle("놀이기구 시간표 정보");
		f.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(12, 59, 1160, 492);
		f.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 10, 1136, 43);
		panel.add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);

		JLabel lblNewLabel = new JLabel(" 원하시는 서비스를 선택하세요.");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(291, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setLayout(null);
		panel_2.setBounds(12, 10, 1160, 39);
		f.getContentPane().add(panel_2);

		JLabel lab = new JLabel("");
		lab.setBounds(633, 10, 155, 15);
		panel_2.add(lab);
		
		JButton b1 = null;
		JButton b2 = null;

		MembersDTO dto = new MembersDTO();
		if (!m_id.equals("")) {
			lab.setText(m_id + "님 환영합니다");
		} else {
			lab.setText("");
		}

		if (m_id.equals("")) {
			b1 = new JButton("로그인");
			b2 = new JButton("회원가입");
			b2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					//if (Join.check==0){
						Join join = new Join(f.getLocation().x, f.getLocation().y);
					//}
				}
			});
			b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					LogIn login = new LogIn(f.getLocation().x, f.getLocation().y);
				}
			});
		} else {
			b1 = new JButton("로그아웃");
			b2 = new JButton("회원정보수정");
			b2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					try {
						Update upd = new Update(f.getLocation().x, f.getLocation().y, LogIn.id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					Start name = new Start(f.getLocation().x, f.getLocation().y, "");
				}
			});
		}
		
		b1.setFont(new Font("굴림", Font.BOLD, 14));
		b1.setBounds(838, 10, 149, 25);
		panel_2.add(b1);

		b2.setVerticalAlignment(SwingConstants.BOTTOM);
		b2.setFont(new Font("굴림", Font.BOLD, 14));
		b2.setBounds(999, 10, 149, 25);
		panel_2.add(b2);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 63, 1136, 419);
		panel.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);

		// 버튼 클릭 시 메인메뉴(Start 클래스)로 이동하는 ActionListener
		JButton back = new JButton("뒤로가기");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start name = new Start(f.getLocation().x, f.getLocation().y, m_id);
				f.dispose();
			}
		});

		back.setFont(new Font("굴림", Font.BOLD, 14));
		back.setBounds(1027, 373, 97, 36);
		panel_3.add(back);

		// 버튼 클릭 시 상품테이블(PtableList 클래스) 화면으로 이동하는 ActionListener
		JButton btnNewButton = new JButton();
		ImageIcon icon1 = new ImageIcon("picture/1.jpg");
		Image originimg1 = icon1.getImage();
		Image changedImg1 = originimg1.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		btnNewButton.setIcon(new ImageIcon(changedImg1));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					PtableList name = new PtableList(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(54, 71, 200, 200);
		panel_3.add(btnNewButton);

		// 버튼 클릭 시 상품예약내역(SmartCheck 클래스) 화면으로 이동하는 ActionListener
		JButton btnNewButton_2 = new JButton();
		ImageIcon icon2 = new ImageIcon("picture/2.png");
		Image originimg2 = icon2.getImage();
		Image changedImg2 = originimg2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		btnNewButton_2.setIcon(new ImageIcon(changedImg2));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					SmartCheck name = new SmartCheck(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(321, 71, 200, 200);
		panel_3.add(btnNewButton_2);

		// 버튼 클릭 시 놀이기구 타임테이블(TtableInfo 클래스) 화면으로 이동하는 ActionListener
		// 놀이기구 정보에서 갈 때와 다르게 선택한 놀이기구가 없어 ""값 반환하여 첫번째 놀이기구 타임테이블로 이동
		JButton btnNewButton_1 = new JButton();
		ImageIcon icon3 = new ImageIcon("picture/3.png");
		Image originimg3 = icon3.getImage();
		Image changedImg3 = originimg3.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		btnNewButton_1.setIcon(new ImageIcon(changedImg3));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TtableInfo name = new TtableInfo(f.getLocation().x, f.getLocation().y, m_id, "");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.dispose();
			}
		});
		btnNewButton_1.setBounds(595, 71, 200, 200);
		panel_3.add(btnNewButton_1);
		
		// 버튼 클릭 시 놀이기구예약내역(RideCheck 클래스) 화면으로 이동하는 ActionListener
		JButton btnNewButton_3 = new JButton();
		ImageIcon icon4 = new ImageIcon("picture/4.jpg");
		Image originimg4 = icon4.getImage();
		Image changedImg4 = originimg4.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		btnNewButton_3.setIcon(new ImageIcon(changedImg4));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					RideCheck name = new RideCheck(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(884, 71, 200, 200);
		panel_3.add(btnNewButton_3);

		JLabel lblNewLabel_2 = new JLabel("스마트 예약확인");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(321, 281, 200, 49);
		panel_3.add(lblNewLabel_2);

		JLabel label = new JLabel("스마트 예약");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(54, 281, 200, 49);
		panel_3.add(label);

		JLabel label_1 = new JLabel("놀이기구 얘약");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(595, 281, 200, 49);
		panel_3.add(label_1);

		JLabel label_2 = new JLabel("놀이기구 예약확인");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(884, 281, 200, 49);
		panel_3.add(label_2);

		f.setLocation(x, y);
		f.setVisible(true);

	}
}
