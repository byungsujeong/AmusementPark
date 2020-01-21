// (해당) 놀이기구 시간표

package swingLHL;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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

import dao.RideDAO;
import dao.TtableDAO;
import dto.RideDTO;
import dto.TtableDTO;
import swingJBS.ReserveMain;
import swingJBS.RideReserve;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

public class TtableInfo {

	public TtableInfo(int x, int y, String m_id, String r_name) throws Exception {

//////////------------- 프레임 생성  ------------- //////////
		JFrame f = new JFrame();
		f.setSize(1200, 600);
		f.setTitle("놀이기구 시간표 정보");
		f.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(12, 59, 1160, 492);
		f.getContentPane().add(panel);

//////////- 회원정보 패널(로그아웃+회원정보수정+로그인정보) - ////////		
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

//////////---------- 놀이기구 이름 콤보박스 ---------- //////////		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 10, 1136, 43);
		panel.add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);

		// 놀이기구 이름 레이블 생성
		JLabel lblNewLabel = new JLabel(" 놀이기구 이름 : ");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(165, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);

		// 놀이기구 이름 콤보박스 생성
		RideDTO rideDTO = new RideDTO();
		RideDAO rideDAO = new RideDAO();
		
		ArrayList<RideDTO> namelist = rideDAO.selectCombo();
		String[] r_names = new String[namelist.size()];
		
		for (int i = 0; i < r_names.length; i++) {
			rideDTO = namelist.get(i);
			r_names[i] = rideDTO.getR_name();
		}
		
		JComboBox comboBox = new JComboBox(r_names);
		
		comboBox.setLocation(131, 10);
		comboBox.setSize(295, 22);
		comboBox.setFont(new Font("굴림", Font.PLAIN, 16));
		panel_1.add(comboBox);
		
////////////---------- 놀이기구  시간표 정보 ---------- //////////
		
		// 시간표 첫 화면
		String[] items = { "T_id", "stime", "ftime", "btime" };
		DefaultTableModel model = new DefaultTableModel(items, 0);
	
		comboBox.setSelectedItem(r_name);
		
		TtableDTO ttableDTO = new TtableDTO();
		TtableDAO ttableDAO = new TtableDAO();
		
		String index = (String) comboBox.getSelectedItem();
		ttableDTO.setR_name(index);
		ArrayList<TtableDTO> list = ttableDAO.selecttable1(ttableDTO);

		String[][] data = new String[list.size()][];
		for (int i = 0; i < data.length; i++) {
			ttableDTO = list.get(i);
			String[] r = { ttableDTO.getT_id(), ttableDTO.getStime(), ttableDTO.getFtime(), ttableDTO.getBtime() };
			data[i] = r;
			model.addRow(r);
		}

		// 콤보박스 변경시 해당 놀이기구의 시간표 
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				model.setRowCount(0);

				TtableDTO ttableDTO = new TtableDTO();
				TtableDAO ttableDAO = new TtableDAO();
				String index = (String) comboBox.getSelectedItem();
				ttableDTO.setR_name(index);
				ArrayList<TtableDTO> list;
				try {
					list = ttableDAO.selecttable1(ttableDTO);
					String[][] data = new String[list.size()][];
					for (int i = 0; i < data.length; i++) {
						ttableDTO = list.get(i);
						String[] r = { ttableDTO.getT_id(), ttableDTO.getStime(), ttableDTO.getFtime(),
								ttableDTO.getBtime() };
						data[i] = r;
						model.addRow(r);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		// 놀이기구 시간표 패널 생성		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 63, 1136, 419);
		panel.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);

		JTable table_1 = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setBounds(12, 10, 1112, 360);
		table_1.setBounds(12, 403, 641, -392);
		panel_3.add(scrollPane);
		
		// 예약하기 버튼
		JButton rbtn = new JButton("예약하기");
		rbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!m_id.equals("")) {
					try {
						RideReserve name = new RideReserve(f.getLocation().x, f.getLocation().y, m_id, (String) model.getValueAt(table_1.getSelectedRow(),0), (String) comboBox.getSelectedItem());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
		rbtn.setFont(new Font("굴림", Font.BOLD, 14));
		rbtn.setBounds(788, 373, 97, 36);
		panel_3.add(rbtn);

		// 뒤로가기 버튼(RideInfo2로 이동)
		JButton bbtn = new JButton("놀이기구 정보");
		bbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RideInfo2 name = new RideInfo2(f.getLocation().x, f.getLocation().y, m_id, (String) comboBox.getSelectedItem());// r_name 못가져와서 바꿈
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				f.dispose();
			}
		});
		bbtn.setFont(new Font("굴림", Font.BOLD, 14));
		bbtn.setBounds(984, 373, 140, 36);
		panel_3.add(bbtn);
		
		JButton button = new JButton("예약메뉴");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReserveMain name = new ReserveMain(f.getLocation().x, f.getLocation().y, m_id);
				f.dispose();
			}
		});
		button.setFont(new Font("굴림", Font.BOLD, 14));
		button.setBounds(886, 373, 97, 36);
		panel_3.add(button);
		
		JButton button_1 = new JButton("추가");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TtableInsert name = new TtableInsert(f.getLocation().x, f.getLocation().y, m_id, (String) comboBox.getSelectedItem());
					f.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("굴림", Font.BOLD, 14));
		button_1.setBounds(641, 373, 72, 36);
		
		JButton button_2 = new JButton("관리");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TtableManagement name = new TtableManagement(f.getLocation().x, f.getLocation().y, m_id, (String) model.getValueAt(table_1.getSelectedRow(), 0));
					f.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,"시간을 선택하세요");
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("굴림", Font.BOLD, 14));
		button_2.setBounds(715, 373, 72, 36);
		if(m_id.equals("root")) {
			panel_3.add(button_1);
			panel_3.add(button_2);			
		}

		f.setLocation(x, y);
		f.setVisible(true);

	}
}
