package vo;
import java.sql.Date;

public class ReviewBean {

	private int pro_code;
	private int tit_fg;
	private String tit_text;
	private int sub1_fg;
	private String sub1_text;
	private int sub2_fg;
	private String sub2_text;
	private int sub3_fg;
	private String sub3_text;
	private Date create_dt;
	private int reviewCount;	
	
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

	public String getTit_text() {
		return tit_text;
	}
	public void setTit_text(String tit_text) {
		this.tit_text = tit_text;
	}
	public String getSub1_text() {
		return sub1_text;
	}
	public void setSub1_text(String sub1_text) {
		this.sub1_text = sub1_text;
	}
	public String getSub2_text() {
		return sub2_text;
	}
	public void setSub2_text(String sub2_text) {
		this.sub2_text = sub2_text;
	}
	public String getSub3_text() {
		return sub3_text;
	}
	public void setSub3_text(String sub3_text) {
		this.sub3_text = sub3_text;
	}
	
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

}
