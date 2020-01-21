package swingMHW;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
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
import swingIJT.BoardMain;
import swingJBS.ReserveMain;
import swingLHL.RideInfo;

import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start {
	
	public Start(int x, int y, String m_id) {
		
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

		if (!m_id.equals("")) {  //DTO를 통해 받아온 아이디 값이 null이 아니라면 
			lab.setText(m_id + "님 환영합니다"); //ID를 화면위에 라벨로 출력하여 확인 됨 
		} else {     //null이라면 이 라벨을 나타내지 않음
			lab.setText("");
		}

		if (m_id.equals("")) {  //DTO를 통해 받아온 아이디 값이 null이 아니라면 
			b1 = new JButton("로그인"); //로그인과 회원가입 버튼을 나타냄
			b2 = new JButton("회원가입");
			b2.addActionListener(new ActionListener() { //회원가입 버튼 눌렀을 때
				public void actionPerformed(ActionEvent e) {
					f.dispose();
						Join join = new Join(f.getLocation().x, f.getLocation().y);  //Join 메소드를 불러옴 
				}
			});
			b1.addActionListener(new ActionListener() { //로그인 버튼 눌렀을 때
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					LogIn login = new LogIn(f.getLocation().x, f.getLocation().y); //Login 메소드를 불러옴 
				}
			});
		} else {   //받아온 id 값, m_id가 null일 때 
			b1 = new JButton("로그아웃"); //로그아웃 버튼 나타냄 
			b2 = new JButton("회원정보수정"); //회원정보수정 버튼 나타냄 
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
			b1.addActionListener(new ActionListener() {  //로그아웃 실행시
				public void actionPerformed(ActionEvent e) { 
					f.dispose();  //기존 id값을 받아와 저장한 메인화면 닫히고 새 start(메인)화면 시작
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

		JButton btnNewButton = new JButton("놀이기구 정보 보기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					RideInfo name = new RideInfo(f.getLocation().x, f.getLocation().y, m_id, "글로벌 페어");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(128, 178, 172, 59);
		panel_3.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("예약 메뉴로 가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveMain name = new ReserveMain(f.getLocation().x, f.getLocation().y, m_id);
				f.dispose();
			}
		});
		btnNewButton_1.setBounds(474, 178, 172, 59);
		panel_3.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("게시판으로 가기");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BoardMain name = new BoardMain(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(802, 178, 172, 59);
		panel_3.add(btnNewButton_2);

		f.setLocation(x, y);
		f.setVisible(true);

	}
}
