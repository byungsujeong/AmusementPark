package swingJBS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import dto.MembersDTO;
import dto.PtableDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// 스마트예약(상품테이블) : 예약진행 화면 
public class PtableList {

	public PtableList(int x, int y, String m_id) throws Exception {
		
		// 상품테이블(ptable) 데이터 가져오기 위한 DTO,DAO객체 생성
		PtableDAO ptableDAO = new PtableDAO();
		PtableDTO ptableDTO = new PtableDTO();
		// ArrayList타입 list 변수 선언하여 DAO에서 select문 실행결과 받아옴
		ArrayList<PtableDTO> list = ptableDAO.list(ptableDTO);
		
		// 에약날짜 생성하기 위해 LocalDate타입 변수 및 데이터 포멧 생성
		LocalDate today = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// 당일부터 7일까지의 날짜만 예약 가능하도록 설정
		String[] rdate = new String[7];
		for (int i = 0; i < rdate.length; i++) {
			rdate[i] = today.plusDays(i).format(format);
		}
		
		// 상품리스트 필드명 생성
		String[] items = { "순번", "상품명", "가격"};
		// JTable 타입 객체에 넣어줄 default모델 생성 
		DefaultTableModel model = new DefaultTableModel(items,0);
		
		// DAO에서 반환된 list를 그 크기만큼 위에서 선언된 변수 DTO에 담에주고 모델 각 row에 추가  
		for (int i = 0; i < list.size(); i++) {
			ptableDTO = list.get(i);
			Object[] r = {i+1, ptableDTO.getP_name(), ptableDTO.getPrice()};
			model.addRow(r);
		}
		
		// view에서 안보이는 상품ID(p_id)를 예약 시 키값으로 넘기기 위해 따로 생성
		String[] p_id = new String[list.size()];
		for (int i = 0; i < p_id.length; i++) {
			ptableDTO = list.get(i);
			p_id[i] = ptableDTO.getP_id();
		}

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

		JLabel lblNewLabel = new JLabel(" 예약 날짜 : ");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(165, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);

		// 예약날짜는 정해진 폼으로 데이터 입력 받을 수 있게 콤보박스 생성하여 rdate입력
		JComboBox comboBox = new JComboBox(rdate);
		comboBox.setLocation(131, 10);
		comboBox.setSize(295, 22);
		comboBox.setFont(new Font("굴림", Font.PLAIN, 16));
		panel_1.add(comboBox);

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
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(12, 10, 847, 399);
		table_1.setBounds(12, 403, 641, -392);
		panel_3.add(scrollPane);

		// 버튼 클릭 시 스마트예약(SmartReserve 클래스) 화면을 팝업으로 띄우는 ActionListener
		JButton b3 = new JButton("예약하기");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 했을 때만 예약 가능하게 if문
				if(!m_id.equals("")) {
					try {
						// 3. 상품정보 키값(p_id) 넘기기 위해 getSelectedItem으로 선택된 row값을 통해 위에서 미리 생성한 p_id[] 배열값을 반환
						// 4. 예약 날짜 넘기기 위해 콤보박스 값 반환
						SmartReserve name = new SmartReserve(f.getLocation().x, f.getLocation().y,  m_id, p_id[table_1.getSelectedRow()], (String) comboBox.getSelectedItem());
					} catch (Exception e1) {
						e1.printStackTrace();
						// 선택된 상품 없을 경우 발생되는 에러
						JOptionPane.showMessageDialog(null, "상품을 선택하세요.");
					}
				}else {
					int jop = JOptionPane.showConfirmDialog(null, "로그인 후 예약 가능합니다.", "예약불가", 0, 3);
					if(jop==0) {
					LogIn name = new LogIn(f.getLocation().x, f.getLocation().y);
					f.dispose();
					}
				}
				
			}
		});
		b3.setFont(new Font("굴림", Font.BOLD, 14));
		b3.setBounds(894, 373, 129, 36);
		panel_3.add(b3);

		// 버튼 클릭 시 예약메뉴(ReserveMain 클래스) 화면으로 이동하는 ActionListener
		JButton b4 = new JButton("뒤로가기");
		b4.addActionListener(new ActionListener() {
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

		b4.setFont(new Font("굴림", Font.BOLD, 14));
		b4.setBounds(1027, 373, 97, 36);
		panel_3.add(b4);
		
		// 버튼 클릭 시 상품추가(PtableInsert 클래스) 화면으로 이동하는 ActionListener
		JButton b5 = new JButton("추가");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					PtableInsert name = new PtableInsert(f.getLocation().x, f.getLocation().y, m_id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.dispose();
				
			}
		});
		b5.setBounds(926, 327, 97, 36);
		
		// 버튼 클릭 시 상품수정/삭제(PtableUpDel 클래스) 화면으로 이동하는 ActionListener
		JButton b6 = new JButton("수정/삭제");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					PtableUpDel name = new PtableUpDel(f.getLocation().x, f.getLocation().y, m_id, p_id[table_1.getSelectedRow()]);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		b6.setBounds(1027, 327, 97, 36);
		
		// 관리자만 추가, 수정/삭제 버튼 보이도록 설정 
		if(m_id.equals("root")) {
			panel_3.add(b5);
			panel_3.add(b6);
		}

		f.setLocation(x, y);
		f.setVisible(true);

	}
}
