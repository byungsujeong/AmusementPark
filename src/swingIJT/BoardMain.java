package swingIJT;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.MembersDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

public class BoardMain {
	private static JTextField t0;
	JFrame f; //프레임 선언(static x)

	public BoardMain(int x, int y, String m_id) throws Exception {
		
	// 테이블 항목명 입력
		//				   b_id, title,  m_id,   uDate,  hits
		String[] items = { "순번", "제목", "작성자", "작성일", "조회수" };
	
	// 모델 불러오기(필드명,레코드수)
		DefaultTableModel model = new DefaultTableModel(items, 0);

		BoardDAO dao = new BoardDAO();
		
	// 배열리스트 생성
		ArrayList<BoardDTO> list = dao.selectAll();

		//2차원 배열변수 data를 불러오기 [행의수는 list.size()개수][열의개수]
		Object[][] data = new Object[list.size()][];
		
		for (int i = 0; i < list.size(); i++) {
		//list의 첫번째 행을 dto로 할당 ... list.size()만큼 반복하여 dto에 할당
			BoardDTO dto = list.get(i);
			
		//dto에 할당된 각 항목들을 가져와 1차원 배열변수 row 선언
			Object[] row = { dto.getB_id(), dto.getTitle(), dto.getM_id(), dto.getuDate(), dto.getHits() };
			
			model.addRow(row); //model에 1차원배열 변수 row를 하나씩 추가하면서 쌓음
		}

	// 프레임 생성
		f = new JFrame();
		f.setSize(1200, 600);
		f.getContentPane().setLayout(null);

	// 패널 생성
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1184, 561);
		f.getContentPane().add(panel);
		panel.setLayout(null);

	// 테이블 생성
		JTable table = new JTable(model); // JTable에 모델이란 변수를 올림
		table.setFont(new Font("굴림", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {

	// 클릭하면 화면전환, 글확인화면으로
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//(table.getSelectedRow() 테이블의 해당 row의 위치값, calumn의 위치값)
				//model.getValueAt() => model에 있는 해당 row, calumn의 위치값에 해당하는 실제값을 가져옴.
				//(int) 캐스팅, 형변환
				//b_id(0)의 해당 row값을 int변수 index로 할당
				int index = (int) model.getValueAt(table.getSelectedRow(), 0);
				
	//hits(조회수)		
				//hits(4)의 해당 row값을 int변수 hits로 할당
				int hits = (int) model.getValueAt(table.getSelectedRow(), 4);
				
				//hit값을 마우스 클릭때마다 1씩 증가시킴
				hits++;
				
				BoardDAO dao = new BoardDAO();
				
				try {
					//글확인화면으로 이동
					BoardConfirm name = new BoardConfirm(f.getLocation().x, f.getLocation().y, m_id, index);
					
					//dao에서 생성한 updateH메소드를 사용하여 변수 index(b_id)값을 기준으로 hit값를 수정
					dao.updateH(index, hits); //db처리
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				f.dispose();
			}
		});
		
	// 스크롤 생성
		JScrollPane scrollPane = new JScrollPane(table); //스크롤에 테이블 올림
		scrollPane.setBounds(110, 66, 966, 400);
		panel.add(scrollPane); //패널에 스크롤 올림

	// 라벨(Page) 불러오기
	//JLabel lblNewLabel = new JLabel("Page");
	//lblNewLabel.setBounds(375, 447, 57, 15);
	//panel.add(lblNewLabel);

	// 페이지 버튼1 임시
	//JButton b_p1 = new JButton("1");
	//b_p1.setFont(new Font("굴림", Font.PLAIN, 7));
	//b_p1.setBounds(444, 443, 39, 23);
	//panel.add(b_p1);

	// 페이지 버튼2 임시
	//JButton b_p2 = new JButton("2");
	//b_p2.setFont(new Font("굴림", Font.PLAIN, 7));
	//b_p2.setBounds(484, 443, 39, 23);
	//panel.add(b_p2);

	// 페이지 버튼3 임시
	//JButton b_p3 = new JButton("3");
	//b_p3.setFont(new Font("굴림", Font.PLAIN, 7));
	//b_p3.setBounds(523, 443, 39, 23);
	//panel.add(b_p3);

	// 페이지 버튼4 임시
	//JButton b_p4 = new JButton("4");
	//b_p4.setFont(new Font("굴림", Font.PLAIN, 7));
	//b_p4.setBounds(562, 443, 39, 23);
	//panel.add(b_p4);

	// 페이지 버튼5 임시
	//JButton b_p5 = new JButton("5");
	//b_p5.setFont(new Font("굴림", Font.PLAIN, 7));
	//b_p5.setBounds(603, 443, 39, 23);
	//panel.add(b_p5);
		
///////------------------------------------////////			
	//버튼(로그인, 회원정보수정) 불러오기
		
	//라벨(로그인 상태메시지) 불러오기
		JLabel lab = new JLabel("");
		lab.setBounds(625, 10, 200, 35);
		panel.add(lab);
	
		JButton b_l1 = null;
		JButton b_l2 = null;
		
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
		

	// 텍스트필드(검색창) 불러오기
		t0 = new JTextField();
		t0.setBounds(375, 484, 267, 21);
		panel.add(t0);
		t0.setColumns(10);

	// 버튼(검색) 불러오기, 검색할 id를 입력하고 누르면 해당하는 글만 검색해서 띄우게 해야함
		JButton b_d = new JButton("검색");
		b_d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//모델의 행의수를 0으로 설정 1차 검색 후 2차 검색시 데이터가 계속 쌓이는 것을 방지하기 위해
				model.setRowCount(0);
				
				BoardDTO dto = new BoardDTO();
				
			//t0의 텍스트값을 가져와 dto의 m_id에 할당
				dto.setM_id(t0.getText());

				ArrayList<BoardDTO> listS;
				
				try {
				//dao에서 생성한 selectS메소드에 dto로 set한 값들을 listS로 할당
					listS = dao.selectS(dto);
					
					Object[][] dataS = new Object[listS.size()][];
					for (int i = 0; i < listS.size(); i++) {
						
						//listS의 첫번째 행을 dto로 할당 ... list.size()만큼 반복하여 dto에 할당
						dto = listS.get(i);
						
						//dto에 할당된 각 항목들을 가져와 1차원 배열변수 rowS 선언
						Object[] rowS = { dto.getB_id(), dto.getTitle(), dto.getM_id(), dto.getuDate(), dto.getHits() };
						model.addRow(rowS); //model에 1차원배열 변수 rowS를 하나씩 추가하면서 쌓음
						
					//t0의 텍스트 값이 null이면 화면변화x
						t0.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
					
			}
		});

		b_d.setFont(new Font("굴림", Font.PLAIN, 12));
		b_d.setBounds(654, 483, 69, 23);
		panel.add(b_d);

	// 버튼(글작성) 불러오기, 누르면 글작성화면으로 이동
		JButton b_w = new JButton("글작성");
		b_w.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// 글작성 권한 부여
				//m_id가 null값이 아니라면(즉 로그인 상태라면) 글작성화면으로 이동, 다르다면 로그인메시지 띄우고 로그인화면으로
				if (!m_id.equals("")) {
					//글작성화면으로 이동
					BoardWrite name = new BoardWrite(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();	
				}else {
					JOptionPane.showMessageDialog(null, "로그인을 해주세요.", "로그인여부", 1);
					LogIn login = new LogIn(f.getLocation().x, f.getLocation().y);
					f.dispose();	
				}
				
			
			}

		});
		b_w.setBounds(771, 483, 97, 23);
		panel.add(b_w);  //글작성버튼 조건에 맞으면 보이게
		

	// 버튼(목록가기) 불러오기, 누르면 목록화면으로 이동
		JButton b_t = new JButton("전체목록");
		b_t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//목록화면으로 이동
					BoardMain name = new BoardMain(f.getLocation().x, f.getLocation().y, m_id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();

			}
		});
		b_t.setBounds(874, 483, 97, 23);
		panel.add(b_t);
		
	//라벨(ID입력) 불러오기	
		JLabel lblNewLabel_1 = new JLabel("ID 입력 >>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(296, 487, 75, 15);
		panel.add(lblNewLabel_1);
	
	//버튼(뒤로가기) 불러오기	
		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start name = new Start(f.getLocation().x, f.getLocation().y, m_id);
				f.dispose();
			}
		});
		btnNewButton.setBounds(983, 483, 97, 22);
		panel.add(btnNewButton);

		f.setLocation(x, y);
		f.setVisible(true);

	}
}
