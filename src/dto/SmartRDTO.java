package dto;

import java.sql.Timestamp;

public class SmartRDTO {

	String sr_id;
	String p_id;
	String p_name;
	String rdate;
	int price;
	String m_id;
	String m_name;
	Timestamp kdate;
	
	public String getSr_id() {
		return sr_id;
	}
	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public Timestamp getKdate() {
		return kdate;
	}
	public void setKdate(Timestamp kdate) {
		this.kdate = kdate;
	}
	
}
