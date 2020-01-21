package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.BoardDTO;


public class BoardDAO {

	
//update(BoardConfirm의 '글수정'에서 사용)
	public void update(BoardDTO dto) throws Exception {
		
	// 1. connector(driver) 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		// 디비버에서 정보를 보고 밑에 세개 입력 //접근권한이 있어야 연결되게 하려고
		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8"; //?characterEncoding=utf8"; 한글설정
		String user = "root";
		String password = "1234";

	// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password); // 오버로딩 중 3개짜리 선택
		System.out.println("2. db연결 성공...");

	// 3. SQL문 결정
		String sql = "update board set title = ?, content = ? where b_id = ?"; // 물음표에 따옴표x
		// String이라 ""가 필요하고 그냥 "변수"이면 변수명이 그대로 입력되므로 변수명 양쪽에 " + 변수명 + " 을 해줌
		// java개발자들은 sql사용시 "이 아닌 '를 사용함. 스트링 사용시 잘못 인식할 수 있으므로
		// sql문 안에는 ;을 쓰지 않음.

		PreparedStatement ps = con.prepareStatement(sql);
		//(물음표의 위치순서, set 조건에 필요한 title값을 dto로 가져옴)
		ps.setString(1, dto.getTitle());
		//(물음표의 위치순서, set 조건에 필요한 content값을 dto로 가져옴)
		ps.setString(2, dto.getContent()); 
		//(물음표의 위치순서, where 조건에 필요한 b_id값을 dto로 가져옴)
		ps.setInt(3, dto.getB_id());

		// 메소드 prepareStatement을 사용해서 SQL문을 변수 ps로 가져옴
		System.out.println("3. SQL문 결정 성공...");

	// 4. SQL문 전송 요청
		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");
		
		ps.close();
		con.close();
	}
	
	
//update(hits, '조회수'에 사용)
	//변수 2개만 있어도 되니까 매개변수에 b_id, hits 입력	
	public void updateH(int b_id, int hits) throws Exception {
			
	// 1. connector(driver) 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 성공...");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

	// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password); // 오버로딩 중 3개짜리 선택
		System.out.println("2. db연결 성공...");

	// 3. SQL문 결정
		String sql = "update board set hits = ? where b_id = ?"; // 물음표에 따옴표x
	
		PreparedStatement ps = con.prepareStatement(sql);
		//(물음표의 위치순서, set 조건에 필요한 b_id 가져옴)
		ps.setInt(1, hits);
		//(물음표의 위치순서, where 조건에 필요한 b_id 가져옴)
		ps.setInt(2, b_id); 
			
		// 메소드 prepareStatement을 사용해서 SQL문을 변수 ps로 가져옴
		System.out.println("3. SQL문 결정 성공...");

	// 4. SQL문 전송 요청
		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");
			
		ps.close();
		con.close();
		}	
	

//delete(BoardConfrim에 '글삭제'에서 사용)
	public void delete(BoardDTO dto) throws Exception {
		
	// 1. connector(driver) 설정
		Class.forName("com.mysql.jdbc.Driver");

		System.out.println("1. connector설정 설공...");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

	// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password); // 오버로딩 중 3개짜리 선택
		System.out.println("2. db연결 성공...");

	// 3. SQL문 결정
		String sql = "delete from board where b_id = ?"; // 물음표에 따옴표x
		PreparedStatement ps = con.prepareStatement(sql);
		//(물음표의 위치순서, where 조건에 필요한 b_id값을 dto로 가져옴)
		ps.setInt(1, dto.getB_id()); 

		// 메소드 prepareStatement을 사용해서 SQL문을 변수 ps로 가져옴
		System.out.println("3. SQL문 결정 성공...");

	// 4. SQL문 전송 요청
		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");

		ps.close();
		con.close();	
	}

	
//insert(BoardWrite에 '글작성'에서 사용)
	public void insert(BoardDTO dto) throws Exception {

	// 1. connector(driver) 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. connector설정 설공...");

		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

	// 2. DB연결
		Connection con = DriverManager.getConnection(url, user, password); // 오버로딩 중 3개짜리 선택
		System.out.println("2. db연결 성공...");
	
	// 3. SQL문 결정		
		String sql = "insert into board (m_id, title, content, uDate, hits) values (?,?,?,now(),0)"; // 물음표에 따옴표x

		PreparedStatement ps = con.prepareStatement(sql); //sql문을 변수 ps로 불러오기
		//(물음표의 위치순서, m_id값을 dto로 가져옴), members테이블에서 받아와야?
		ps.setString(1, dto.getM_id());
		//(물음표의 위치순서, 제목에 넣어줄 title값을 dto로 가져옴)
		ps.setString(2, dto.getTitle());
		//(물음표의 위치순서, 내용에 넣어줄 content값을 dto로 가져옴)
		ps.setString(3, dto.getContent());

		System.out.println("3. SQL문 결정 성공...");

	// 4. SQL문 전송 요청
		ps.executeUpdate();
		System.out.println("4. SQL문 전송 요청 성공...");

		ps.close();
		con.close();

	}

	
// select문(BoardMain에 '검색'에서 사용)
	public ArrayList<BoardDTO> selectS(BoardDTO dto) throws Exception { // 예외처리
		ArrayList<BoardDTO> list = new ArrayList();
		
	// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. 드라이버 설정 ok...");

	// 2. DB연결
		// url, user, password
		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 ok...");

		// 3. SQL문 결정
		String sql = "select b_id, title, m_id, uDate, hits from board where m_id like concat('%',?,'%')";
		//유사한 아이디 검색. '= ?' , 대신 'like concat' 사용
		PreparedStatement ps = con.prepareStatement(sql);
		//(물음표의 위치순서, where 조건에 필요한 m_id값을 dto로 가져옴 )
		ps.setString(1, dto.getM_id());
		System.out.println("3. SQL문 객체화 ok...");

	// 4. SQL문 실행 요청
		//ps.executeUpdate(); //c, u, d 에만 사용!
		ResultSet rs = ps.executeQuery();

		//하나의 dto에 검색된 항목의 값들을 dto의 각 변수에 넣어주고 list에 add! 이것을 반복
		while (rs.next()) {
			dto = new BoardDTO();
			int b_id = rs.getInt(1);
			String title = rs.getString(2);
			String m_id = rs.getString(3);
			Timestamp uDate = rs.getTimestamp(4);
			int hits = rs.getInt(5);
			
			//rs에서 get으로 가져온 값들을 dto의 b_id에 값을 넣어줌
			dto.setB_id(b_id);
			//rs에서 get으로 가져온 값들을 dto의 title에 값을 넣어줌
			dto.setTitle(title);
			//rs에서 get으로 가져온 값들을 dto의 m_id에 값을 넣어줌
			dto.setM_id(m_id);
			//rs에서 get으로 가져온 값들을 dto의 udate에 값을 넣어줌
			dto.setuDate(uDate);
			//rs에서 get으로 가져온 값들을 dto의 hits에 값을 넣어줌
			dto.setHits(hits);
			
			//검색되어 가져와 dto의 각 변수에 넣어준 항목값들이 dto에 있고 그 dto를 list에 추가
			list.add(dto);
		}
		System.out.println("4. SQL문 실행 요청 OK...");
		
		rs.close();
		ps.close();
		con.close();
		
		return list; // list로 묶어서 반환
		}
	
	
//select문 (JTable위에 얹은 model 위에  '전체리스트'를 뿌려주기 위해 생성)
	public ArrayList<BoardDTO> selectAll() throws Exception { // 예외처리
		ArrayList<BoardDTO> list = new ArrayList();
	// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. 드라이버 설정 ok...");

	// 2. DB연결
		// url, user, password
		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 ok...");

	// 3. SQL문 결정
		String sql = "select b_id, title, m_id, uDate, hits from board";
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("3. SQL문 객체화 ok...");

	// 4. SQL문 실행 요청
		ResultSet rs = ps.executeQuery();

		//하나의 dto에 검색된 항목의 값들을 dto의 각 변수에 넣어주고 list에 add! 이것을 반복
		while (rs.next()) {
			BoardDTO dto = new BoardDTO();
			
			//(항목순서),순서대로 입력됨
			//DB에서 검색된 해당 항목의 값들을 변수들에 할당 
			int b_id = rs.getInt(1);
			String title = rs.getString(2);
			String m_id = rs.getString(3);
			Timestamp uDate = rs.getTimestamp(4);
			int hits = rs.getInt(5);

			//rs에서 get으로 가져온 값들을 dto의 b_id에 값을 넣어줌
			dto.setB_id(b_id);
			//rs에서 get으로 가져온 값들을 dto의 title에 값을 넣어줌
			dto.setTitle(title);
			//rs에서 get으로 가져온 값들을 dto의 m_id에 값을 넣어줌
			dto.setM_id(m_id);
			//rs에서 get으로 가져온 값들을 dto의 uDate에 값을 넣어줌
			dto.setuDate(uDate);
			//rs에서 get으로 가져온 값들을 dto의 hits에 값을 넣어줌
			dto.setHits(hits);
			
			//검색되어 가져와 dto의 각 변수에 넣어준 항목값들이 dto에 있고 그 dto를 list에 추가
			list.add(dto);
		}

		System.out.println("4. SQL문 실행 요청 OK...");

		rs.close();
		ps.close();
		con.close();
		
		return list; // list로 묶어서 반환

	}
	
	
// select문(BoeadConfirm창, ContentView), '글확인'에 제목,내용을 뿌려주기 위해 생성
	public BoardDTO selectC(BoardDTO dto) throws Exception { // 예외처리
		
	// 1. 드라이버 설정
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("1. 드라이버 설정 ok...");

	// 2. DB연결
		// url, user, password
		String url = "jdbc:mysql://localhost:3306/projectno1?characterEncoding=utf8";
		String user = "root";
		String password = "1234";

		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. DB연결 ok...");

	// 3. SQL문 결정
		String sql = "select title, content, m_id from board where b_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		//(물음표의 위치순서, where 조건에 필요한 b_id값을 dto로 가져옴 )
		ps.setInt(1, dto.getB_id());
		System.out.println("3. SQL문 객체화 ok...");

	// 4. SQL문 실행 요청
		//ps.executeUpdate(); //c, u, d 에만 사용!
		//DB에서 DAO로 보내기 위해
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			
			//(항목순서),순서대로 입력됨
			//DB에서 검색된 해당 항목의 값들을 변수들에 할당
			String title = rs.getString(1);
			String content = rs.getString(2);
			String m_id = rs.getString(3);

			//rs에서 get으로 가져온 값들을 dto의 title에 값을 넣어줌
			dto.setTitle(title);
			//rs에서 get으로 가져온 값들을 dto의 content에 값을 넣어줌
			dto.setContent(content);
			//rs에서 get으로 가져온 값들을 dto의 m_id에 값을 넣어줌
			//글확인창에서 보이진 않으나 글수정시 m_id값을 비교하여 삭제하기 위한 기준이 필요하므로 
			//dto에 할당해놓아 출력할 클래스(BoardConfirm)에서 사용
			dto.setM_id(m_id);
			
			//302~311을 간단하게 314~316으로 가능
			//dto.setTitle(rs.getString(1));
			//dto.setContent(rs.getString(2));
			//dto.setM_id(rs.getString(3));
		}

		rs.close();
		ps.close();
		con.close();
			
		return dto; //dto로 묶어서 반환
			
	}
	
		
}
