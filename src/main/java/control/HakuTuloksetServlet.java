package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumiDao;
import database.AlbumiJdbcDao;
import model.Albumi;

@WebServlet ("/hakuTulokset")
public class HakuTuloksetServlet extends HttpServlet{
	
	
	//doGet antaa tuloksen ja heittää sivulle 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Sijoitetaan muuttujaan pyynnön parametrina tullut muokattavan albumin nimi
		String albuminNimi = request.getParameter("albuminNimi");
		
		// Luodaan albumidao rajapinta
		AlbumiDao albumidao = new AlbumiJdbcDao();
		
		// haetaan albumi rajapinnasta nimellällä
		Albumi albumi = albumidao.findByName(albuminNimi);

//asetetaan albumiId ja haettu albumi saataville parametreinä jsp:lle

		request.setAttribute("albumi", albumi); 
//kutsutaan lomaketta
request.getRequestDispatcher("/WEB-INF/Hakutulos.jsp").forward(request, response);
}
	
	
	//doPost pistää takaisin kotiin
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/koti");
		
}
}