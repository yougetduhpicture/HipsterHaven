package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumiDao;
import database.AlbumiJdbcDao;

@WebServlet("/poista-albumi")

public class PoistaAlbumiServlet extends HttpServlet {
	/**
	 * Vastaanottaa selaimelta tulleen asiakkaan poistopyynnön
	 * 
	 * @param request
	 *            pyyntö
	 * @param response
	 *            vastaus
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// Sijoitetaan muuttujaan pyynnön parametrina tullut poistettavan asiakkaan
			// id-arvo
			String idStr = request.getParameter("albumiId");
			int albumiId = Integer.parseInt(idStr);
			// Luodaan albumidao
			AlbumiDao albumidao = new AlbumiJdbcDao();
			// Poistetaan asiakkaan tiedot tietokannasta
			boolean poistoOnnistui = albumidao.removeAlbumi(albumiId);
			if (poistoOnnistui) {
				// uudelleenohjataan pyyntö /listaa-asiakkaat-endpointiin
				response.sendRedirect("/koti");
			} else {
				request.setAttribute("viesti", "Pizzan poistossa tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
			request.setAttribute("viesti", "Sovelluksessa tapahtui virhe,");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}

		
	}

}