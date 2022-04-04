package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Albumi;

	/**
	 * AsiakasJdbcDao-luokka toteuttaa AsiakasDao-rajapinnan palvelut
	 * 
	 * Asiakas-tietokantataulun käsittelypalvelut kuten
		 * findAll() - hae kaikki asiakkaat tietokannasta
		 * findById() - todo: hae yhden asiakkaan tiedot annetulla asiakasid:llä
		 * addAsiakas() - todo: lisää asiakas tietokantaan
		 * updateAsiakas() - todo: päivitä asiakkaan tiedot tietokantaan
		 * removeAsiakas() - todo: poista asiakkaan tiedot tietokannasta
	 *
	 */
public class AlbumiJdbcDao implements AlbumiDao {
	
	
	
	
	
//FIND ALL
	
	
	public ArrayList<Albumi> findAll() {	
		Connection connection = null;  // tietokantayhteys
		PreparedStatement statement = null;  // sql-lause
		ResultSet resultset = null;   // select-lauseen tulostaulu
		ArrayList<Albumi> albumit = new ArrayList<Albumi>();  // tyhjä asiakaslista
		Albumi albumi = null; 
		try {
			// Luodaan yhteys
			connection = Database.getDBConnection();
			// Luodaan komento: haetaan kaikki rivit asiakas-taulusta
			String sqlSelect = 
					"SELECT id, artisti, nimi, genre, kuvaus, kansikuva FROM albumitaulu;";
			// Valmistellaan komento:
			statement = connection.prepareStatement(sqlSelect);
			// Lähetetään select-komento suoritettavaksi tietokantapalvelimelle:
			resultset = statement.executeQuery();
			// Käydään tulostaulun rivit läpi ja luetaan readAlbumi()-metodilla:
			while (resultset.next()) {
				albumi = readAlbumi(resultset);
				// lisätään albumi listaan
				albumit.add(albumi);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Database.closeDBConnection(resultset, statement, connection); // Suljetaan
		}
	
		return albumit;
	}
	
	
	
	
	
//FIND BY ID
	
	
		public Albumi findById(int albumiId) {	
			
			
			//yhteysjutskat
			
			Connection connection = null;  // tietokantayhteys
			PreparedStatement statement = null;  // sql-lause
			ResultSet resultset = null;   // select-lauseen tulostaulu
			Albumi albumi = null; 
			
		
			try {
				// Luodaan yhteys
				connection = Database.getDBConnection();
				// Luodaan komento: haetaan kaikki rivit asiakas-taulusta
				String sqlSelect = 
						"SELECT id, artisti, nimi, genre, kuvaus, kansikuva FROM albumitaulu WHERE id =?;";
				
				// Valmistellaan komento:
				statement = connection.prepareStatement(sqlSelect);
				statement.setInt(1, albumiId);
				// Lähetetään select-komento suoritettavaksi tietokantapalvelimelle:
				resultset = statement.executeQuery();
				// Luodaan ja palautetaan uusi Asiakas-luokan olio
				while (resultset.next()) {
					albumi = readAlbumi(resultset);
				}
					return albumi;
				} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Database.closeDBConnection(resultset, statement, connection); // Suljetaan
			}
		}

//FIND BY NAME

public Albumi findByName(String levynNimi) {	
			
			
			//yhteysjutskat
			
			Connection connection = null;  // tietokantayhteys
			PreparedStatement statement = null;  // sql-lause
			ResultSet resultset = null;   // select-lauseen tulostaulu
			Albumi albumi = null; 
			
		
			try {
				// Luodaan yhteys
				connection = Database.getDBConnection();
				// Luodaan komento: haetaan kaikki rivit asiakas-taulusta
				String sqlSelect = 
						"SELECT id, artisti, nimi, genre, kuvaus, kansikuva FROM albumitaulu WHERE nimi =?;";
				
				// Valmistellaan komento:
				statement = connection.prepareStatement(sqlSelect);
				statement.setString(1, levynNimi);
				// Lähetetään select-komento suoritettavaksi tietokantapalvelimelle:
				resultset = statement.executeQuery();
				// Luodaan ja palautetaan uusi Asiakas-luokan olio
				while (resultset.next()) {
					albumi = readAlbumi(resultset);
				}
					return albumi;
				} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Database.closeDBConnection(resultset, statement, connection); // Suljetaan
			}
		}

//ADD
		
		public boolean addAlbumi(Albumi albumi)  {
			Connection connection = null;
			PreparedStatement stmtInsert = null;
			boolean updateSuccessed = false; 

			try {
				// Luodaan tietokantayhteys
				connection = Database.getDBConnection();
				// Luodaan komento: luodaan uusi albumi tietokannan tauluun
				String sqlInsert = 
						"INSERT INTO albumitaulu (id, artisti, nimi, genre, kuvaus, kansikuva) VALUES (?, ?, ?, ?, ?, ?)";
				// Valmistellaan komento:
				stmtInsert = connection.prepareStatement(sqlInsert);
				
				// Asetetaan parametrisoidun komennon parametrit yksi kerrallaan 
		        // Albumi-taulussa id-sarake auto_increment-tyyppinen, joten ei mukana insertissä
				stmtInsert.setInt(1, albumi.getId());
				stmtInsert.setString(2, albumi.getArtisti());
				stmtInsert.setString(3, albumi.getNimi());
				stmtInsert.setString(4, albumi.getGenre());
				stmtInsert.setString(5, albumi.getKuvaus());
				stmtInsert.setString(6, albumi.getKansikuva());
				
				//Lähetetään INSERT-komento suoritettavaksi tietokantapalvelimelle
				int rowAffected = stmtInsert.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			} finally {
				Database.closeDBConnection(stmtInsert, connection); // Suljetaan statement ja yhteys
			}
			return updateSuccessed;
		}
		
		
		
//REMOVE
	
		public boolean removeAlbumi(int albumiId) {
			Connection connection = null;
			PreparedStatement stmtDelete = null;
			boolean updateSuccessed = false;

			try {
				// Luodaan tietokantayhteys
				connection = Database.getDBConnection();
				//Poistetaan albumi tietokantasta:
				String sql = "DELETE FROM albumitaulu WHERE id = ?";
				stmtDelete = connection.prepareStatement(sql);
				// Asetetaan parametrisoidun delete-komennon parametri 
				stmtDelete.setInt(1, albumiId);
				//Lähetetään DELETE-komento suoritettavaksi tietokantapalvelimelle
				int rowAffected = stmtDelete.executeUpdate();
				if (rowAffected == 1) updateSuccessed = true;
				
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			} finally {
				Database.closeDBConnection(stmtDelete, connection); // Suljetaan statement ja yhteys
			}
			return updateSuccessed;
		}
		
		
		
		
//MODIFY
		
		public boolean modifyAlbumi(int albumiId, Albumi albumi) {
			Connection connection = null;
			PreparedStatement stmtUpdate = null;
			boolean updateSuccessed = false;

			try {
				// Luodaan tietokantayhteys
				connection = Database.getDBConnection();
				//Poistetaan albumi tietokantasta:
				String sql = 
						"UPDATE albumitaulu SET artisti=?, nimi=?, genre=?, kuvaus=?, kansikuva=? WHERE id = ?;";
				
				stmtUpdate = connection.prepareStatement(sql);
				
				// Asetetaan parametrisoidun update-komennon parametri 
				stmtUpdate.setString(1, albumi.getArtisti());
				stmtUpdate.setString(2, albumi.getNimi());
				stmtUpdate.setString(3, albumi.getGenre());
				stmtUpdate.setString(4, albumi.getKuvaus());
				stmtUpdate.setString(5, albumi.getKansikuva());
				stmtUpdate.setInt(6, albumiId);
				
				//Lähetetään UPDATE-komento suoritettavaksi tietokantapalvelimelle
				int rowAffected = stmtUpdate.executeUpdate();
				
				if (rowAffected == 1) {
					updateSuccessed = true;
				}				
			} catch (SQLException e) {
				e.printStackTrace(); // consoleen näkyviin Exception-tilanteen tarkemmat tiedot vianjäljitystä varten
				throw new RuntimeException(e);
			} finally {
				Database.closeDBConnection(stmtUpdate, connection); // Suljetaan statement ja yhteys
			}
			return updateSuccessed;
		}
		
		
		
//READ

	private Albumi readAlbumi(ResultSet resultset) {	
		try {
			// Haetaan yhden albumin tiedot kyselyn tulostaulun (ResultSet-tyyppinen resultset-olion) aktiiviselta tietoriviltä
			int id = resultset.getInt("id");
			String artisti = resultset.getString("artisti");
			String nimi = resultset.getString("nimi");
			String genre = resultset.getString("genre");
			String kuvaus = resultset.getString("kuvaus");
			String kansikuva = resultset.getString("kansikuva");
			
		
		// Luodaan ja palautetaan uusi Albumi-luokan olio
			return new Albumi(id, artisti, nimi, genre, kuvaus, kansikuva);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}
}
