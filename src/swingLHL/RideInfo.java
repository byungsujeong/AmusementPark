// 위치별 놀이기구 종류

package swingLHL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dao.RideDAO;
import dto.MembersDTO;
import dto.RideDTO;
import swingMHW.Join;
import swingMHW.LogIn;
import swingMHW.Start;
import swingMHW.Update;

public class RideInfo {

	public RideInfo(int x, int y, String m_id, String location) throws Exception {

////////// ------------- 프레임 생성  ------------- //////////
		JFrame f = new JFrame();
		f.setSize(1200, 600);
		f.setTitle("놀이기구 정보");
		f.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBounds(12, 59, 1160, 492);
		f.getContentPane().add(panel);

////////// - 회원정보 패널(로그아웃+회원정보수정+로그인정보) - ////////
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

//////////---------- 놀이기구 위치 콤보박스 ---------- //////////
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 10, 1136, 43);
		panel.add(panel_1);
		panel_1.setBackground(Color.LIGHT_GRAY);
		
		//놀이기구 위치 레이블 생성
		JLabel lblNewLabel = new JLabel(" 놀이기구 위치 : ");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(165, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
	
		// 놀이기구 위치 콤보박스 생성
		JComboBox comboBox = new JComboBox();
		
		RideDAO rideDAO = new RideDAO();
		RideDTO rideDTO = new RideDTO();
		ArrayList<RideDTO> locationlist = rideDAO.selectlocation();
		
		Object[] data1 = new Object[locationlist.size()];
		for (int i = 0; i < data1.length; i++) {
			rideDTO = locationlist.get(i);
			data1[i] = rideDTO.getLocation();
			comboBox.addItem(data1[i]);
		}
		
		comboBox.setLocation(131, 10);
		comboBox.setSize(295, 22);
		comboBox.setFont(new Font("굴림", Font.PLAIN, 16));
		panel_1.add(comboBox);
		
				
				
////////////---------- 놀이기구 목록 정보 ---------- ////////// 
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 63, 1136, 419);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 1112, 360);
		panel_3.add(scrollPane);
				
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 40, 20));
		
////////////---------- 놀이기구 목록 첫 화면 ---------- ////////// 
		comboBox.setSelectedItem(location);
						
		String index = (String) comboBox.getSelectedItem();
		rideDTO.setLocation(index);
		
		ArrayList<RideDTO> list;
		
		try {

			list = rideDAO.selectride(rideDTO);

			JButton[] buttons = new JButton[list.size()];
			ImageIcon[] icon = new ImageIcon[list.size()];

			for (int i = 0; i < buttons.length; i++) {
				// 패널 재배치(?)
				panel_4.revalidate();

				rideDTO = list.get(i);
				icon[i] = new ImageIcon(rideDTO.getImg());

				// ImageIcon에서 Image를 추출
				Image originimg = icon[i].getImage();
				// 추출된 Image의 크기를 조절하여 새로운 Image 객체 생성
				Image changedImg = originimg.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
				// 새로운 Image로 ImageIcon 객체를 생성
				ImageIcon Icon1 = new ImageIcon(changedImg);

				// btn_icon 과 텍스트를 가지는 JButton 객체를 생성한다
				buttons[i] = new JButton(rideDTO.getR_name() + "", Icon1);
				// 버튼크기설정
				buttons[i].setPreferredSize(new Dimension(150, 250));
				// 텍스트가 이미지의 아래쪽에 위치하도록 텍스트의 수직 위치를 설정한다
				buttons[i].setVerticalTextPosition(AbstractButton.BOTTOM);
				// 텍스트가 이미지의 중앙에 위치하도록 텍스트의 수평 위치를 설정
				buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);

				panel_4.add(buttons[i]);

				// 버튼 클릭시 해당 놀이기구의 RideInfo2 로 이동
				buttons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						try {
						RideInfo2 rideinfo2 = new RideInfo2(f.getLocation().x, f.getLocation().y, m_id, arg0.getActionCommand());
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						f.dispose();
					}

				});
			}
					
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
////////////---------- 콤보박스 값 변경시 액션(놀이기구 목록 변경) ---------- //////////
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				// 패널 리셋
				panel_4.removeAll();

				RideDTO rideDTO = new RideDTO();
				RideDAO rideDAO = new RideDAO();

				String index = (String) comboBox.getSelectedItem();
				rideDTO.setLocation(index);

				ArrayList<RideDTO> list;

				// 버튼생성 (이미지 + 놀이기구 이름)
				try {

					list = rideDAO.selectride(rideDTO);

					JButton[] buttons = new JButton[list.size()];
					ImageIcon[] icon = new ImageIcon[list.size()];

					for (int i = 0; i < buttons.length; i++) {
						// 패널 재배치(?)
						panel_4.revalidate();

						rideDTO = list.get(i);
						icon[i] = new ImageIcon(rideDTO.getImg());

						// ImageIcon에서 Image를 추출
						Image originimg = icon[i].getImage();
						// 추출된 Image의 크기를 조절하여 새로운 Image 객체 생성
						Image changedImg = originimg.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
						// 새로운 Image로 ImageIcon 객체를 생성
						ImageIcon Icon1 = new ImageIcon(changedImg);

						// btn_icon 과 텍스트를 가지는 JButton 객체를 생성한다
						buttons[i] = new JButton(rideDTO.getR_name() + "", Icon1);
						// 버튼크기설정
						buttons[i].setPreferredSize(new Dimension(150, 250));
						// 텍스트가 이미지의 아래쪽에 위치하도록 텍스트의 수직 위치를 설정한다
						buttons[i].setVerticalTextPosition(AbstractButton.BOTTOM);
						// 텍스트가 이미지의 중앙에 위치하도록 텍스트의 수평 위치를 설정
						buttons[i].setHorizontalTextPosition(AbstractButton.CENTER);

						panel_4.add(buttons[i]);

						// 버튼 클릭시 해당 놀이기구의 RideInfo2 로 이동
						buttons[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							
								try {
								RideInfo2 rideinfo2 = new RideInfo2(f.getLocation().x, f.getLocation().y, m_id, arg0.getActionCommand());
								
								} catch (Exception e) {
									e.printStackTrace();
								}
								f.dispose();
							}

						});
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		// 뒤로가기 버튼 (RideInfo로 이동)
				JButton bbtn = new JButton("뒤로가기");
				bbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Start name = new Start(f.getLocation().x, f.getLocation().y, m_id);
						f.dispose();
					}
				});
			
				bbtn.setFont(new Font("굴림", Font.BOLD, 14));
				bbtn.setBounds(1027, 373, 97, 36);
				panel_3.add(bbtn);
				
				// 추가버튼(RideInsert로 이동)
				JButton button = new JButton("추가");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RideInsert insert = new RideInsert(f.getLocation().x, f.getLocation().y, m_id);
						f.dispose();
					}
				});
				button.setFont(new Font("굴림", Font.BOLD, 14));
				button.setBounds(956, 373, 70, 36);
				if(m_id.equals("root")) {
					panel_3.add(button);	
				}	

		f.setLocation(x, y);
		f.setVisible(true);

	}
}