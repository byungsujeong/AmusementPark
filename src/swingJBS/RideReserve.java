package swingJBS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dao.MembersDAO;
import dao.RideDAO;
import dao.RideRDAO;
import dao.SmartRDAO;
import dao.TtableDAO;
import dto.MembersDTO;
import dto.RideDTO;
import dto.RideRDTO;
import dto.SmartRDTO;
import dto.TtableDTO;

//놀이기구 예약 팝업(놀이기구명 예약자명 날짜 등) : 선택한 예약 정보 확인 및 예약 확정
public class RideReserve {
	private static JTable table_1;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private String rr_id;
	private JTextField t5;
	private JTextField t6;
	private JTextField t0;

	public RideReserve(int x, int y, String m_id,String t_id, String r_name) throws Exception {
		// t_id가져오면(stime,time,btime) r_name(people, rLtall, r_Htall, r_id)
		// sr_id(입력받기)가져오면(rdate)

		// 회원테이블(members) 데이터 가져오기 위한 DTO,DAO객체 생성
		MembersDTO membersDTO = new MembersDTO();
		MembersDAO membersDAO = new MembersDAO();
		// 회원아이디(m_id) 보내고 select문 실행
		membersDTO.setM_id(m_id);
		membersDAO.sele2(membersDTO);

		// 타임테이블(ttable) 데이터 가져오기 위한 DTO,DAO객체 생성
		TtableDTO ttableDTO = new TtableDTO();
		TtableDAO ttableDAO = new TtableDAO();
		// 타임테이블 아이디(t_id) 보내고 select문 실행
		ttableDTO.setT_id(t_id);
		ttableDAO.selecttable(ttableDTO);

		// 놀이기구테이블(ride) 데이터 가져오기 위한 DTO,DAO객체 생성
		RideDTO rideDTO = new RideDTO();
		RideDAO rideDAO = new RideDAO();
		// 놀이기구 아이디(r_id) 보내고 select문 실행
		rideDTO.setR_name(r_name);
		rideDAO.getInfor(rideDTO);

		// 예약 번호 생성 알고리즘, 상품예약번호와 같은 방법에 날짜와 순번(6자리 -> 4자리) 사이에 t_id 추가
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate today = LocalDate.now();
		DecimalFormat numFormat = new DecimalFormat("0000");

		// 놀이기구예약테이블(rider) 데이터 가져오기 위한 DTO,DAO객체 생성
		RideRDTO rideRDTO = new RideRDTO();
		RideRDAO rideRDAO = new RideRDAO();
		// 가장 최근 데이터 불러오는 sql
		rideRDAO.firstRow(rideRDTO);
		// 데이터가 아무것도 없을 때(최초 데이터), 같은 날짜 예약이 있을 때, 당일 첫 예약일 때 구분
		// 최초데이터 :  날짜+t_id+순번1, 같은날짜가 있는 경우 뒤 4짜리 숫자로 받아 1씩 증가, 당일첫예약 : 날짜+t_id+순번1
		if(rideRDTO.getRr_id()==null) {
			rr_id = today.format(dateFormat) + t_id + numFormat.format(1);
		}else if (rideRDTO.getRr_id().substring(0, 8).equals(today.format(dateFormat))) {
			int rNum = Integer.parseInt(rideRDTO.getRr_id().substring(14, 18));
			rr_id = today.format(dateFormat) + t_id + numFormat.format(rNum + 1);
		} else {
			rr_id = today.format(dateFormat) + t_id + numFormat.format(1);
		}

		JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setTitle("놀이기구 시간표 정보");
		f.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(12, 59, 560, 492);
		f.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 10, 536, 43);
		panel.add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);

		JLabel lblNewLabel = new JLabel(" 자유이용권 예약번호를 입력하세요. ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(512, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setLayout(null);
		panel_2.setBounds(12, 10, 560, 39);
		f.getContentPane().add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(12, 63, 536, 419);
		panel.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);

		JButton btnNewButton = new JButton("예약번호 확인");

		btnNewButton.setBounds(185, 62, 129, 34);
		panel_3.add(btnNewButton);

		JButton b1 = new JButton("확인");
		b1.setEnabled(false);
		b1.setBounds(119, 356, 129, 36);
		panel_3.add(b1);
		b1.setFont(new Font("굴림", Font.BOLD, 14));

		// 버튼 클릭 시 팝업으로 뜬 예약화면 닫히는 ActionListener
		JButton b2 = new JButton("취소");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		b2.setBounds(302, 356, 97, 36);
		panel_3.add(b2);

		b2.setFont(new Font("굴림", Font.BOLD, 14));

		JLabel l0 = new JLabel("예약번호 : ");
		l0.setHorizontalAlignment(SwingConstants.RIGHT);
		l0.setFont(new Font("굴림", Font.BOLD, 20));
		l0.setBounds(50, 20, 123, 28);
		panel_3.add(l0);

		JLabel l1 = new JLabel("예약자명 : ");
		l1.setFont(new Font("굴림", Font.BOLD, 20));
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l1.setBounds(50, 106, 123, 28);
		panel_3.add(l1);

		JLabel l2 = new JLabel("놀이기구명 : ");
		l2.setFont(new Font("굴림", Font.BOLD, 20));
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setBounds(26, 152, 151, 28);
		panel_3.add(l2);

		JLabel l3 = new JLabel("이용날짜 : ");
		l3.setFont(new Font("굴림", Font.BOLD, 20));
		l3.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.setBounds(50, 194, 123, 28);
		panel_3.add(l3);

		JLabel l4 = new JLabel("탑승시간 : ");
		l4.setHorizontalAlignment(SwingConstants.RIGHT);
		l4.setFont(new Font("굴림", Font.BOLD, 20));
		l4.setBounds(50, 232, 123, 28);
		panel_3.add(l4);

		JLabel l5 = new JLabel("시작시간 : ");
		l5.setFont(new Font("굴림", Font.BOLD, 20));
		l5.setHorizontalAlignment(SwingConstants.RIGHT);
		l5.setBounds(50, 270, 123, 28);
		panel_3.add(l5);

		JLabel l6 = new JLabel("종료시간 : ");
		l6.setHorizontalAlignment(SwingConstants.RIGHT);
		l6.setFont(new Font("굴림", Font.BOLD, 20));
		l6.setBounds(50, 308, 123, 28);
		panel_3.add(l6);

		t0 = new JTextField();
		t0.setBounds(185, 20, 286, 32);
		panel_3.add(t0);
		t0.setColumns(10);

		t1 = new JTextField();
		t1.setBackground(Color.LIGHT_GRAY);
		t1.setEditable(false);
		t1.setFont(new Font("굴림", Font.BOLD, 20));
		t1.setBounds(185, 106, 286, 28);
		panel_3.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setFont(new Font("굴림", Font.BOLD, 20));
		t2.setEditable(false);
		t2.setColumns(10);
		t2.setBackground(Color.LIGHT_GRAY);
		t2.setBounds(185, 152, 286, 28);
		panel_3.add(t2);

		t3 = new JTextField();
		t3.setFont(new Font("굴림", Font.BOLD, 20));
		t3.setEditable(false);
		t3.setColumns(10);
		t3.setBackground(Color.LIGHT_GRAY);
		t3.setBounds(185, 194, 286, 28);
		panel_3.add(t3);

		t4 = new JTextField();
		t4.setFont(new Font("굴림", Font.BOLD, 20));
		t4.setEditable(false);
		t4.setColumns(10);
		t4.setBackground(Color.LIGHT_GRAY);
		t4.setBounds(185, 232, 286, 28);
		panel_3.add(t4);

		t5 = new JTextField();
		t5.setFont(new Font("굴림", Font.BOLD, 20));
		t5.setEditable(false);
		t5.setColumns(10);
		t5.setBackground(Color.LIGHT_GRAY);
		t5.setBounds(185, 270, 286, 28);
		panel_3.add(t5);

		t6 = new JTextField();
		t6.setFont(new Font("굴림", Font.BOLD, 20));
		t6.setEditable(false);
		t6.setColumns(10);
		t6.setBackground(Color.LIGHT_GRAY);
		t6.setBounds(185, 308, 286, 28);
		panel_3.add(t6);

		// 예약번호 확인하여 자유이용권인 경우에만 놀이기구 예약할 수 있도록 ActionListener 정의
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 예약 내역 객체 생성
				SmartRDTO smartRDTO = new SmartRDTO();
				SmartRDAO smartRDAO = new SmartRDAO();
				// 입력된 예약번호를 받아 DTO로 반환 
				smartRDTO.setSr_id(t0.getText());
				try {
					smartRDAO.getInfor(smartRDTO);
					// 회원아이디(m_id)가 로그인된 회원아이디와 일치 하지 않는 경우 오류 메세지 출력 
					if(!smartRDTO.getM_id().equals(m_id)) {
						JOptionPane.showMessageDialog(null, "본인 예약번호가 아닙니다.", "예약불가", 1);
					// 자유이용권인 경우 예약 진행할 수 있도록 텍스트 값 가져오고, 확인 버튼 활성 / 예약번호확인 버튼 비활성
					}else if (smartRDTO.getP_name().substring(0, 2).equals("자유")) {
						b1.setEnabled(true);
						t1.setText(membersDTO.getM_name());
						t2.setText(r_name);
						t3.setText(smartRDTO.getRdate());
						t4.setText(ttableDTO.getStime());
						t5.setText(ttableDTO.getFtime());
						t6.setText(ttableDTO.getBtime());
						t0.setEnabled(false);
						btnNewButton.setEnabled(false);
					// 자유이용권이 아닌 경우 오류 메세지 출력
					} else {
						JOptionPane.showMessageDialog(null, "자유이용권으로만 예약 가능합니다.", "예약불가", 1);
					}
					// 입력한 예약번호가 null인 경우 오류 메세지 출력
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 예약번호 입니다.", "예약불가", 1);
					e1.printStackTrace();
				}

			}
		});

		// 번튼 클릭 시 예약(insert) 실행 되는 ActionListener
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rideRDTO.setRdate(t3.getText());
				rideRDTO.setT_id(t_id);
				try {
					rideRDAO.count(rideRDTO);
					
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// 관리자(root)인 경우 제약조건 상관없이 예약
				if (m_id.equals("root")) {
					rideRDTO.setRr_id(rr_id);
					rideRDTO.setR_name(r_name);
					rideRDTO.setSr_id(t0.getText());
					rideRDTO.setRdate(t3.getText());
					rideRDTO.setStime(t4.getText());
					rideRDTO.setFtime(t5.getText());
					rideRDTO.setBtime(t6.getText());
					rideRDTO.setM_id(m_id);
					rideRDTO.setM_name(t1.getText());
					rideRDTO.setR_id(rideDTO.getR_id());
					rideRDTO.setT_id(t_id);
					try {
						rideRDAO.insert(rideRDTO);
						JOptionPane.showMessageDialog(null, "예약이 완료 되었습니다.", "예약완료", 1);
						f.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				// 키 제약 조건
				}else if(rideDTO.getR_ltall()>membersDTO.getTall()||rideDTO.getR_htall()<membersDTO.getTall()) {
					JOptionPane.showMessageDialog(null, "키 제한에 부합됩니다.", "예약불가", 1);
				// 정원초과 제약조건
				}else if(rideDTO.getPeople()<=rideRDTO.getCount()) {
					JOptionPane.showMessageDialog(null, "예약이 가득 찼습니다.", "예약불가", 1);
				// 제약 조건에 걸리지 않는 경우 예약(insert) 실행
				}else {
					rideRDTO.setRr_id(rr_id);
					rideRDTO.setR_name(r_name);
					rideRDTO.setSr_id(t0.getText());
					rideRDTO.setRdate(t3.getText());
					rideRDTO.setStime(t4.getText());
					rideRDTO.setFtime(t5.getText());
					rideRDTO.setBtime(t6.getText());
					rideRDTO.setM_id(m_id);
					rideRDTO.setM_name(t1.getText());
					rideRDTO.setR_id(rideDTO.getR_id());
					rideRDTO.setT_id(t_id);
					try {
						rideRDAO.insert(rideRDTO);
						JOptionPane.showMessageDialog(null, "예약이 완료 되었습니다.", "예약완료", 1);
						f.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		f.setLocation(x + 300, y);
		f.setVisible(true);

	}
}
