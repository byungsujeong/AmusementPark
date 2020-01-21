package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.TtableDTO;

public class TtableDAO {

// insert문	

	public void insert(TtableDTO dto) throws Exception {

		// 1. connector 설정
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "insert into ttable values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getT_id());
		ps.setString(2, dto.getR_id());
		ps.setString(3, dto.getR_name());
		ps.setString(4, dto.getStime());
		ps.setString(5, dto.getFtime());
		ps.setString(6, dto.getBtime());

		// 4. SQL문 전송 요청
		ps.executeUpdate();

		ps.close();
		con.close();

	}

// select문
	public ArrayList<TtableDTO> selectAll() throws Exception {
		ArrayList<TtableDTO> list = new ArrayList();

		// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결 (url, user, password)
		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "select * from ttable";
		PreparedStatement ps = con.prepareStatement(sql);

		// 4. SQL문 실행 요청

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TtableDTO dto = new TtableDTO();
			String t_id = rs.getString(1);
			String r_id = rs.getString(2);
			String r_name = rs.getString(3);
			String stime = rs.getString(4);
			String ftime = rs.getString(5);
			String btime = rs.getString(6);

			dto.setT_id(t_id);
			dto.setR_id(r_id);
			dto.setR_name(r_name);
			dto.setStime(stime);
			dto.setFtime(ftime);
			dto.setBtime(btime);

			list.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return list;

	}

// 놀이기구 이름 select문(콤보박스)
	public ArrayList<TtableDTO> selectname() throws Exception {
		ArrayList<TtableDTO> namelist = new ArrayList();

		// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결 (url, user, password)
		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "select distinct r_name from ttable";
		PreparedStatement ps = con.prepareStatement(sql);

		// 4. SQL문 실행 요청

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TtableDTO dto = new TtableDTO();
			String r_name = rs.getString(1);

			dto.setR_name(r_name);

			namelist.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return namelist;

	}

// 놀이기구 이름별 시간표 select문
	public ArrayList<TtableDTO> selecttable1(TtableDTO dto) throws Exception {
		ArrayList<TtableDTO> list = new ArrayList();

		// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결 (url, user, password)
		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "select t_id, stime, ftime, btime from ttable where r_name = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getR_name());

		// 4. SQL문 실행 요청

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			dto = new TtableDTO();

			dto.setT_id(rs.getString(1));
			dto.setStime(rs.getString(2));
			dto.setFtime(rs.getString(3));
			dto.setBtime(rs.getString(4));

			list.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return list;

	}

// update문

	public void update(TtableDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "update ttable set stime =?, ftime=?, btime = ? where t_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getStime());
		ps.setString(2, dto.getFtime());
		ps.setString(3, dto.getBtime());
		ps.setString(4, dto.getT_id());

		ps.executeUpdate();

		ps.close();
		con.close();

	}

// delete문	

	public void delete(TtableDTO dto) throws Exception {
		// 1. connector 설정
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "delete from ttable where t_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getT_id());

		// 4. SQL문 전송 요청
		ps.executeUpdate();

		ps.close();
		con.close();

	}

	// sql 수정
	public TtableDTO selecttable(TtableDTO dto) throws Exception {

		// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결 (url, user, password)
		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "select * from ttable where t_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getT_id());

		// 4. SQL문 실행 요청
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			String t_id = rs.getString(1);
			String r_id = rs.getString(2);
			String r_name = rs.getString(3);
			String stime = rs.getString(4);
			String ftime = rs.getString(5);
			String btime = rs.getString(6);

			dto.setT_id(t_id);
			dto.setR_id(r_id);
			dto.setR_name(r_name);
			dto.setStime(stime);
			dto.setFtime(ftime);
			dto.setBtime(btime);

		}

		rs.close();
		ps.close();
		con.close();

		return dto;
	}

}
