package action;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.AdminProRegService;

import vo.ActionForward;
import vo.AdminProBean;
import vo.PageInfo;
import vo.ReviewBean;

public class AdminProRegAcrion implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		AdminProBean adminProBean = null;
		String realFolder= "";
		String imageFolder = "/images";
		int fileSize= 5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder= context.getr
		
		return null;
	}

}
