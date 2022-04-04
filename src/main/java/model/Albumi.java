package model;

public class Albumi {

	//attribuutit
	 private int id;
	 private String artisti;
	 private String nimi;
	 private String genre;
	 private String kuvaus;
	 private String kansikuva;
	 
	 //konstruktorit
	 public Albumi() {
		 super();
		 this.id = 0;
		 this.artisti = null;
		 this.nimi = null;
		 this.genre = null;
		 this.kuvaus = null;
		 this.kansikuva = null;
		 
	 }
	 public Albumi(int id, String artisti, String nimi, String genre, String kuvaus, String kansikuva ) {
		 super();
		 this.id = id;
		 this.kansikuva = kansikuva;
		 this.artisti = artisti;
		 this.nimi = nimi;
		 this.genre = genre;
		 this.kuvaus = kuvaus;
	 }
 
	 //getterit

	 public int getId() {
			return id;
		}
		public String getArtisti() {
			return artisti;
		}
		public String getNimi() {
			return nimi;
		}
		public String getKuvaus() {
			return kuvaus;
		}
		
	 public String getKansikuva() {
			return kansikuva;
		}
		public String getGenre() {
			return genre;
		}
		
//setterit
public void setId(int id) {
	this.id = id;
}
public void setArtisti(String artisti) {
	this.artisti = artisti;
}
public void setNimi(String nimi) {
	this.nimi = nimi;
}
public void setKuvaus(String kuvaus) {
	this.kuvaus = kuvaus;
}
public void setKansikuva(String kansikuva) {
	this.kansikuva = kansikuva;
}
public void setGenre(String genre) {
	this.genre = genre;
}
	//toString
	 @Override
	 public String toString() {
		 
		 return id + " " + artisti + " " + nimi + " " + genre + " " + kuvaus + " " + kansikuva;
	 }
	
	
	 
	 
}