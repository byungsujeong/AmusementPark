// 해당 놀이기구 정보

package swingLHL;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import dao.RideDAO;
import dto.RideDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;


public class RideInsert {
		
	public RideInsert(int x, int y, String m_id){
		
		RideDTO rideDTO = new RideDTO();
		RideDAO rideDAO = new RideDAO();
		
		
//////////------------- 프레임 생성  ------------- //////////
		JFrame f = new JFrame();
		f.setSize(1200, 600);
		f.setTitle("놀이기구 추가");
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
		JLabel lblNewLabel = new JLabel("추가할 놀이기구 정보를 입력하세요");
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
		
		
		NumberFormat intFormat = NumberFormat.getNumberInstance();
		MaskFormatter idFormat = null;
		try {
			idFormat = new MaskFormatter("R##");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		MaskFormatter imgformat = null;
		try {
			imgformat = new MaskFormatter("picture/R##.jpg");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		// 놀이기구 아이디(레이블+텍스트)
		JLabel labelId = new JLabel("Id");
		labelId.setFont(new Font("굴림", Font.BOLD, 18));
		labelId.setBounds(23, 10, 163, 25);
		panel_4.add(labelId);
		
		JFormattedTextField tid = new JFormattedTextField(idFormat);
		tid.setFont(new Font("굴림", Font.PLAIN, 15));
		tid.setBounds(198, 10, 316, 25);
		panel_4.add(tid);
		tid.setColumns(10);
		
		// 놀이기구 이름(레이블+텍스트)
		JLabel label = new JLabel("이름");
		label.setFont(new Font("굴림", Font.BOLD, 18));
		label.setBounds(23, 50, 163, 25);
		panel_4.add(label);
		
		JTextField tname = new JTextField();
		tname.setFont(new Font("굴림", Font.PLAIN, 15));
		tname.setColumns(10);
		tname.setBounds(198, 50, 316, 25);
		panel_4.add(tname);
		
		// 놀이기구 위치 (레이블+콤보박스) 
		JLabel label_1 = new JLabel("위치");
		label_1.setFont(new Font("굴림", Font.BOLD, 18));
		label_1.setBounds(23, 90, 163, 25);
		panel_4.add(label_1);
				
		JComboBox combolocation = new JComboBox();
		
		RideDAO dao = new RideDAO();
		RideDTO dto = new RideDTO();
	
		ArrayList<RideDTO> locationlist;
		try {
			locationlist = dao.selectlocation();
			Object[] data = new Object[locationlist.size()];
			for (int i = 0; i < data.length; i++) {
				RideDTO locationdto = locationlist.get(i);
				data[i] = locationdto.getLocation();
				combolocation.addItem(data[i]);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		combolocation.setFont(new Font("굴림", Font.PLAIN, 15));
		combolocation.setBounds(198, 90, 316, 25);
		panel_4.add(combolocation);
		
		// 놀이기구 정원 (레이블+텍스트)
		JLabel label_2 = new JLabel("정원");
		label_2.setFont(new Font("굴림", Font.BOLD, 18));
		label_2.setBounds(23, 130, 163, 25);
		panel_4.add(label_2);
		
		JFormattedTextField tpeople = new JFormattedTextField(intFormat);
		tpeople.setFont(new Font("굴림", Font.PLAIN, 15));
		tpeople.setColumns(10);
		tpeople.setBounds(198, 130, 316, 25);
		panel_4.add(tpeople);
		
		// 놀이기구 최소키 (레이블+텍스트)
		JLabel label_3 = new JLabel("최소키");
		label_3.setFont(new Font("굴림", Font.BOLD, 18));
		label_3.setBounds(23, 170, 163, 25);
		panel_4.add(label_3);
		
		JFormattedTextField tltall = new JFormattedTextField(intFormat);
		tltall.setFont(new Font("굴림", Font.PLAIN, 15));
		tltall.setColumns(10);
		tltall.setBounds(198, 170, 316, 25);
		panel_4.add(tltall);
		
		// 놀이기구 최대키 (레이블+텍스트)
		JLabel label_4 = new JLabel("최대키");
		label_4.setFont(new Font("굴림", Font.BOLD, 18));
		label_4.setBounds(23, 210, 163, 25);
		panel_4.add(label_4);
		
		JFormattedTextField thtall = new JFormattedTextField(intFormat);
		thtall.setFont(new Font("굴림", Font.PLAIN, 15));
		thtall.setColumns(10);
		thtall.setBounds(198, 210, 316, 25);
		panel_4.add(thtall);
		
		// 놀이기구 소요시간 (레이블+텍스트)
		JLabel label_5 = new JLabel("소요시간");
		label_5.setFont(new Font("굴림", Font.BOLD, 18));
		label_5.setBounds(23, 250, 163, 25);
		panel_4.add(label_5);
		
		JFormattedTextField tltime = new JFormattedTextField(intFormat);
		tltime.setFont(new Font("굴림", Font.PLAIN, 15));
		tltime.setColumns(10);
		tltime.setBounds(198, 250, 316, 25);
		panel_4.add(tltime);
		
		// 놀이기구 예약 가능 여부 (레이블+콤보박스)
		JLabel label_6 = new JLabel("예약 가능 여부");
		label_6.setFont(new Font("굴림", Font.BOLD, 18));
		label_6.setBounds(562, 10, 163, 25);
		panel_4.add(label_6);
		
		JComboBox comboability = new JComboBox();
		comboability.setModel(new DefaultComboBoxModel(new String[] {"가능", "불가능"}));
		comboability.setBounds(737, 10, 316, 25);
		panel_4.add(comboability);
	
		// 놀이기구 내용 (레이블+텍스트페인+스크롤페인)
		JLabel label_7 = new JLabel("내용");
		label_7.setFont(new Font("굴림", Font.BOLD, 18));
		label_7.setBounds(562, 50, 163, 25);
		panel_4.add(label_7);
		
		JTextPane tcontent = new JTextPane();
		tcontent.setBounds(198, 330, 316, 100);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(737, 50, 316, 298);
		scroll.setViewportView(tcontent);
		panel_4.add(scroll);
	
		// 놀이기구 이미지 (레이블+텍스트) 
		JLabel label_8 = new JLabel("이미지");
		label_8.setFont(new Font("굴림", Font.BOLD, 18));
		label_8.setBounds(23, 297, 163, 25);
		panel_4.add(label_8);
		
		JFormattedTextField timg = new JFormattedTextField(imgformat);
		timg.setFont(new Font("굴림", Font.PLAIN, 15));
		timg.setColumns(10);
		timg.setBounds(198, 299, 316, 25);
		panel_4.add(timg);
			
//////////------------- 추가하기 버튼  ------------- //////////	
		
		JButton insertbtn = new JButton("추가하기");
		insertbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String r_id = tid.getText();
					String r_name = tname.getText();
					String location = (String) combolocation.getSelectedItem();
					int people = Integer.parseInt(tpeople.getText());
					int r_ltall;
					if(tltall.getText().equals("")) {
						r_ltall = 0;						
					}else {
						r_ltall = Integer.parseInt(tltall.getText());
					}
					int r_htall;
					if(thtall.getText().equals("")) {
						r_htall = 0;
					}else {						
						r_htall = Integer.parseInt(thtall.getText());
					}
					int ltime;
					if(tltime.getText().equals("")) {
						ltime = 0;
					}else {						
						ltime = Integer.parseInt(tltime.getText());
					}
					String r_content = tcontent.getText();
					String availability = (String) comboability.getSelectedItem();
					String img = timg.getText();
					
					dto.setR_id(r_id);
					dto.setR_name(r_name);
					dto.setLocation(location);
					dto.setPeople(people);
					dto.setR_ltall(r_ltall);
					dto.setR_htall(r_htall);
					dto.setLtime(ltime);
					dto.setR_content(r_content);
					dto.setAvailability(availability);
					dto.setImg(img);
					
					dao.insert(dto);
					
					JOptionPane.showMessageDialog(f, "추가되었습니다.", "추가확인", JOptionPane.INFORMATION_MESSAGE);
					
					
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
				
				String location = (String) combolocation.getSelectedItem();
				try {
					f.dispose();
					RideInfo name = new RideInfo(f.getLocation().x, f.getLocation().y, m_id, location);
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