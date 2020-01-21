package swingJBS;

import java.awt.Color;
import java.awt.Font;

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
import dao.PtableDAO;
import dao.SmartRDAO;
import dto.MembersDTO;
import dto.PtableDTO;
import dto.SmartRDTO;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

// 스마트예약 팝업(상품명 예약자명 날짜 등) : 선택한 예약 정보 확인 및 예약 확정
public class SmartReserve {
	private static JTable table_1;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private String sr_id;
	
	public SmartReserve(int x, int y, String m_id, String p_id, String rdate) throws Exception {
		
		// 예약 번호 생성 알고리즘, 앞 8자리 날짜 형식으로 뒤 6자리 순차적으로 증가하는 예약번호
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate today = LocalDate.now();
		DecimalFormat numFormat = new DecimalFormat("000000");
	
		// 상품예약테이블(samrtr) 데이터 가져오기 위한 DTO,DAO객체 생성
		SmartRDTO SmartRDTO = new SmartRDTO();
		SmartRDAO SmartRDAO = new SmartRDAO();
		// 가장 최근 데이터 불러오는 sql
		SmartRDAO.firstRow(SmartRDTO);
		// 데이터가 아무것도 없을 때(최초 데이터), 같은 날짜 예약이 있을 때, 당일 첫 예약일 때 구분
		// 최초데이터 :  날짜+순번1, 같은날짜가 있는 경우 뒤 6짜리 숫자로 받아 1씩 증가, 당일첫예약 : 날짜+순번1
		if(SmartRDTO.getSr_id()==null){
			sr_id = today.format(dateFormat)+numFormat.format(1);
		}else if(SmartRDTO.getSr_id().substring(0, 8).equals(today.format(dateFormat))) {
			int rNum = Integer.parseInt(SmartRDTO.getSr_id().substring(8, 14));
			sr_id = today.format(dateFormat)+numFormat.format(rNum+1);
		}else {
			sr_id = today.format(dateFormat)+numFormat.format(1);
		}
		
		// 회원테이블(members) 데이터 가져오기 위한 DTO,DAO객체 생성
		MembersDTO MembersDTO = new MembersDTO();
		MembersDAO MembersDAO = new MembersDAO();
		// 로그인 된 m_id값으로 select문 실행하여 예약자명 가저오기 위함
		MembersDTO.setM_id(m_id);
		MembersDAO.sele2(MembersDTO);		
		
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

		JLabel lblNewLabel = new JLabel(" 예약 정보를 확인 해주세요. ");
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

		JButton b1 = new JButton("확인");
		b1.setBounds(119, 356, 129, 36);
		panel_3.add(b1);
		b1.setFont(new Font("굴림", Font.BOLD, 14));

		// 취소 시 팝업 닫힘
		JButton b2 = new JButton("취소");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		b2.setBounds(302, 356, 97, 36);
		panel_3.add(b2);

		b2.setFont(new Font("굴림", Font.BOLD, 14));
		
		JLabel l1 = new JLabel("예약자명 : ");
		l1.setFont(new Font("굴림", Font.BOLD, 20));
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l1.setBounds(50, 38, 123, 28);
		panel_3.add(l1);
		
		JLabel l2 = new JLabel("예약상품 : ");
		l2.setFont(new Font("굴림", Font.BOLD, 20));
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setBounds(50, 112, 123, 28);
		panel_3.add(l2);
		
		JLabel l3 = new JLabel("이용날짜 : ");
		l3.setFont(new Font("굴림", Font.BOLD, 20));
		l3.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.setBounds(50, 192, 123, 28);
		panel_3.add(l3);
		
		JLabel l4 = new JLabel("가격 : ");
		l4.setFont(new Font("굴림", Font.BOLD, 20));
		l4.setHorizontalAlignment(SwingConstants.RIGHT);
		l4.setBounds(50, 269, 123, 28);
		panel_3.add(l4);
		
		t1 = new JTextField();
		t1.setBackground(Color.LIGHT_GRAY);
		t1.setEditable(false);
		t1.setFont(new Font("굴림", Font.BOLD, 20));
		t1.setBounds(185, 38, 286, 28);
		panel_3.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setFont(new Font("굴림", Font.BOLD, 20));
		t2.setEditable(false);
		t2.setColumns(10);
		t2.setBackground(Color.LIGHT_GRAY);
		t2.setBounds(185, 112, 286, 28);
		panel_3.add(t2);
		
		t3 = new JTextField();
		t3.setFont(new Font("굴림", Font.BOLD, 20));
		t3.setEditable(false);
		t3.setColumns(10);
		t3.setBackground(Color.LIGHT_GRAY);
		t3.setBounds(185, 192, 286, 28);
		panel_3.add(t3);
		
		t4 = new JTextField();
		t4.setFont(new Font("굴림", Font.BOLD, 20));
		t4.setEditable(false);
		t4.setColumns(10);
		t4.setBackground(Color.LIGHT_GRAY);
		t4.setBounds(185, 269, 286, 28);
		panel_3.add(t4);

		// 상품테이블(ptable) 데이터 가져오기 위한 DTO,DAO객체 생성
		PtableDTO PtableDTO = new PtableDTO();
		PtableDAO PtableDAO = new PtableDAO();
		// 넘겨받은 p_id로 예약할 상품 정보 가져옴
		PtableDTO.setP_id(p_id);
		PtableDAO.getInfor(PtableDTO);
		
		// 선택한 예약정보가 맞는지 확인 할 수 있는 view에 데이터 삽입
		t1.setText(MembersDTO.getM_name());
		t2.setText(PtableDTO.getP_name());
		t3.setText(rdate);
		t4.setText(PtableDTO.getPrice()+"원");
		
		// 확인버튼 클릭 시 텍스트필드에 있는 값을 받아(보여주지 않는 값은 DTO에서 get) 스마트예약테이블로 반환 
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SmartRDTO.setSr_id(sr_id);
				SmartRDTO.setP_id(p_id);
				SmartRDTO.setP_name(PtableDTO.getP_name());
				SmartRDTO.setRdate(rdate);
				SmartRDTO.setPrice(PtableDTO.getPrice());
				SmartRDTO.setM_id(m_id);
				SmartRDTO.setM_name(MembersDTO.getM_name());
				
				try {
					// inser문 실행
					SmartRDAO.insert(SmartRDTO);
					JOptionPane.showMessageDialog(null,"예약이 완료 되었습니다.", "예약완료", 1);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		f.setLocation(x+300, y);
		f.setVisible(true);

	}
}
