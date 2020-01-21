package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.PtableDTO;
import dto.SmartRDTO;

public class SmartRDAO {
	
	// 예약 정보 변경 조회
	public SmartRDTO getInfor(SmartRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select sr_id, p_name, rdate, price, m_id, m_name from smartr where sr_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getSr_id());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			
			dto.setSr_id(rs.getString(1));
			dto.setP_name(rs.getString(2));
			dto.setRdate(rs.getString(3));
			dto.setPrice(rs.getInt(4));
			dto.setM_id(rs.getString(5));
			dto.setM_name(rs.getString(6));

		}

		rs.close();
		ps.close();
		con.close();

		return dto;
	}

	// 예약 정보 조회(관리자)
	public ArrayList<SmartRDTO> listAdmin(SmartRDTO dto) throws Exception {

		ArrayList<SmartRDTO> list = new ArrayList();

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "select sr_id, p_name, rdate, price, m_id, m_name from smartr where sr_id like CONCAT('%',?,'%')";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getSr_id());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			dto = new SmartRDTO();

			dto.setSr_id(rs.getString(1));
			dto.setP_name(rs.getString(2));
			dto.setRdate(rs.getString(3));
			dto.setPrice(rs.getInt(4));
			dto.setM_id(rs.getString(5));
			dto.setM_name(rs.getString(6));

			list.add(dto);
		}

		rs.close();
		ps.close();
		con.close();

		return list;
	}
	
	// 예약 정보 조회(본인 예약번호)
	public ArrayList<SmartRDTO> list(SmartRDTO dto) throws Exception {

		ArrayList<SmartRDTO> list = new ArrayList();

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "select sr_id, p_name, rdate, price, m_id, m_name from smartr where sr_id like concat('%',?,'%') and m_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getSr_id());
		ps.setString(2, dto.getM_id());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			dto = new SmartRDTO();

			dto.setSr_id(rs.getString(1));
			dto.setP_name(rs.getString(2));
			dto.setRdate(rs.getString(3));
			dto.setPrice(rs.getInt(4));
			dto.setM_id(rs.getString(5));
			dto.setM_name(rs.getString(6));

			list.add(dto);
		}

		rs.close();
		ps.close();
		con.close();

		return list;
	}

	// 예약번호 생성 알고리즘
	public SmartRDTO firstRow(SmartRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "select sr_id from smartr order by kdate desc limit 1";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			dto.setSr_id(rs.getString(1));
		}

		rs.close();
		ps.close();
		con.close();

		return dto;
	}

	public void insert(SmartRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "insert into smartr values (?,?,?,?,?,?,?,now())";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getSr_id());
		ps.setString(2, dto.getP_id());
		ps.setString(3, dto.getP_name());
		ps.setString(4, dto.getRdate());
		ps.setInt(5, dto.getPrice());
		ps.setString(6, dto.getM_id());
		ps.setString(7, dto.getM_name());

		System.out.println("3. SQL문 결정 성공...");

		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");

		ps.close();
		con.close();

	}

	public void delete(SmartRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "delete from smartr where sr_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getSr_id());
		System.out.println("3. SQL문 결정 성공...");

		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");

		ps.close();
		con.close();

	}

	public void update(SmartRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "update smartr set p_id = ?, p_name = ?, rdate = ?, price = ? where sr_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getP_id());
		ps.setString(2, dto.getP_name());
		ps.setString(3, dto.getRdate());
		ps.setInt(4, dto.getPrice());
		ps.setString(5, dto.getSr_id());
		System.out.println("3. SQL문 결정 성공...");

		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");

		ps.close();
		con.close();

	}

}
