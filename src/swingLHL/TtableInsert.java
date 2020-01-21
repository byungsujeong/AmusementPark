// 해당 놀이기구 정보

package swingLHL;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import dao.RideDAO;
import dao.TtableDAO;
import dto.RideDTO;
import dto.TtableDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;


public class TtableInsert {
	private JTextField tname;
		
	public TtableInsert(int x, int y, String m_id, String r_name) throws Exception{
		
		RideDTO rideDTO = new RideDTO();
		RideDAO rideDAO = new RideDAO();
		
		rideDTO.setR_name(r_name);
		rideDAO.getInfor(rideDTO);
		
		TtableDTO ttableDTO = new TtableDTO();
		TtableDAO ttableDAO = new TtableDAO();
		
		
//////////------------- 프레임 생성  ------------- //////////
		JFrame f = new JFrame();
		f.setSize(1200, 600);
		f.setTitle("놀이기구 시간표 추가");
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
		
//////////---------- 놀이기구 관리설정 콤보박스 ---------- //////////
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 10, 1136, 43);
		panel.add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);

		// 놀이기구 이름 레이블 생성
		JLabel lblNewLabel = new JLabel("놀이기구 시간표를 입력하세요.");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(400, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		
		
////////////---------- 놀이기구  정보 패널 ---------- ////////// 
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 63, 1136, 419);
		panel.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 1112, 360);
		panel_3.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(null);
		
////////////---------- 놀이기구  정보 내용 (레이블+텍스트필드) ---------- ////////// 
		
		// 포맷설정
		MaskFormatter tidFormat = null;
		try {
			tidFormat = new MaskFormatter(rideDTO.getR_id()+"###");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		
		MaskFormatter timeformat = null;
		try {
			timeformat = new MaskFormatter("##시 ##분");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
				
		// 놀이기구 아이디(레이블+텍스트)
		JLabel labelId = new JLabel("시간표 아이디");
		labelId.setFont(new Font("굴림", Font.BOLD, 18));
		labelId.setBounds(23, 10, 163, 25);
		panel_4.add(labelId);
		
		JFormattedTextField ttid = new JFormattedTextField(tidFormat);
		ttid.setFont(new Font("굴림", Font.PLAIN, 15));
		ttid.setBounds(198, 10, 316, 25);
		panel_4.add(ttid);
		ttid.setColumns(10);
		
		// 놀이기구 아이디(레이블+텍스트)
		JLabel label = new JLabel("놀이기구 아이디");
		label.setFont(new Font("굴림", Font.BOLD, 18));
		label.setBounds(23, 50, 163, 25);
		panel_4.add(label);
		
		JFormattedTextField trid = new JFormattedTextField();
		trid.setText(rideDTO.getR_id());
		trid.setEnabled(false);
		trid.setFont(new Font("굴림", Font.PLAIN, 15));
		trid.setColumns(10);
		trid.setBounds(198, 50, 316, 25);
		panel_4.add(trid);
		
		JFormattedTextField tstime = new JFormattedTextField(timeformat);
		tstime.setFont(new Font("굴림", Font.PLAIN, 15));
		tstime.setColumns(10);
		tstime.setBounds(198, 130, 316, 25);
		panel_4.add(tstime);
		
		JFormattedTextField tftime = new JFormattedTextField(timeformat);
		tftime.setFont(new Font("굴림", Font.PLAIN, 15));
		tftime.setColumns(10);
		tftime.setBounds(198, 170, 316, 25);
		panel_4.add(tftime);
		
		JFormattedTextField tbtime = new JFormattedTextField(timeformat);
		tbtime.setFont(new Font("굴림", Font.PLAIN, 15));
		tbtime.setColumns(10);
		tbtime.setBounds(198, 210, 316, 25);
		panel_4.add(tbtime);
		
		JLabel label_1 = new JLabel("놀이기구 이름");
		label_1.setFont(new Font("굴림", Font.BOLD, 18));
		label_1.setBounds(23, 90, 163, 25);
		panel_4.add(label_1);
		
		JLabel label_2 = new JLabel("운행시작시간");
		label_2.setFont(new Font("굴림", Font.BOLD, 18));
		label_2.setBounds(23, 130, 163, 25);
		panel_4.add(label_2);
		
		JLabel label_3 = new JLabel("운행종료시간");
		label_3.setFont(new Font("굴림", Font.BOLD, 18));
		label_3.setBounds(23, 170, 163, 25);
		panel_4.add(label_3);
		
		JLabel label_4 = new JLabel("탑승시간");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(23, 210, 163, 25);
		panel_4.add(label_4);
		
		tname = new JTextField();
		tname.setText(rideDTO.getR_name());
		tname.setEnabled(false);
		tname.setFont(new Font("굴림", Font.PLAIN, 15));
		tname.setColumns(10);
		tname.setBounds(198, 90, 316, 25);
		panel_4.add(tname);
			


//////////------------- 추가하기 버튼  ------------- //////////	
		
		TtableDAO dao = new TtableDAO();
		TtableDTO dto = new TtableDTO();
		
		JButton insertbtn = new JButton("추가하기");
		insertbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String t_id = ttid.getText();
					String r_id = trid.getText();
					String r_name = tname.getText();
					String stime = tstime.getText();
					String ftime = tftime.getText();
					String btime = tbtime.getText();
					
					dto.setT_id(t_id);
					dto.setR_id(r_id);
					dto.setR_name(r_name);
					dto.setStime(stime);;
					dto.setFtime(ftime);;
					dto.setBtime(btime);;
					
					
					dao.insert(dto);
					
					JOptionPane.showMessageDialog(null, "추가되었습니다.");
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		insertbtn.setFont(new Font("굴림", Font.BOLD, 14));
		insertbtn.setBounds(924, 373, 97, 36);
		panel_3.add(insertbtn);
				

//////////------------- 닫기  버튼  ------------- //////////			 
		
		JButton cbtn = new JButton("닫기");
	
		cbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
						f.dispose();
						String r_name = tname.getText();
						TtableInfo name = new TtableInfo(f.getLocation().x, f.getLocation().y, m_id, r_name);
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		cbtn.setFont(new Font("굴림", Font.BOLD, 14));
		cbtn.setBounds(1027, 373, 97, 36);
		panel_3.add(cbtn);
				
		f.setLocation(x, y);
		f.setVisible(true);

	}
}