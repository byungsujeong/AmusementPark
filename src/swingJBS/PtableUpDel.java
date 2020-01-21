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
import dao.SmartRDAO;
import dto.MembersDTO;
import dto.PtableDTO;
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

// 상품 정보 수정/삭제 화면
public class PtableUpDel {
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;

	public PtableUpDel(int x, int y, String m_id, String p_id) throws Exception {

		// 상품 정보(ptable) 가져오기 위한 객체 생성 
		PtableDTO ptableDTO = new PtableDTO();
		PtableDAO ptableDAO = new PtableDAO();
		// 상품 키값(p_id) DTO에서 반환 받아 getInfor메소드 실행
		ptableDTO.setP_id(p_id);
		ptableDAO.getInfor(ptableDTO);

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

		JLabel lblNewLabel = new JLabel(" 추가할 상품정보를 입력하세요. ");
		lblNewLabel.setLocation(12, 10);
		lblNewLabel.setSize(251, 22);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		panel_1.add(lblNewLabel);

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
					// if (Join.check==0){
					Join join = new Join(f.getLocation().x, f.getLocation().y);
					// }
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

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 10, 847, 399);
		panel_3.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("상품ID : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(78, 50, 138, 35);
		panel_4.add(lblNewLabel_2);

		JLabel label = new JLabel("상품명 : ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(78, 140, 138, 35);
		panel_4.add(label);

		JLabel label_1 = new JLabel("가격 : ");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(78, 242, 138, 35);
		panel_4.add(label_1);

		t1 = new JTextField();
		t1.setBounds(242, 50, 203, 35);
		panel_4.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(242, 140, 203, 35);
		panel_4.add(t2);

		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(242, 242, 203, 35);
		panel_4.add(t3);

		// getInfor 메소드를 통해 가져온 정보 각 텍스트 필드에 삽입 
		t1.setText(p_id);
		t2.setText(ptableDTO.getP_name());
		t3.setText(Integer.toString(ptableDTO.getPrice()));

		// 버튼 클릭 시 delete문 실행 하여 상품 정보 삭제하는 ActionListener
		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// 삭제 후 상품리스트(PtableList 클래스) 화면으로 넘어감
					ptableDAO.delete(ptableDTO);
					f.dispose();
					PtableList name = new PtableList(f.getLocation().x, f.getLocation().y, m_id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnNewButton.setFont(new Font("굴림", Font.BOLD, 14));
		btnNewButton.setBounds(894, 327, 129, 36);
		panel_3.add(btnNewButton);

		// 버튼클릭 시 상품리시트(PtableList 클래스) 화면으로 가는 ActionListener
		JButton button = new JButton("뒤로가기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					PtableList name = new PtableList(f.getLocation().x, f.getLocation().y, m_id);
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

		// update문 실행하는 ActionListener
		JButton button_1 = new JButton("수정");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 상품 정보(p_id, p_name, price) DTO에서 반환받고 메소드 실행 후 상품 리스트(PtableList클래스) 화면으로 이동 
				ptableDTO.setP_id(t1.getText());
				ptableDTO.setP_name(t2.getText());
				ptableDTO.setPrice(Integer.parseInt(t3.getText()));
				try {
					ptableDAO.update(ptableDTO);
					PtableList name = new PtableList(f.getLocation().x, f.getLocation().y, m_id);
					f.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		button_1.setFont(new Font("굴림", Font.BOLD, 14));
		button_1.setBounds(894, 373, 129, 36);
		panel_3.add(button_1);

		f.setLocation(x, y);
		f.setVisible(true);

	}
}
