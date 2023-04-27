package vo;

public class ActionForward {
	
	private boolean redirect= false;
	private String path= null;
	
	public ActionForward() {
		
	}
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.redirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
