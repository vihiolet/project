package vo;
import java.sql.Date;

public class AdminProBean {
	private int pro_code; 	//자동발번인데 없어도 되는거 아닌가?	
	private String pro_nm;
	private int menu_code;
	private String pro_company;
	private String pro_img;
	private int srch_code1;
	private String srch_nm1;
	private int srch_code2;
	private String srch_nm2;
	private int srch_code3;
	private String srch_nm3;
	private Date create_dt;
	private String create_id;
	private String pro_explain;
	
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public String getPro_nm() {
		return pro_nm;
	}
	public void setPro_nm(String pro_nm) {
		this.pro_nm = pro_nm;
	}
	public int getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(int menu_code) {
		this.menu_code = menu_code;
	}
	public String getPro_company() {
		return pro_company;
	}
	public void setPro_company(String pro_company) {
		this.pro_company = pro_company;
	}
	public String getPro_img() {
		return pro_img;
	}
	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	public int getSrch_code1() {
		return srch_code1;
	}
	public void setSrch_code1(int srch_code1) {
		this.srch_code1 = srch_code1;
	}
	public String getSrch_nm1() {
		return srch_nm1;
	}
	public void setSrch_nm1(String srch_nm1) {
		this.srch_nm1 = srch_nm1;
	}
	public int getSrch_code2() {
		return srch_code2;
	}
	public void setSrch_code2(int srch_code2) {
		this.srch_code2 = srch_code2;
	}
	public String getSrch_nm2() {
		return srch_nm2;
	}
	public void setSrch_nm2(String srch_nm2) {
		this.srch_nm2 = srch_nm2;
	}
	public int getSrch_code3() {
		return srch_code3;
	}
	public void setSrch_code3(int srch_code3) {
		this.srch_code3 = srch_code3;
	}
	public String getSrch_nm3() {
		return srch_nm3;
	}
	public void setSrch_nm3(String srch_nm3) {
		this.srch_nm3 = srch_nm3;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	public String getCreate_id() {
		return create_id;
	}
	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}
	public String getPro_explain() {
		return pro_explain;
	}
	public void setPro_explain(String pro_explain) {
		this.pro_explain = pro_explain;
	}
}
