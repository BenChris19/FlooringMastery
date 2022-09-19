package dao;

import java.util.List;

import model.State;

public interface StateDao {
	
	List<State> getAllStates() throws DataPersistenceException;

}
