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

public class BoardConfirm {
	private JTextField t1;
	private JTextField t2;
	
	//프레임 위치설정에 필요한 변수 x, y
	//테이블 record 클릭시 해당 record의 값들을 BoardConfirm화면으로 넘길 때 record의 위치값(b_id의 해당 row값)이 필요하고 
	//필요한 변수 b_id
	public BoardConfirm(int x, int y, String m_id, int b_id) throws Exception {
	
	// 프레임 불러오기	
		JFrame f = new JFrame();
		f.setTitle("글확인");
		f.setSize(1200, 600);
	// 패널 불러오기
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
		
		
	// 라벨(제목) 불러오기
		JLabel lblNewLabel = new JLabel("제목");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(130, 110, 57, 32);
		panel.add(lblNewLabel);
		
	// 텍스트필드(제목) 불러오기
		t1 = new JTextField();
		t1.setBounds(205, 110, 871, 32);
		panel.add(t1);
		t1.setColumns(10);
		
	// 라벨(내용) 불러오기
		JLabel label = new JLabel("내용");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 18));
		label.setBounds(130, 153, 57, 32);
		panel.add(label);
		
	// 텍스트필드(제목) 불러오기
		t2 = new JTextField();
		t2.setHorizontalAlignment(SwingConstants.LEFT);
		t2.setBounds(205, 152, 871, 265);
		panel.add(t2);
		t2.setColumns(10);
		
	// selectC메소드 불러오기(글확인창에 제목,내용을 뿌려주기 위해)
		BoardDAO boardDAO  = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();

		//모델에 얹은 테이블의 record 인덱스 값(b_id로 구별)
		//b_id의 값을 dto의 b_id에 넣어줌
		boardDTO.setB_id(b_id);
		
		//ado에서 만든 selectC메소드를 dto에 할당
		boardDAO.selectC(boardDTO);
		
	// selectC메소드에서 만든 dto를 사용하여 제목,내용 뿌려주기
		
		//dto에 넣어놓은 title값을 불러와 t1(제목)에 넣어줌
		t1.setText(boardDTO.getTitle());
		
		//dto에 넣어놓은 content값을 불러와 t2(내용)에 넣어줌
		t2.setText(boardDTO.getContent());
		

	// 버튼(글수정) 불러오기
		JButton b_w = new JButton("글수정");
		b_w.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// 글수정 확인창 띄우기
				int data = JOptionPane.showConfirmDialog(null, "글수정을 완료하시겠습니까?", "글수정 확인창",
						JOptionPane.OK_CANCEL_OPTION);
			// 컨펌창에서 확인을 누를경우
				if (data == 0) {
					
						//t1의 텍스트값을 dto의 title에 값을 넣어줌(수정된 내용을 다시 입력해주는 것)
					boardDTO.setTitle(t1.getText());
						
						//t2의 텍스트값을 dto의 title에 값을 넣어줌(수정된 내용을 다시 입력해주는 것)
					boardDTO.setContent(t2.getText());
						
						//m_id의 값을 dto의 m_id에 값을 넣어줌(수정,삭제 권한을 부여하기 위한 선별기준이 필요해서)
					boardDTO.setM_id(m_id);
						
						BoardDAO dao = new BoardDAO();
						
						try {
							// dao에서 생성한 update메소드를 불러옴(수정을 실행하기 위해)
							dao.update(boardDTO);
							
							//목록화면으로 돌아가기
							BoardMain name = new BoardMain(f.getLocation().x, f.getLocation().y, m_id);
							f.dispose();
							
						} catch (Exception e2) {
							e2.printStackTrace();
						}

						
					
				}
			}
		});
		b_w.setBounds(741, 462, 97, 23);
		
	// 수정,삭제 권한 부여
		//로그인한 아이디 m_id가 관리자(root) or dto에 입력되어 있는  m_id와 같다면 수정버튼을 보이게 아니라면 안보이게
		if (m_id.equals("root") || m_id.equals(boardDTO.getM_id())) {
			panel.add(b_w); //글수정버튼 조건에 맞으면 보이게
		}

	// 버튼(글삭제) 불러오기
		JButton b_d = new JButton("글삭제");
		b_d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// 글삭제 확인창 띄우기
				int data = JOptionPane.showConfirmDialog(null, "글삭제를 완료하시겠습니까?", "글삭제 확인창",
						JOptionPane.OK_CANCEL_OPTION);
			// 컨펌창에서 확인을 누를경우
				if (data == 0) {
										// m_id의 값을 dto를 사용해서 가져옴
					boardDTO.setB_id(b_id);
					
					// m_id의 값을 dto의 m_id에 값을 넣어줌(수정,삭제 권한을 부여하기 위한 선별기준이 필요해서)
					boardDTO.setM_id(m_id);
					
					BoardDAO dao = new BoardDAO();
					
					try {
						// dao에서 생성한 delete메소드를 불러옴(삭제를 실행하기 위해)
						dao.delete(boardDTO);
						
						//목록화면으로 돌아가기
						BoardMain name = new BoardMain(f.getLocation().x, f.getLocation().y, m_id);
						f.dispose();
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}					
					
				}

			}
		});
		b_d.setBounds(862, 462, 97, 23);
		
	//수정,삭제 권한 부여	
		//로그인한 아이디 m_id가 관리자(root) or dto에 존재하는 m_id와 같다면 삭제버튼을 보이게, 아니라면 안보이게
		if (m_id.equals("root") || m_id.equals(boardDTO.getM_id())) {
			panel.add(b_d);
		}

	// 버튼(목록가기) 불러오기
		JButton b_t = new JButton("목록가기");
		b_t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//목록화면으로 돌아가기
					BoardMain name = new BoardMain(f.getLocation().x, f.getLocation().y, m_id);
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
