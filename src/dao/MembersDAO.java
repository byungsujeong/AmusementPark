package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.MembersDTO;

public class MembersDAO {
	String url = "jdbc:mysql://localhost:3306/projectno1";
	String user = "root";
	String password = "1234";

	// TODO Auto-generated method stub
	public void insert(MembersDTO dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "insert into Members values (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getM_id());
		ps.setString(2, dto.getPw());
		ps.setString(3, dto.getM_name());
		ps.setString(4, dto.getGender());
		ps.setString(5, dto.getBirth());
		ps.setInt(6, dto.getTall());
		ps.setString(7, dto.getTel());
		ps.setString(8, dto.getEmail());
		
	

		ps.executeUpdate();
		// 여기까지가 insert 과정

		ps.close();
		con.close();

	}

	///// Check LogIn!!!

	public boolean sele(MembersDTO dto) throws Exception {

		Connection con = DriverManager.getConnection(url, user, password);
		String sql = "select m_id from members where m_id=? and pw=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getM_id());
		ps.setString(2, dto.getPw());

		ResultSet rs = ps.executeQuery();
		boolean result = false;
		if (rs.next()) {
			result = true;
		} else {
		}
		return result;
//select2
	}
	public MembersDTO sele2(MembersDTO dto) throws Exception {
		
		Connection con = DriverManager.getConnection(url, user, password);
		String sql = "select * from members where m_id=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getM_id());
		MembersDTO dto2 = new MembersDTO();
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			dto.setM_id((rs.getString(1)));
			dto.setPw((rs.getString(2)));
			dto.setM_name((rs.getString(3)));
			dto.setGender((rs.getString(4)));
			dto.setBirth((rs.getString(5)));
			dto.setTall((rs.getInt(6)));
			dto.setTel((rs.getString(7)));
			dto.setEmail((rs.getString(8)));
			 ///
			////
		} else {
		}
		return dto;
//delete
	}

	public void dele(MembersDTO dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			String sql = "delete from members where  m_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getM_id());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// udpate
	public void update(MembersDTO dto) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=null;
		PreparedStatement ps =null;
	String sql = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			sql = "update members set pw=?, m_name=?, gender=?,birth=?,tall=?, tel=?, email=? where m_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getM_name());
			ps.setString(3, dto.getGender());
			ps.setString(4, dto.getBirth());
			ps.setInt(5, dto.getTall());
			ps.setString(6, dto.getTel());
			ps.setString(7, dto.getEmail());
			ps.setString(8, dto.getM_id());
			
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		
	}

}
