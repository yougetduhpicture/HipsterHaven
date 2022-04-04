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

@WebServlet ("/albuminmuokkaus")
public class MuokkaaAlbumiaServlet extends HttpServlet{
	

	
// doGet
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Sijoitetaan muuttujaan pyynnön parametrina tullut muokattavan albumin id-arvo
					String idStr = request.getParameter("albumiId");
					int albumiId = Integer.parseInt(idStr);
					
					// Luodaan albumidao rajapinta
					AlbumiDao albumidao = new AlbumiJdbcDao();
					
					// haetaan albumi rajapinnasta Idllä
					Albumi albumi = albumidao.findById(albumiId);
		
		//asetetaan albumiId ja haettu albumi saataville parametreinä jsp:lle

					request.setAttribute("albumi", albumi); 
		//kutsutaan lomaketta
		request.getRequestDispatcher("/WEB-INF/Albuminmuokkauslomake.jsp").forward(request, response);
	}
	
	
	

// DoPost
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// Sijoitetaan muuttujaan pyynnön parametrina tullut muokattavan albumin
			// id-arvo
			String idStr = request.getParameter("id");
			int albumiId = Integer.parseInt(idStr);
			
			//todo: kerää/vastaanota ja tallenna tiedot
			String artisti = request.getParameter("artisti");
			String nimi = request.getParameter("nimi");
			String genre = request.getParameter("genre");
			String kuvaus = request.getParameter("kuvaus"); 
			String kansikuva = request.getParameter("kansikuva"); 

			// Luodaan uusi Albumi-luokan olio edellisillä parametreillä
			Albumi albumi = new Albumi(albumiId, artisti, nimi, genre, kuvaus, kansikuva);
			System.out.println("Albumi: " + albumi.toString());
			
			// servlet kutsuu jsp:tä
			AlbumiDao albumidao = new AlbumiJdbcDao();
			// tallennetaan tiedot tietokantaan 
			boolean paivitysOnnistui = albumidao.modifyAlbumi(albumiId, albumi);
			if (paivitysOnnistui)
				// uudelleenohjaus /listaa-asiakkaat endpointtiin  .jps-käsittelyn sijaan 
				response.sendRedirect("/koti"); 
			else {

				request.setAttribute("viesti", "Albumin muokkauksessa tietokantaan tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
			
		}catch (NumberFormatException e){
			e.printStackTrace();  // tulostetaan Consoleen virhetilanteessa metodikutsupinoa, josta näkee rivinumeron, jossa Exception tapahtuu

			request.setAttribute("viesti", "Albumi-lomakkeella syötetyt tiedot eivät olleet kelvolliset.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}
		finally {
			
		}

}
}