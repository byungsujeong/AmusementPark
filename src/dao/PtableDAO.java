package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.MembersDTO;
import dto.PtableDTO;

public class PtableDAO {
	
	// 예약정보 변경할 때 호출 위한 메서드
	public PtableDTO getInforChange(PtableDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ptable where p_name = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getP_name());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
					
			dto.setP_id(rs.getString(1));
			dto.setP_name(rs.getString(2));
			dto.setPrice(rs.getInt(3));
			
		}
		
		rs.close();
		ps.close();
		con.close();
		
		return dto;
		
	}
	
	// 예약할 때 정보 호출 위한 메서드
	public PtableDTO getInfor(PtableDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ptable where p_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getP_id());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
					
			dto.setP_id(rs.getString(1));
			dto.setP_name(rs.getString(2));
			dto.setPrice(rs.getInt(3));
			
		}
		
		rs.close();
		ps.close();
		con.close();
		
		return dto;
		
	}

	// 상품 리스트 호출하기 위한 메서드
	public ArrayList<PtableDTO> list(PtableDTO dto) throws Exception {
		
		ArrayList<PtableDTO> list = new ArrayList<PtableDTO>();
		
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		String sql = "select * from ptable";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			dto = new PtableDTO();
			
			dto.setP_id(rs.getString(1));
			dto.setP_name(rs.getString(2));
			dto.setPrice(rs.getInt(3));
			
			list.add(dto);	
		}	
		
		rs.close();
		ps.close();
		con.close();
		
		return list;
		
	}
	
	public void insert(PtableDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");
		
		String sql = "insert into ptable values (?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getP_id());
		ps.setString(2, dto.getP_name());
		ps.setInt(3, dto.getPrice());

		ps.executeUpdate();

		ps.close();
		con.close();

	}
	
	public void delete(PtableDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "delete from ptable where p_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getP_id());

		ps.executeUpdate();

		ps.close();
		con.close();
		
	}
	
	public void update(PtableDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "update ptable set p_id = ?, p_name = ?, price = ? where p_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getP_id());
		ps.setString(2, dto.getP_name());
		ps.setInt(3, dto.getPrice());
		ps.setString(4, dto.getP_id());

		ps.executeUpdate();

		ps.close();
		con.close();
		
	}
	
}
