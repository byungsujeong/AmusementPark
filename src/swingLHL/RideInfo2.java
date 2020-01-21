// 해당 놀이기구 정보

package swingLHL;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import dao.RideDAO;
import dto.MembersDTO;
import dto.RideDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;


public class RideInfo2 {
		
	public RideInfo2(int x, int y, String m_id, String r_name)throws Exception{
		
		RideDTO rideDTO = new RideDTO();
		RideDAO rideDAO = new RideDAO();
		
		
//////////------------- 프레임 생성  ------------- //////////
		JFrame f = new JFrame();
		f.setSize(1200, 600);
		f.setTitle("놀이기구 정보");
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
		JComboBox comboBox = new JComboBox();
				
		try {
			ArrayList<RideDTO> namelist = rideDAO.selectname();
			Object[] data1 = new Object[namelist.size()];
			for (int i = 0; i < data1.length; i++) {
				rideDTO = namelist.get(i);
				data1[i] = rideDTO.getR_name();
				comboBox.addItem(data1[i]);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		comboBox.setLocation(131, 10);
		comboBox.setSize(295, 22);
		comboBox.setFont(new Font("굴림", Font.PLAIN, 16));
		panel_1.add(comboBox);
		
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
		
		// 놀이기구 이름 레이블 생성
		JLabel label = new JLabel("놀이기구 이름 : ");
		label.setFont(new Font("굴림", Font.BOLD, 17));
		label.setBounds(287, 73, 189, 15);
		panel_4.add(label);
		
		JLabel namelabel = new JLabel("New label");
		namelabel.setFont(new Font("굴림", Font.PLAIN, 17));
		namelabel.setBounds(514, 73, 165, 15);
		panel_4.add(namelabel);
		
		// 놀이기구 위치 레이블 생성
		JLabel label_1 = new JLabel("놀이기구 위치 : ");
		label_1.setFont(new Font("굴림", Font.BOLD, 17));
		label_1.setBounds(287, 98, 189, 15);
		panel_4.add(label_1);
		
		JLabel locationlabel = new JLabel("New label");
		locationlabel.setFont(new Font("굴림", Font.PLAIN, 17));
		locationlabel.setBounds(514, 98, 165, 15);
		panel_4.add(locationlabel);
		
		// 놀이기구 정원 레이블 생성
		JLabel label_3 = new JLabel("놀이기구 정원 : ");
		label_3.setFont(new Font("굴림", Font.BOLD, 17));
		label_3.setBounds(287, 123, 189, 15);
		panel_4.add(label_3);
		
		JLabel peoplelabel = new JLabel("New label");
		peoplelabel.setFont(new Font("굴림", Font.PLAIN, 17));
		peoplelabel.setBounds(514, 123, 165, 15);
		panel_4.add(peoplelabel);
		
		// 놀이기구 최소 키 제한 레이블 생성
		JLabel label_5 = new JLabel("놀이기구 최소 키 제한 : ");
		label_5.setFont(new Font("굴림", Font.BOLD, 17));
		label_5.setBounds(287, 148, 215, 15);
		panel_4.add(label_5);
		
		JLabel ltalllabel = new JLabel("New label");
		ltalllabel.setFont(new Font("굴림", Font.PLAIN, 17));
		ltalllabel.setBounds(514, 148, 165, 15);
		panel_4.add(ltalllabel);
		
		// 놀이기구 최대 키 제한 레이블 생성
		JLabel label_8 = new JLabel("놀이기구 최대 키 제한 : ");
		label_8.setFont(new Font("굴림", Font.BOLD, 17));
		label_8.setBounds(287, 173, 215, 15);
		panel_4.add(label_8);
		
		JLabel htalllabel = new JLabel("New label");
		htalllabel.setFont(new Font("굴림", Font.PLAIN, 17));
		htalllabel.setBounds(514, 173, 165, 15);
		panel_4.add(htalllabel);
		
		// 놀이기구 소요시간 레이블 생성
		JLabel label_9 = new JLabel("놀이기구 소요시간 : ");
		label_9.setFont(new Font("굴림", Font.BOLD, 17));
		label_9.setBounds(12, 359, 189, 15);
		panel_4.add(label_9);
		
		JLabel ltimelabel = new JLabel("New label");
		ltimelabel.setFont(new Font("굴림", Font.PLAIN, 17));
		ltimelabel.setBounds(239, 359, 165, 15);
		panel_4.add(ltimelabel);
		
		// 놀이기구 설명 레이블 생성
		JLabel lblNewLabel_5 = new JLabel("<놀이기구 설명>");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel_5.setBounds(677, 14, 189, 15);
		panel_4.add(lblNewLabel_5);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		textPane.setFont(new Font("굴림", Font.PLAIN, 17));
		textPane.setBounds(677, 39, 421, 305);
		panel_4.add(textPane);
		
		// 놀이기구 이미지 레이블 생성
		JLabel imglabel = new JLabel();
		
		// 시간표보기 버튼 생성
		JButton tbtn = new JButton("시간표 보기");
		
////////////---------- 놀이기구 정보 첫 화면 ---------- ////////// 
		
		rideDTO.setR_name(r_name);
		

		comboBox.setSelectedItem(r_name);
		ArrayList<RideDTO> list;
		
		try {
			
			list = rideDAO.selectcontent(rideDTO);
								
			rideDTO = list.get(0);
			
			// 해당 놀이기구 이미지 
			
			ImageIcon icon = new ImageIcon(rideDTO.getImg());
			Image originimg = icon.getImage();
			Image changedImg = originimg.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
			ImageIcon Icon1 = new ImageIcon(changedImg);
			imglabel.setIcon(Icon1);
			imglabel.setBounds(12, 52, 250, 200);
			panel_4.add(imglabel);
			// 놀이기구 이름 
			namelabel.setText(rideDTO.getR_name());
			// 놀이기구 위치
			locationlabel.setText(rideDTO.getLocation());
			// 놀이기구 정원
			peoplelabel.setText(rideDTO.getPeople()+"명");
			// 놀이기구 최소 키 제한
			if(rideDTO.getR_ltall()==0) {
				ltalllabel.setText("");
			}else {
				ltalllabel.setText(rideDTO.getR_ltall()+"cm");					
			}
			// 놀이기구 최대 키 제한
			if(rideDTO.getR_htall()==0) {
				htalllabel.setText("");
			}else {
				htalllabel.setText(rideDTO.getR_htall()+"cm");
			}
			// 놀이기구 소요시간
			if(rideDTO.getLtime()==0) {
				ltimelabel.setText("");
			}else {
				ltimelabel.setText(rideDTO.getLtime()+"분");
			}
			// 놀이기구 설명
			textPane.setText(rideDTO.getR_content());
	
			// 시간표보기 버튼
			if(rideDTO.getAvailability().equals("가능")) {
				panel_3.add(tbtn);
				tbtn.setVisible(true);
			}else {
				tbtn.setVisible(false);
			}
					
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
////////////---------- 콤보박스 값 변경시 액션(놀이기구 정보 변경) ---------- //////////
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				RideDTO rideDTO = new RideDTO();
				RideDAO rideDAO = new RideDAO();
				
				String index = (String) comboBox.getSelectedItem();
				rideDTO.setR_name(index);
				
				ArrayList<RideDTO> list;
								
				try {
					
					list = rideDAO.selectcontent(rideDTO);
										
					rideDTO = list.get(0);
					
					// 해당 놀이기구 이미지 
					ImageIcon icon = new ImageIcon(rideDTO.getImg());

					Image originimg = icon.getImage();
					Image changedImg = originimg.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
					ImageIcon Icon1 = new ImageIcon(changedImg);
					imglabel.setIcon(Icon1);
					imglabel.setBounds(12, 12, 250, 200);
					panel_4.add(imglabel);
					// 놀이기구 이름 
					namelabel.setText(rideDTO.getR_name());
					// 놀이기구 위치
					locationlabel.setText(rideDTO.getLocation());
					// 놀이기구 정원
					peoplelabel.setText(rideDTO.getPeople()+"명");
					// 놀이기구 최소 키 제한
					if(rideDTO.getR_ltall()==0) {
						ltalllabel.setText("");
					}else {
						ltalllabel.setText(rideDTO.getR_ltall()+"cm");					
					}
					// 놀이기구 최대 키 제한
					if(rideDTO.getR_htall()==0) {
						htalllabel.setText("");
					}else {
						htalllabel.setText(rideDTO.getR_htall()+"cm");
					}
					// 놀이기구 소요시간
					if(rideDTO.getLtime()==0) {
						ltimelabel.setText("");
					}else {
						ltimelabel.setText(rideDTO.getLtime()+"분");
					}
					// 놀이기구 설명
					textPane.setText(rideDTO.getR_content());
					
					System.out.println(rideDTO.getAvailability());
					
					if(rideDTO.getAvailability().equals("가능")) {
						panel_3.add(tbtn);
						tbtn.setVisible(true);
						}else {
							tbtn.setVisible(false);
						}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
	
		});
		
		// 시간표 보기 버튼 (해당 놀이기구 시간표(TtableInfo)로 이동)		
		tbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TtableInfo timetableinfo = new TtableInfo(f.getLocation().x, f.getLocation().y, m_id, (String) comboBox.getSelectedItem());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();
			}
		});
		
		tbtn.setFont(new Font("굴림", Font.BOLD, 14));
		tbtn.setBounds(894, 373, 129, 36);
				
		// 뒤로가기 버튼 (RideInfo로 이동)
		JButton bbtn = new JButton("뒤로가기");
	
		
		String location = rideDTO.getLocation();
		bbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					RideInfo rideinfo = new RideInfo(f.getLocation().x, f.getLocation().y, m_id, location);
					System.out.println(location);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();
			}
		});
		
		bbtn.setFont(new Font("굴림", Font.BOLD, 14));
		bbtn.setBounds(1027, 373, 97, 36);
		panel_3.add(bbtn);
		
		JButton mbtn = new JButton("관리");
		mbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ridemanagement name = new Ridemanagement(f.getLocation().x, f.getLocation().y, m_id, r_name);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();
			}
		});
		mbtn.setFont(new Font("굴림", Font.BOLD, 14));
		mbtn.setBounds(789, 373, 97, 36);
		if(m_id.equals("root")) {
			panel_3.add(mbtn);	
		}
	
		f.setLocation(x, y);
		f.setVisible(true);

	}
}