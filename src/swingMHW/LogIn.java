package swingMHW;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.MembersDAO;
import dto.MembersDTO;

public class LogIn {   //
	private JTextField textField;
	private JTextField t2;
	private JPasswordField t3;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnG;
	public static String id;
	private JLabel lblWelcome;
	
	public LogIn(int x, int y) {
		JFrame f = new JFrame();
		f.setTitle("로그인 화면");
		f.setSize(1200, 600);
		f.getContentPane().setLayout(null);

		textField = new JTextField();
		f.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);


		t2 = new JTextField(); //ID입력란
		t2.setColumns(10);
		t2.setBounds(395, 136, 213, 49);
		f.getContentPane().add(t2);

		t3 = new JPasswordField();  //패스워드 입력란 - 화면에 패스워드 블러처리 될 수 있는 옵션으로 텍스트필드 생성
		t3.setColumns(10);
		t3.setBounds(395, 219, 213, 49);
		f.getContentPane().add(t3);

		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(319, 153, 57, 15);
		f.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(295, 236, 88, 15);
		f.getContentPane().add(lblNewLabel_1);

		btnG = new JButton("확인");
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MembersDTO dto = new MembersDTO();
				MembersDAO dao = new MembersDAO();
				dto.setM_id(t2.getText()); //텍스트필드에 입력한 ID,PW를 DTO로 보내기
				dto.setPw(t3.getText());
				try {			//DTO에 보내진 ID,PW를 DAO에 있는 셀렉트 메소드로 DB에 있는 정보와 비교
					boolean result = dao.sele(dto);
					if (result) {  //if result true, 즉 정보가 일치한다면 로그인 창 dispose 하고 다시 메인으로
						Start main = new Start(f.getLocation().x, f.getLocation().y, dto.getM_id());
						id = t2.getText();
						f.dispose();
					} else {		//일치하지 않았다면 재시도를 유도하는 팝업 메시지 띄우기
						JOptionPane.showMessageDialog(null, "PW가 틀렸거나 가입되지 않은 ID 입니다!");
						t3.setText(""); //로그인 재시도 할때에 편리함을 위해 Id 입력값인 t2는 놓아두고 t3만 다시 null로 설정
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnG.setBounds(395, 353, 97, 23);
		f.getContentPane().add(btnG);

		JButton button = new JButton("홈으로");  //로그인이 성공하지 않아도 다시 메인으로 돌아갈 수 있는 옵션 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose(); //로그인창 꺼지고 다시 메인으로
				try {
					Start main = new Start(f.getLocation().x, f.getLocation().y, "");
				} catch (Exception e2) {
				}
			}
		});
		button.setBounds(522, 353, 97, 23);
		f.getContentPane().add(button);
		
		lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Adobe Song Std L", Font.PLAIN, 26));
		lblWelcome.setBounds(426, 45, 213, 49);
		f.getContentPane().add(lblWelcome);
		
		f.setLocation(x, y);
		f.setVisible(true);
	}

}
