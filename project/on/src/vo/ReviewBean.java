package vo;
import java.sql.Date;

public class ReviewBean {

	private int pro_code;
	private int tit_fg;
	private int sub1_fg;
	private int sub2_fg;
	private int sub3_fg;
	private Date create_dt;
	
	public int getPro_code() {
		return pro_code;
	}
	public void setPro_code(int pro_code) {
		this.pro_code = pro_code;
	}
	public int getTit_fg() {
		return tit_fg;
	}
	public void setTit_fg(int tit_fg) {
		this.tit_fg = tit_fg;
	}
	public int getSub1_fg() {
		return sub1_fg;
	}
	public void setSub1_fg(int sub1_fg) {
		this.sub1_fg = sub1_fg;
	}
	public int getSub2_fg() {
		return sub2_fg;
	}
	public void setSub2_fg(int sub2_fg) {
		this.sub2_fg = sub2_fg;
	}
	public int getSub3_fg() {
		return sub3_fg;
	}
	public void setSub3_fg(int sub3_fg) {
		this.sub3_fg = sub3_fg;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	
	

}
