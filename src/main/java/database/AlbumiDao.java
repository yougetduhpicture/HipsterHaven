package database;

import java.util.List;

import model.Albumi;

public interface AlbumiDao {

	//etsintä ja löytäminen
	
	public List<Albumi> findAll();
	
	public Albumi findById(int albumiId);
	
	public Albumi findByName(String levynNimi);
	
	//kasaus ja muokkaus
	
	public boolean addAlbumi(Albumi albumi);
	
	public boolean removeAlbumi(int albumiId);
	
	public boolean modifyAlbumi(int albumiId, Albumi albumi);
}
