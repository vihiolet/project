package vo;

import java.sql.Date;

public class KeywordBean {
	private int srch_code;
	private String srch_name;
	private Date create_dt;
	private int create_id;
	private String remark;	
	
	public int getSrch_code() {
		return srch_code;
	}
	public void setSrch_code(int srch_code) {
		this.srch_code = srch_code;
	}
	public String getSrch_name() {
		return srch_name;
	}
	public void setSrch_name(String srch_name) {
		this.srch_name = srch_name;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	public int getCreate_id() {
		return create_id;
	}
	public void setCreate_id(int create_id) {
		this.create_id = create_id;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
