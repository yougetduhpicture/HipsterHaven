package control;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumiDao;
import database.AlbumiJdbcDao;
import model.Albumi;



@WebServlet("/lisaa-albumi")
public class UusiAlbumiLisaaServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/AlbuminLisaysLomake.jsp").forward(request, response);
	}

	/*
	 * Vastaanottaa tietoa selaimelta: Otetaan lomakkeella syötetyn asiakkaan tiedot
	 * request (pyyntö)-olion parametritiedoista ja luodaan saaduista tiedoista
	 * Pizza-luokan olio
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pyydetään lomakkeella syötetyn asiakkaan tiedot request-oliolta

		try {
			String artisti = request.getParameter("artisti");
			String nimi = request.getParameter("nimi");
			String genre = request.getParameter("genre");
			String kuvaus = request.getParameter("kuvaus"); 
			String kansikuva = request.getParameter("kansikuva"); 

			// Luodaan uusi Pizza-luokan olio edellisillä parametreillä
			Albumi albumi = new Albumi(0, artisti, nimi, genre, kuvaus, kansikuva);
			System.out.println("Albumi: " + albumi.toString());
			
			// TODO: talletaan asiakkaan tiedot tietokantaan(DB)
			String viestiteksti = "Albumia " + albumi.getArtisti() + " - " + albumi.getNimi() + " ei vielä lisätä tietokantaan.";
			request.setAttribute("viesti", viestiteksti);
			// "viesti" on keyword, jolla jsp pääsee käsiksi viestitekstiin
			
			// servlet kutsuu jsp:tä
			AlbumiDao albumidao = new AlbumiJdbcDao();
			// tallennetaan tiedot tietokantaan 
			boolean lisaysOnnistui = albumidao.addAlbumi(albumi);
			if (lisaysOnnistui)
				// uudelleenohjaus /listaa-asiakkaat endpointtiin  .jps-käsittelyn sijaan 
				response.sendRedirect("/koti"); 
			else {

				request.setAttribute("viesti", "Albumin lisäyksessä tietokantaan tapahtui virhe.");
				// servlet kutsuu jsp:tä
				request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();  // tulostetaan Consoleen virhetilanteessa metodikutsupinoa, josta näkee rivinumeron, jossa Exception tapahtuu

			request.setAttribute("viesti", "Albumi-lomakkeella syötetyt tiedot eivät olleet kelvolliset.");
			// servlet kutsuu jsp:tä
			request.getRequestDispatcher("/WEB-INF/tapahtumaraportti.jsp").forward(request, response);
		}
	}
}