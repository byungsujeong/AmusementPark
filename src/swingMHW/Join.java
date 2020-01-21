package swingMHW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.MembersDAO;
import dto.MembersDTO;

public class Join {
	//textfield 선언
	private JTextField textField;
	private JTextField t1;
	private JTextField t2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnG;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JTextField t6;
	private JTextField t7;
	private JTextField t8;
	
	//DTO와 DAO 가져오기
	MembersDTO dto = new MembersDTO();
	MembersDAO dao = new MembersDAO();
	private JButton button;
	public Join(int x, int y) {  //Main에서 실행된 Start가 이 메소드를 가져올 때
		JFrame f = new JFrame();
		f.setTitle("회원가입 화면");
		f.setSize(1200,600);
		f.getContentPane().setLayout(null);
		
		textField = new JTextField();
		f.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
	
		
		t1 = new JTextField();
		t1.setColumns(10);
		t1.setBounds(176, 52, 213, 49);
		f.getContentPane().add(t1);
		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(112, 69, 20, 15);
		f.getContentPane().add(lblNewLabel);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(176, 104, 213, 49);
		f.getContentPane().add(t2);
		lblNewLabel_1 = new JLabel("패스워드:"); 
		lblNewLabel_1.setBounds(75, 121, 57, 15);
		f.getContentPane().add(lblNewLabel_1);
		
	
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(176, 163, 213, 49);
		f.getContentPane().add(t3);
		JLabel label_1 = new JLabel("이름:");
		label_1.setBounds(98, 180, 34, 15);
		f.getContentPane().add(label_1);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(176, 222, 213, 49);
		f.getContentPane().add(t4);
		JLabel lblm = new JLabel("성별 (M 혹은 F):");
		lblm.setBounds(33, 239, 99, 15);
		f.getContentPane().add(lblm);
		
		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(176, 281, 213, 49);
		f.getContentPane().add(t5);
		JLabel label_3 = new JLabel("출생년도:");
		label_3.setBounds(75, 298, 57, 15);
		f.getContentPane().add(label_3);
		
		t6 = new JTextField();
		t6.setColumns(10);
		t6.setBounds(176, 336, 213, 49);
		f.getContentPane().add(t6);
		JLabel lblcm = new JLabel("키 (cm):");
		lblcm.setBounds(75, 353, 57, 15);
		f.getContentPane().add(lblcm);
		
		
		t7 = new JTextField();
		t7.setColumns(10);
		t7.setBounds(176, 395, 213, 49);
		f.getContentPane().add(t7);
		JLabel label_5 = new JLabel("전화번호:");
		label_5.setBounds(75, 412, 57, 15);
		f.getContentPane().add(label_5);
		
		t8 = new JTextField();
		t8.setColumns(10);
		t8.setBounds(176, 454, 213, 49);
		f.getContentPane().add(t8);
		JLabel label_6 = new JLabel("이메일 주소: ");
		label_6.setBounds(57, 471, 85, 15);
		f.getContentPane().add(label_6);
		//여기까지 회원 정보 입력 받고
		
		JLabel label_2 = new JLabel("회원가입 진행");
		label_2.setFont(new Font("굴림", Font.PLAIN, 15));
		label_2.setBounds(188, 10, 152, 23);
		f.getContentPane().add(label_2);
		
		btnG = new JButton("확인"); //화면 하단 화인 버튼 누를시 입력된 정보를 DTO로 보내기 
		
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dto.setM_id(t1.getText());
				dto.setPw(t2.getText());
				dto.setM_name(t3.getText());
				dto.setGender(t4.getText());
				dto.setBirth(t5.getText());
				dto.setTall(Integer.parseInt(t6.getText())); //DB에서 int타입으로 설정되었기에 스트링으로 받아온 textfield 값을 int로 바꿔줌
				dto.setTel(t7.getText());
				dto.setEmail(t8.getText());
			
			
				try {
					dao.insert(dto); //DAO의 insert 메소드에 dto값을 넣어 실행
					f.dispose(); //확인이 눌려서 정보가 넘어가면 꺼지고 다시 Start(메인)화면 실행
					Start main = new Start(f.getLocation().x, f.getLocation().y, "");
				} catch (Exception e1) {
					e1.printStackTrace();  //에러(not null로 설정된 column의 값을 입력하지 않았을시 캐치
					JOptionPane.showMessageDialog(null, "필수값을 입력하지 않으셨습니다!"); //알림 팝업 
				}
				
			}
		});
		btnG.setBounds(176, 528, 97, 23);
		f.getContentPane().add(btnG);
		
		
		button = new JButton("홈으로");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			f.dispose();
			try {
				Start main = new Start(f.getLocation().x, f.getLocation().y, "");
			} catch (Exception e2) {
			}
			}
		});
		button.setBounds(292, 528, 97, 23);
		f.getContentPane().add(button);
		
		JLabel lblNewLabel_2 = new JLabel("*필수 입력값");
		lblNewLabel_2.setForeground(new Color(205, 92, 92));
		lblNewLabel_2.setBounds(416, 69, 79, 15);
		f.getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("*필수 입력값");
		label.setForeground(new Color(205, 92, 92));
		label.setBounds(416, 121, 79, 15);
		f.getContentPane().add(label);
		
		JLabel label_4 = new JLabel("*필수 입력값");
		label_4.setForeground(new Color(205, 92, 92));
		label_4.setBounds(416, 353, 79, 15);
		f.getContentPane().add(label_4);
		
		f.setLocation(x, y);
		f.setVisible(true);
	}
}
