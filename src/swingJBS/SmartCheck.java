package swingJBS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.SmartRDAO;
import dto.MembersDTO;
import dto.SmartRDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

// 상품예약내역 테이블 : 본인예약확인, 예약 변경 삭제 등
public class SmartCheck {

	public SmartCheck(int x, int y, String m_id) throws Exception {

		// 예약내역 필드명
		String[] items = { "예약번호", "상품명", "예약날짜", "가격", "예약자ID", "예약자" };
		
		// JTable 타입 객체에 넣어줄 default모델 생성 
		DefaultTableModel model = new DefaultTableModel(items, 0);
		
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

		JLabel lblNewLabel = new JLabel(" 예약번호 : ");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(165, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);

		JTextField t1 = new JTextField();
		t1.setBounds(107, 10, 211, 22);
		panel_1.add(t1);
		t1.setColumns(10);
	
		JButton btnNewButton_1 = new JButton("조회");
		btnNewButton_1.setBounds(330, 10, 97, 24);
		panel_1.add(btnNewButton_1);

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

		JTable table_1 = new JTable(model);
		table_1.setBounds(12, 403, 641, -392);
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(12, 10, 847, 399);
		panel_3.add(scrollPane);
		
		// 버튼 클릭 시 스마트예약 변경,취소(SmartChange 클래스) 화면을 팝업으로 띄우는 ActionListener
		JButton btnNewButton = new JButton("예약변경");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// getSelectedRow로 선택한 row값을 가져와 model의 첫번째(index 0)열에 들어있는 데이터 반환
					SmartChange name = new SmartChange(f.getLocation().x, f.getLocation().y, m_id, (String) model.getValueAt(table_1.getSelectedRow(), 0));
				} catch (Exception e1) {
					e1.printStackTrace();
					// 미선택 시 오류
					JOptionPane.showMessageDialog(null, "예약내역을 선택하세요.");
				}
				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 14));
		btnNewButton.setBounds(894, 373, 129, 36);
		panel_3.add(btnNewButton);
		
		// 버튼 누르는 경우 예약번호(sr_id)와 유사한 값 가져옴 (like '%검색어%')
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 조회 시 테이블 모델 초기화
				model.setRowCount(0);
							
				// 상품예약테이블(samrtr) 데이터 가져오기 위한 DTO,DAO객체 생성
				SmartRDTO smartRDTO = new SmartRDTO();
				SmartRDAO smartRDAO = new SmartRDAO();
				// ArrayList타입 list 변수 선언하여 DAO에서 select문 실행결과 받아옴
				ArrayList<SmartRDTO> list;
				// 관리자(m_id : root)인 경우에 전체 예약 내역 조회
				if (m_id.equals("root")) {
					try {
						// 검색 내용을 가져와서 listAdmin()메소드 실행
						smartRDTO.setSr_id(t1.getText());
						list = smartRDAO.listAdmin(smartRDTO);
						// DAO에서 반환된 list를 그 크기만큼 위에서 선언된 변수 DTO에 담에주고 모델 각 row에 추가  
						for (int i = 0; i < list.size(); i++) {
							smartRDTO = list.get(i);
							Object[] r = { smartRDTO.getSr_id(), smartRDTO.getP_name(), smartRDTO.getRdate(),
									smartRDTO.getPrice(), smartRDTO.getM_id(), smartRDTO.getM_name() };
							model.addRow(r);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				// 관리자가 아닌 경우에 본인 예약 내역 조회
				} else {
					// 본인여부를 확인하는 setM_id 외 위와 같음
					smartRDTO.setSr_id(t1.getText());
					smartRDTO.setM_id(m_id);
					try {
						list = smartRDAO.list(smartRDTO);
						for (int i = 0; i < list.size(); i++) {
							smartRDTO = list.get(i);
							Object[] r = { smartRDTO.getSr_id(), smartRDTO.getP_name(), smartRDTO.getRdate(),
									smartRDTO.getPrice(), smartRDTO.getM_id(), smartRDTO.getM_name() };
							model.addRow(r);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 검색 후 검색창 초기화
				t1.setText(null);
			}
		});

		// 버튼 클릭 시  예약메인(ReserveMain) 화면으로 이동하는 ActionListener
		JButton button = new JButton("뒤로가기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ReserveMain name = new ReserveMain(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		button.setFont(new Font("굴림", Font.BOLD, 14));
		button.setBounds(1027, 373, 97, 36);
		panel_3.add(button);
		
		// 화면이 선택되면(focus) 조회를 클릭하는 WindowFocusListener
		// 예약 변경/삭제 결과를 팝업이 닫힐 때 변견된 결과를 바로 보여주기 위함.
		f.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				btnNewButton_1.doClick();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		
		f.setLocation(x, y);
		f.setVisible(true);

	}

}
