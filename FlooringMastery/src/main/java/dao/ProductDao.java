package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	
	List<Product> getAllProducts() throws DataPersistenceException;

}
