package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PtableDTO;
import dto.RideRDTO;
import dto.SmartRDTO;

public class RideRDAO {

	// 예약 정보 변경 조회
//	public RideRDTO getInfor(RideRDTO dto) throws Exception {
//
//		Class.forName("com.mysql.jdbc.Driver");
//		System.out.println("1. connector설정 성공...");
//
//		String url = "jdbc:mysql://localhost:3306/projectno1";
//		String user = "root";
//		String password = "1234";
//
//		Connection con = DriverManager.getConnection(url, user, password);
//		System.out.println("2. DB연결 성공...");
//
//		String sql = "select rr_id, r_name, rdate, t_id, btime, stime, ftime, m_id, m_name, sr_id from rider where rr_id = ?";
//		PreparedStatement ps = con.prepareStatement(sql);
//		ps.setString(1, dto.getRr_id());
//
//		ResultSet rs = ps.executeQuery();
//
//		if (rs.next()) {
//			
//			dto.setRr_id(rs.getString(1));
//			dto.setR_name(rs.getString(2));
//			dto.setRdate(rs.getString(3));
//			dto.setT_id(rs.getString(4));
//			dto.setBtime(rs.getString(5));
//			dto.setStime(rs.getString(6));
//			dto.setFtime(rs.getString(7));
//			dto.setM_id(rs.getString(8));
//			dto.setM_name(rs.getString(9));
//			dto.setSr_id(rs.getString(10));
//
//		}
//
//		rs.close();
//		ps.close();
//		con.close();
//
//		return dto;
//	}
	
	// 예약 내역(관리자모드)
		public ArrayList<RideRDTO> listAdmin(RideRDTO dto) throws Exception {

			ArrayList<RideRDTO> list = new ArrayList();

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/projectno1";
			String user = "root";
			String password = "1234";

			Connection con = DriverManager.getConnection(url, user, password);

			String sql = "select rr_id, r_name, rdate, t_id, btime, stime, ftime, m_id, m_name, sr_id from rider where rr_id like concat('%',?,'%')";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getRr_id());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dto = new RideRDTO();

				dto.setRr_id(rs.getString(1));
				dto.setR_name(rs.getString(2));
				dto.setRdate(rs.getString(3));
				dto.setT_id(rs.getString(4));
				dto.setBtime(rs.getString(5));
				dto.setStime(rs.getString(6));
				dto.setFtime(rs.getString(7));
				dto.setM_id(rs.getString(8));
				dto.setM_name(rs.getString(9));
				dto.setSr_id(rs.getString(10));

				list.add(dto);
			}

			rs.close();
			ps.close();
			con.close();

			return list;
		}

		// 예약 내역 호출(본인 예약 호출)
		public ArrayList<RideRDTO> list(RideRDTO dto) throws Exception {

			ArrayList<RideRDTO> list = new ArrayList();

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/projectno1";
			String user = "root";
			String password = "1234";

			Connection con = DriverManager.getConnection(url, user, password);

			String sql = "select rr_id, r_name, rdate, t_id, btime, stime, ftime, m_id, m_name, sr_id from rider where m_id = ? and rr_id like concat('%',?,'%')";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getM_id());
			ps.setString(2, dto.getRr_id());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dto = new RideRDTO();
				
				dto.setRr_id(rs.getString(1));
				dto.setR_name(rs.getString(2));
				dto.setRdate(rs.getString(3));
				dto.setT_id(rs.getString(4));
				dto.setBtime(rs.getString(5));
				dto.setStime(rs.getString(6));
				dto.setFtime(rs.getString(7));
				dto.setM_id(rs.getString(8));
				dto.setM_name(rs.getString(9));
				dto.setSr_id(rs.getString(10));

				list.add(dto);
			}

			rs.close();
			ps.close();
			con.close();

			return list;
		}
	
	public RideRDTO count(RideRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select count(*) from rider where rdate = ? and t_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getRdate());
		ps.setString(2, dto.getT_id());
		
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			dto.setCount(rs.getInt(1));
		}

		rs.close();
		ps.close();
		con.close();

		return dto;
	}
	
	// 예약번호 생성 알고리즘
	public RideRDTO firstRow(RideRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "select rr_id from rider order by kdate desc limit 1";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			dto.setRr_id(rs.getString(1));
		}

		rs.close();
		ps.close();
		con.close();

		return dto;
	}
	
	public void insert(RideRDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		
		String sql = "insert into rider values (?,?,?,?,?,?,?,?,?,?,?,now())";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getRr_id());
		ps.setString(2, dto.getR_name());
		ps.setString(3, dto.getSr_id());
		ps.setString(4, dto.getRdate());
		ps.setString(5, dto.getStime());
		ps.setString(6, dto.getFtime());
		ps.setString(7, dto.getBtime());
		ps.setString(8, dto.getM_id());
		ps.setString(9, dto.getM_name());
		ps.setString(10, dto.getR_id());
		ps.setString(11, dto.getT_id());

		ps.executeUpdate();

		ps.close();
		con.close();

	}
	
	public void delete(RideRDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "delete from rider where rr_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getRr_id());
		System.out.println(dto.getRr_id()+"%%%%%%%%%%");

		ps.executeUpdate();

		ps.close();
		con.close();
		
	}
	
	public void update(RideRDTO dto) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 성공...");

		String sql = "update rider set r_name = ?, sr_id = ?, rdate = ?, stime = ?, ftime = ?, btime = ?, m_id = ?, m_name = ?,";
		String sql2 = "r_id = ?, t_id = ? where rr_id = ?";
		PreparedStatement ps = con.prepareStatement(sql+sql2);
			
		ps.setString(1, dto.getR_name());
		ps.setString(2, dto.getSr_id());
		ps.setString(3, dto.getRdate());
		ps.setString(4, dto.getStime());
		ps.setString(5, dto.getFtime());
		ps.setString(6, dto.getBtime());
		ps.setString(7, dto.getM_id());
		ps.setString(8, dto.getM_name());
		ps.setString(9, dto.getR_id());
		ps.setString(10, dto.getT_id());
		ps.setString(11, dto.getRr_id());
		System.out.println("3. SQL문 결정 성공...");

		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");

		ps.close();
		con.close();
		
	}
	
}
