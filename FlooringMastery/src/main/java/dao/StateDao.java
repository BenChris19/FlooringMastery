package dao;

import java.util.List;

import model.State;

/**
 * State dao interface
 * @author benat
 *
 */
public interface StateDao {
	
	List<State> getAllStates() throws DataPersistenceException;

}
