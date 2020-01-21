package swingJBS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.PtableDAO;
import dao.RideRDAO;
import dao.SmartRDAO;
import dto.MembersDTO;
import dto.PtableDTO;
import dto.RideRDTO;
import dto.SmartRDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

// 놀이기구 예약 내역 확인 화면
public class RideCheck {

	public RideCheck(int x, int y, String m_id) throws Exception {

		// 필드명 생성
		String[] items = { "예약번호", "놀이기구명", "예약날짜", "탑승시간", "시작시간", "종료시간","예약자ID", "예약자"};
		
		// 테이블에 넣을 default모델 생성하여 row 0으로 설정
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

		// 버튼 클릭 시 예약정보(rider 테이블)row 삭제하는  ActionListener
		JButton btnNewButton = new JButton("예약취소");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 예약내역 정보 가져오기 위한 DAO, DTO 객체 생성
				RideRDTO rideRDTO = new RideRDTO();
				RideRDAO rideRDAO = new RideRDAO();
				// 클릭한 행 값을 가져와 예약 키값(rr_id)을 DTO로 반환
				rideRDTO.setRr_id((String) model.getValueAt(table_1.getSelectedRow(), 0));
				
				try {
					// 예약 취소 여부 확인 후 확인 클릭 시 delete문 실행
					int jop = JOptionPane.showConfirmDialog(null, "예약을 취소하시겠습니까?", "예약취소", 0, 3);
					if(jop==0) {
						rideRDAO.delete(rideRDTO);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "예약내역을 선택하세요.");
				}
				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 14));
		btnNewButton.setBounds(894, 373, 129, 36);
		panel_3.add(btnNewButton);
		
		// 버튼 클릭 시 예약번호(rr_id) 유사값(like)으로 예약 내역 보여주는 검색 버튼 ActionListener
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				
				// 예약 내역(rider 테이블) 가져오기 위해 DTO, DAO 객체 생성 
				RideRDTO rideRDTO = new RideRDTO();
				RideRDAO rideRDAO = new RideRDAO();
				// 관리자 로그인 시  예약 내역 전부 보여줌
				if (m_id.equals("root")) {
					// 데이터 받아 올 ArrayList타입 변수 선언
					ArrayList<RideRDTO> list;
					try {
						// 텍스트필드에 입력한 값가져와 DTO로 반환
						rideRDTO.setRr_id(t1.getText());
						// DAO에서 Select문 실행한 결과 list변수에 가져오기
						list = rideRDAO.listAdmin(rideRDTO);
						// list에 들어간 ArrayList값 각 index별로 DTO에 담고 모델에 row추가
						for (int i = 0; i < list.size(); i++) {
							rideRDTO = list.get(i);
							Object[] r = { rideRDTO.getRr_id(), rideRDTO.getR_name(), rideRDTO.getRdate(),
									rideRDTO.getBtime(), rideRDTO.getStime(), rideRDTO.getFtime(),rideRDTO.getM_id(), rideRDTO.getM_name() };
							model.addRow(r);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 관리자가 아닌 경우 본인 정보만 조회하도록 m_id값 DTO로 반환되는 부분 추가
					// 그 외 관리자와 동일
				} else {
					rideRDTO.setRr_id(t1.getText());
					rideRDTO.setM_id(m_id);
					try {
						ArrayList<RideRDTO> list = rideRDAO.list(rideRDTO);
						for (int i = 0; i < list.size(); i++) {
							rideRDTO = list.get(i);
							Object[] r = {  rideRDTO.getRr_id(), rideRDTO.getR_name(), rideRDTO.getRdate(),
									rideRDTO.getBtime(), rideRDTO.getStime(), rideRDTO.getFtime(),rideRDTO.getM_id(), rideRDTO.getM_name() };
							model.addRow(r);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				// 검색 후 텍스트 필드 초기화(아래 focus활용)
				t1.setText(null);
			}
		});

		// 버튼 클릭 시 예약메뉴(ReserveMain 클래스) 화면으로 가는 ActionListener
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
		// 예약 삭제 시 바로 변경된 결과를 바로 보여주기 위함.
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
