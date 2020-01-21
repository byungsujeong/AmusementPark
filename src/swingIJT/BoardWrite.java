package swingIJT;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.MembersDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

public class BoardWrite {
	private JTextField t1;
	private JTextField t2;

	//스윙 로케이션 값 지정
	public BoardWrite(int x, int y, String m_id) {
		
	//프레임 불러오기	
		JFrame f = new JFrame();
		f.setTitle("글작성");
		f.setSize(1200, 600);
		
	//패널 불러오기	
		JPanel panel = new JPanel();
		f.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
///////------------------------------------////////	
		//버튼(로그인, 회원정보수정) 불러오기	
		
		//라벨(로그인 상태메시지) 불러오기	
			JLabel lab = new JLabel("");
			lab.setBounds(625, 10, 200, 35);
			panel.add(lab);
		
			JButton b_l1 = null;
			JButton b_l2 = null;
			
			
			MembersDTO dto = new MembersDTO();
			if (!m_id.equals("")) {
				lab.setText(m_id + " 님 환영합니다");
			} else {
				lab.setText("");
			}

			if (m_id.equals("")) {
				b_l1 = new JButton("로그인");
				b_l2 = new JButton("회원가입");
				b_l2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						//if (Join.check==0){
							Join join = new Join(f.getLocation().x, f.getLocation().y);
						//}
					}
				});
				b_l1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						LogIn login = new LogIn(f.getLocation().x, f.getLocation().y);
					}
				});
			} else {
				b_l1 = new JButton("로그아웃");
				b_l2 = new JButton("회원정보 수정");
				b_l2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						try {
							Update upd = new Update(f.getLocation().x, f.getLocation().y, LogIn.id);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				b_l1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						f.dispose();
						Start name = new Start(f.getLocation().x, f.getLocation().y, "");
					}
				});
			}
			// 버튼(로그인) 옵션
			b_l1.setBounds(750, 10, 150, 32);
			panel.add(b_l1);
			
			// 버튼(회원가입 or 회원정보 수정) 옵션
			b_l2.setBounds(925, 10, 150, 32);
			panel.add(b_l2);
///////------------------------------------////////
			
		
	//라벨(제목) 불러오기
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(130, 110, 57, 32);
		panel.add(lblNewLabel);
		
	//텍스트필드(t1,내용) 불러오기	
		t1 = new JTextField();
		t1.setBounds(205, 110, 871, 32);
		panel.add(t1);
		t1.setColumns(10);
		
	//텍스트필드(t2,제목) 불러오기	
		t2 = new JTextField();
		t2.setBounds(205, 152, 871, 265);
		panel.add(t2);
		t2.setColumns(10);
		
	//라벨(내용) 불러오기	
		JLabel label = new JLabel("내용");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 18));
		label.setBounds(130, 153, 57, 32);
		panel.add(label);
		
	//버튼(글작성) 불러오기
		JButton b_w = new JButton("글작성");
		b_w.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		//글작성 확인창 띄우기		
		int data = JOptionPane.showConfirmDialog
		(null, "글작성을 완료하시겠습니까?", "글작성 확인창", JOptionPane.OK_CANCEL_OPTION);
			//컨펌창에서 확인 누를 때
			if (data == 0) {
//				String m_id = JOptionPane.showInputDialog("id");
				
				//t1에 입력된 텍스트 값을 변수 title로 할당
				String title = t1.getText();
				
				//t2에 입력된 텍스트 값을 변수 content로 할당
				String content = t2.getText();
				
				BoardDTO dto = new BoardDTO();
				
				try {
					BoardDAO dao = new BoardDAO();
					
					//m_id의 값을 dto의 m_id에 값을 넣어줌
					dto.setM_id(m_id);
					
					//title의 값을 dto의 title에 값을 넣어줌
					dto.setTitle(title);
					
					//content의 값을 dto의 content에 값을 넣어줌					
					dto.setContent(content);
					
						//dao에서 생성한 insert메소드를 불러옴(작성을 실행하기 위해)
						dao.insert(dto);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
				try {
					//목록화면으로 돌아가기
					BoardMain name = new BoardMain(f.getLocation().x,f.getLocation().y, m_id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();
				
			}

				
				
			}
		});
		b_w.setBounds(862, 462, 97, 23);
		panel.add(b_w);
			
	//버튼(목록가기) 불러오기	
		JButton b_t = new JButton("목록가기");
		b_t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//목록화면으로 돌아가기
					BoardMain name = new BoardMain(f.getLocation().x,f.getLocation().y, m_id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();
				
			}
		});
		b_t.setBounds(979, 462, 97, 23);
		panel.add(b_t);
		
		
		f.setLocation(x, y);
		f.setVisible(true);
		
		
	}

}
