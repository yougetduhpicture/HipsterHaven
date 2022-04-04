package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/hae-albumia")

public class HaeAlbumiaServlet extends HttpServlet{
	
//doget näytetään sivu
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/HakuKentta.jsp").forward(request, response);
}
	
	
//doPost Luetaan hakusana ja ohjataan hakutulossivuille
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String albuminNimi = request.getParameter("nimi");
		response.sendRedirect("/hakuTulokset?albuminNimi="+albuminNimi);
	}
}