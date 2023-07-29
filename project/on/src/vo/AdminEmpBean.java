package vo;

import java.sql.Date;

public class AdminEmpBean {
	private int emp_code;
	private String emp_pass;
	private String emp_id;
	private String emp_name;
	private Date create_dt;
	private int create_id;
	private String remark;
	
	public int getEmp_code() {
		return emp_code;
	}
	public void setEmp_code(int emp_code) {
		this.emp_code = emp_code;
	}
	public String getEmp_pass() {
		return emp_pass;
	}
	public void setEmp_pass(String emp_pass) {
		this.emp_pass = emp_pass;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
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
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
}
