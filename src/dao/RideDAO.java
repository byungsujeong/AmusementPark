package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.RideDTO;
import dto.RideRDTO;

public class RideDAO {

	// 놀이기구 예약 정보 호출
	public RideDTO getInfor(RideDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ride where r_name = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getR_name());

		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {

			String r_id = rs.getString(1);
			String r_name = rs.getString(2);
			String location = rs.getString(3);
			int people = rs.getInt(4);
			int r_ltall = rs.getInt(5);
			int r_htall = rs.getInt(6);
			int ltime = rs.getInt(7);
			String r_content = rs.getString(8);
			String availability = rs.getString(9);
			String img = rs.getString(10);

			dto.setR_id(r_id);
			dto.setR_name(r_name);
			dto.setLocation(location);
			dto.setPeople(people);
			dto.setR_ltall(r_ltall);
			if(r_htall==0)	{
				dto.setR_htall(999);
			}else {
				dto.setR_htall(r_htall);
			}
			dto.setLtime(ltime);
			dto.setR_content(r_content);
			dto.setAvailability(availability);
			dto.setImg(img);

		}

		rs.close();
		ps.close();
		con.close();

		return dto;

	}

// insert문	

	public void insert(RideDTO dto) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "insert into ride values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getR_id());
		ps.setString(2, dto.getR_name());
		ps.setString(3, dto.getLocation());
		ps.setInt(4, dto.getPeople());
		ps.setInt(5, dto.getR_ltall());
		ps.setInt(6, dto.getR_htall());
		ps.setInt(7, dto.getLtime());
		ps.setString(8, dto.getR_content());
		ps.setString(9, dto.getAvailability());
		ps.setString(10, dto.getImg());

		ps.executeUpdate();

		ps.close();
		con.close();

	}

// select All문

	public ArrayList<RideDTO> selectAll() throws Exception {
		ArrayList<RideDTO> list = new ArrayList();

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ride";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			RideDTO dto = new RideDTO();
			String r_id = rs.getString(1);
			String r_name = rs.getString(2);
			String location = rs.getString(3);
			int people = rs.getInt(4);
			int r_ltall = rs.getInt(5);
			int r_htall = rs.getInt(6);
			int ltime = rs.getInt(7);
			String r_content = rs.getString(8);
			String availability = rs.getString(9);
			String img = rs.getString(10);

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

			list.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return list;

	}

// 예약가능한 놀이기구 이름 select문(combobox)

	public ArrayList<RideDTO> selectCombo() throws Exception {
		ArrayList<RideDTO> list = new ArrayList();

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ride where availability='가능'";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			RideDTO dto = new RideDTO();
			String r_id = rs.getString(1);
			String r_name = rs.getString(2);
			String location = rs.getString(3);
			int people = rs.getInt(4);
			int r_ltall = rs.getInt(5);
			int r_htall = rs.getInt(6);
			int ltime = rs.getInt(7);
			String r_content = rs.getString(8);
			String availability = rs.getString(9);
			String img = rs.getString(10);

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

			list.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return list;

	}

// 놀이기구 위치별 놀이기구 select문()
///////////////
	public ArrayList<RideDTO> selectride(RideDTO dto) throws Exception {
		ArrayList<RideDTO> list = new ArrayList();

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ride where location = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getLocation());

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			dto = new RideDTO();

			dto.setR_id(rs.getString(1));
			dto.setR_name(rs.getString(2));
			dto.setLocation(rs.getString(3));
			dto.setPeople(rs.getInt(4));
			dto.setR_ltall(rs.getInt(5));
			dto.setR_htall(rs.getInt(6));
			dto.setLtime(rs.getInt(7));
			dto.setR_content(rs.getString(8));
			dto.setAvailability(rs.getString(9));
			dto.setImg(rs.getString(10));

			list.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return list;

	}

// 놀이기구 정보 select문()

	public ArrayList<RideDTO> selectcontent(RideDTO dto) throws Exception {
		ArrayList<RideDTO> list = new ArrayList();

		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);

		String sql = "select * from ride where r_name = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getR_name());

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			dto = new RideDTO();

			dto.setR_id(rs.getString(1));
			dto.setR_name(rs.getString(2));
			dto.setLocation(rs.getString(3));
			dto.setPeople(rs.getInt(4));
			dto.setR_ltall(rs.getInt(5));
			dto.setR_htall(rs.getInt(6));
			dto.setLtime(rs.getInt(7));
			dto.setR_content(rs.getString(8));
			dto.setAvailability(rs.getString(9));
			dto.setImg(rs.getString(10));

			list.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return list;

	}

//놀이기구 위치 select문
	public ArrayList<RideDTO> selectlocation() throws Exception {
		ArrayList<RideDTO> locationlist = new ArrayList();

		// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결 (url, user, password)
		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "select distinct location from ride";
		PreparedStatement ps = con.prepareStatement(sql);

		// 4. SQL문 실행 요청

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			RideDTO dto = new RideDTO();
			String location = rs.getString(1);

			dto.setLocation(location);

			locationlist.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return locationlist;

	}

//놀이기구 이름 select문
	public ArrayList<RideDTO> selectname() throws Exception {
		ArrayList<RideDTO> namelist = new ArrayList();

		// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");

		// 2. DB연결 (url, user, password)
		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "select distinct r_name from ride";
		PreparedStatement ps = con.prepareStatement(sql);

		// 4. SQL문 실행 요청

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			RideDTO dto = new RideDTO();
			String rname = rs.getString(1);

			dto.setR_name(rname);

			namelist.add(dto);

		}

		rs.close();
		ps.close();
		con.close();

		return namelist;

	}

// update문

	public void update(RideDTO dto) throws Exception {
		// 1. connector 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "update ride set people = ?, r_ltall = ?, r_htall = ?, ltime = ?, r_content = ?,";
		String sql2 = "availability = ?, img = ? where r_id = ?";
		PreparedStatement ps = con.prepareStatement(sql+sql2);
		ps.setInt(1, dto.getPeople());
		ps.setInt(2, dto.getR_ltall());
		ps.setInt(3, dto.getR_htall());
		ps.setInt(4, dto.getLtime());
		ps.setString(5, dto.getR_content());
		ps.setString(6, dto.getAvailability());
		ps.setString(7, dto.getImg());		
		ps.setString(8, dto.getR_id());

		// 4. SQL문 전송 요청
		ps.executeUpdate();
		
		ps.close();
		con.close();

	}

// delete문	

	public void delete(RideDTO dto) throws Exception {
		// 1. connector 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1";
		String user = "root";
		String password = "1234";

		// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password);

		// 3. SQL문 결정
		String sql = "delete from ride where r_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getR_id());

		// 4. SQL문 전송 요청
		ps.executeUpdate();
		
	}

}
