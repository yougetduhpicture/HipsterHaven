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



@WebServlet("/koti")   // sovelluksen juuriendpoint
public class KotiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
			// haetaan albumit tietokannasta
					AlbumiDao albumidao = new AlbumiJdbcDao();
					List<Albumi> albumit = albumidao.findAll();
					
					// albumit-lista .jsp:n saataville
					request.setAttribute("albumit", albumit); 
					// pyynnön eteenpäinlähetys asiakas.jsp:lle
					request.getRequestDispatcher("/WEB-INF/koti.jsp").forward(request, response);

		}
	}