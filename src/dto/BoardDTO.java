package dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	int b_id;
	String m_id;
	String title;
	String content;
	Timestamp uDate;
	int hits;
	
	//기본생성자
	public BoardDTO() {
	}
	
	//파라메터가 있을 때 생성자
	public BoardDTO(String m_id, String title, String content, Timestamp uDate, int hits) {
		//알트 + 쉬프트 + s + Constructor Using Fields
		super();
		this.m_id = m_id;
		this.title = title;
		this.content = content;
		this.uDate = uDate;
		this.hits = hits;
	}
	
	//getters/setters, 알트+쉬프트+s+r
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getuDate() {
		return uDate;
	}
	public void setuDate(Timestamp uDate) {
		this.uDate = uDate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
//	public void setId(String id) {
//		this.id = id; //전역변수와 지역변수 명이 동일할 때 전역변수를 사용하기 위해 this를 사용
//	}
//	public String getId() {
//		return id;
//	}
}
