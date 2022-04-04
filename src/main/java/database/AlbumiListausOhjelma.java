package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AlbumiListausOhjelma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection dbYhteys = null;

		try {
			// haetaan tietokannasta albumitaulun tietorivit
			Class.forName("org.mariadb.jdbc.Driver");
			dbYhteys = DriverManager.getConnection(DBAccounts.DBURL, DBAccounts.DBUSERNAME, DBAccounts.DBPASSWORD);
			PreparedStatement sqlSelect = dbYhteys.prepareStatement("select id, artisti, nimi, genre, kuvaus from albumitaulu;");
			ResultSet tulostaulu = sqlSelect.executeQuery();
			System.out.println("Albumit: ");
			while (tulostaulu.next()) {
				System.out.print("\n" + tulostaulu.getInt("id"));
				System.out.print( " " + tulostaulu.getString("artisti"));
				System.out.print( " " + tulostaulu.getString("nimi"));
				System.out.print( " " + tulostaulu.getString("genre"));
				System.out.print( " " + tulostaulu.getString("kuvaus"));
				System.out.print( " " + tulostaulu.getString("kansikuva"));
			}
			// suljetaan osa resursseista
			tulostaulu.close();
			sqlSelect.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// suljetaan viimeinen resurssi
				dbYhteys.close(); // Huom. tärkeää sulkea tietokantayhteys!
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}




	}

	}
