package swingJBS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dao.PtableDAO;
import dao.SmartRDAO;
import dto.PtableDTO;
import dto.SmartRDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//상품예약내역 변경/삭제 팝업
public class SmartChange {
	private static JTable table_1;
	private JTextField t1;
	private JTextField t2;
	private String sr_id;
	
	public SmartChange(int x, int y, String m_id, String sr_id) throws Exception {
	
		// 상품예약테이블(samrtr) 데이터 가져오기 위한 DTO,DAO객체 생성
		SmartRDTO smartRDTO = new SmartRDTO();
		SmartRDAO smartRDAO = new SmartRDAO();
		//
		smartRDTO.setSr_id(sr_id);
		smartRDAO.getInfor(smartRDTO);
		
		PtableDTO ptableDTO = new PtableDTO();
		PtableDAO ptableDAO = new PtableDAO();
		ArrayList<PtableDTO> list = ptableDAO.list(ptableDTO);
		String[] p_names = new String[list.size()];
		for (int i = 0; i < p_names.length; i++) {
			ptableDTO = list.get(i);
			p_names[i] = ptableDTO.getP_name();
		}
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String[] rdate = new String[7];
		for (int i = 0; i < rdate.length; i++) {
			rdate[i] = today.plusDays(i).format(format);
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

		JButton b1 = new JButton("예약변경");
		b1.setBounds(75, 356, 129, 36);
		panel_3.add(b1);
		b1.setFont(new Font("굴림", Font.BOLD, 14));

		JButton b3 = new JButton("닫기");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		JButton b2 = new JButton("예약취소");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int jop = JOptionPane.showConfirmDialog(null, "예약을 취소하시겠습니까?", "예약취소", 0, 3);
					if(jop==0) {
						smartRDAO.delete(smartRDTO);
						f.dispose();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		b2.setFont(new Font("굴림", Font.BOLD, 14));
		b2.setBounds(218, 356, 129, 36);
		panel_3.add(b2);
		b3.setBounds(363, 356, 97, 36);
		panel_3.add(b3);

		b3.setFont(new Font("굴림", Font.BOLD, 14));
		
		JLabel l1 = new JLabel("예약번호 : ");
		l1.setFont(new Font("굴림", Font.BOLD, 20));
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l1.setBounds(50, 38, 123, 28);
		panel_3.add(l1);
		
		JLabel l2 = new JLabel("예약자 : ");
		l2.setFont(new Font("굴림", Font.BOLD, 20));
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setBounds(50, 112, 123, 28);
		panel_3.add(l2);
		
		JLabel l3 = new JLabel("예약상품 : ");
		l3.setFont(new Font("굴림", Font.BOLD, 20));
		l3.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.setBounds(50, 192, 123, 28);
		panel_3.add(l3);
		
		JLabel l4 = new JLabel("이용날짜 : ");
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
		
		JComboBox cb1 = new JComboBox(p_names);
		cb1.setBounds(185, 192, 286, 28);
		panel_3.add(cb1);
		
		JComboBox cb2 = new JComboBox(rdate);
		cb2.setBounds(185, 269, 286, 28);
		panel_3.add(cb2);
		
		t1.setText(smartRDTO.getSr_id());
		t2.setText(smartRDTO.getM_name());
		cb1.setSelectedItem(smartRDTO.getP_name());
		cb2.setSelectedItem(smartRDTO.getRdate());
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PtableDTO ptableDTO = new PtableDTO();
				
				try {
					int jop = JOptionPane.showConfirmDialog(null, "예약을 변경하시겠습니까?", "예약변경", 0, 3);
					if(jop==0) {
						ptableDTO.setP_name((String) cb1.getSelectedItem());
						ptableDAO.getInforChange(ptableDTO);
						smartRDTO.setP_id(ptableDTO.getP_id());
						smartRDTO.setP_name(ptableDTO.getP_name());
						smartRDTO.setRdate((String) cb2.getSelectedItem());
						smartRDTO.setPrice(ptableDTO.getPrice());
						smartRDTO.setSr_id(sr_id);	
						smartRDAO.update(smartRDTO);
						
						f.dispose();
					}
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
