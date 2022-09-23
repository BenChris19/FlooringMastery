package dao;

import java.util.List;

import model.Product;

/**
 * Product dao interface
 * @author benat
 *
 */
public interface ProductDao {
	
	List<Product> getAllProducts() throws DataPersistenceException;

}
