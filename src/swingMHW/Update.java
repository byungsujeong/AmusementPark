package swingMHW;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.MembersDAO;
import dto.MembersDTO;

public class Update {   //회원정보 수정 메소드 생성 
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
	
	private JButton button;
	
	public Update(int x, int y, String id) throws Exception {
		JFrame f = new JFrame();
		f.setTitle("회원정보 수정");
		f.setSize(1200,600);
		f.getContentPane().setLayout(null);
		
		textField = new JTextField();
		f.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
	
		MembersDAO dao = new MembersDAO();  //dao, dto 불러오기 
		MembersDTO dto = new MembersDTO();
		dto.setM_id(id);   //dto에서 id값 가저오기
		dao.sele2(dto);//dao에 있는 sele2실행 
		//sele메소드로 얻은 로그인 아이디 값을 line32의 String id로 받아 저장함
	    //sele2는 sele에서 가져온 데이터와 비교하여 id를 조건으로 나머지 정보를 다 가져옴
		
		t1 = new JTextField(dto.getM_id());
		t1.setEnabled(false);   //아이디는 수정 불가하게 설정
		t1.setText(dto.getM_id());
		t1.setColumns(10);
		t1.setBounds(176, 52, 213, 49);
		f.getContentPane().add(t1);
		
		t2 = new JTextField(dto.getPw());
		t2.setText(dto.getPw());
		t2.setColumns(10);
		t2.setBounds(176, 104, 213, 49);
		f.getContentPane().add(t2);
		
		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(33, 69, 57, 15);
		f.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("패스워드:");  
		lblNewLabel_1.setBounds(33, 121, 57, 15);
		f.getContentPane().add(lblNewLabel_1);
		
	
		t3 = new JTextField(dto.getM_name());   
		t3.setText(dto.getM_name());
		t3.setColumns(10);
		t3.setBounds(176, 163, 213, 49);
		f.getContentPane().add(t3);
		
		t4 = new JTextField(dto.getGender());
		t4.setText(dto.getGender());
		t4.setColumns(10);
		t4.setBounds(176, 222, 213, 49);
		f.getContentPane().add(t4);
		
		t5 = new JTextField(dto.getBirth());
		t5.setText(dto.getBirth());
		t5.setColumns(10);
		t5.setBounds(176, 281, 213, 49);
		f.getContentPane().add(t5);
		
		t6 = new JTextField(dto.getTall());
		t6.setText(Integer.toString(dto.getTall()));
		t6.setColumns(10);
		t6.setBounds(176, 336, 213, 49);
		f.getContentPane().add(t6);
		
		t7 = new JTextField(dto.getTel());
		t7.setText(dto.getTel());
		t7.setColumns(10);
		t7.setBounds(176, 395, 213, 49);
		f.getContentPane().add(t7);
		
		t8 = new JTextField(dto.getEmail());
		t8.setText(dto.getEmail());
		t8.setColumns(10);
		t8.setBounds(176, 454, 213, 49);
		f.getContentPane().add(t8);
		
		btnG = new JButton("확인");    //수정 완료 후 dto 통해서 업데이트 된 정보를 다시 보냄  
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dto.setM_id(t1.getText());
				dto.setPw(t2.getText());
				dto.setM_name(t3.getText());
				dto.setGender(t4.getText());
				dto.setBirth(t5.getText());
				dto.setTall(Integer.parseInt(t6.getText()));
				dto.setTel(t7.getText());
				dto.setEmail(t8.getText());

			
				try {
					dao.update(dto);    //다오에 있는 업데이트 문이 dto 값을 가지고 실행 됨 
					f.dispose();   //업데이트 완료 후 창 끄고 다시 메인으로 전환
					Start main = new Start(f.getLocation().x, f.getLocation().y, id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnG.setBounds(176, 528, 97, 23);
		f.getContentPane().add(btnG);
		
		JLabel label_1 = new JLabel("이름:");
		label_1.setBounds(33, 180, 57, 15);
		f.getContentPane().add(label_1);
		
		JLabel lblm = new JLabel("성별 (M 혹은 F):");
		lblm.setBounds(33, 239, 131, 15);
		f.getContentPane().add(lblm);
		
		JLabel label_3 = new JLabel("출생년도:");
		label_3.setBounds(33, 298, 57, 15);
		f.getContentPane().add(label_3);
		
		JLabel lblcm = new JLabel("키 (cm):");
		lblcm.setBounds(33, 353, 57, 15);
		f.getContentPane().add(lblcm);
		
		JLabel label_5 = new JLabel("전화번호:");
		label_5.setBounds(33, 414, 57, 15);
		f.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("이메일 주소: ");
		label_6.setBounds(33, 473, 85, 15);
		f.getContentPane().add(label_6);
		
		JLabel label_2 = new JLabel("회원정보 수정");
		label_2.setFont(new Font("굴림", Font.PLAIN, 15));
		label_2.setBounds(188, 10, 152, 23);
		f.getContentPane().add(label_2);
		
		button = new JButton("탈퇴");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
			try {
				dao.dele(dto);
				Start main = new Start(f.getLocation().x, f.getLocation().y, "");
				f.dispose();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			}
		});
		button.setBounds(292, 528, 97, 23);
		f.getContentPane().add(button);
		
		f.setLocation(x, y);
		f.setVisible(true);
	}
	
}
