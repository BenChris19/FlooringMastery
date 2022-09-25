package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Product;

/**
 * Product dao implementation 
 * @author benat
 *
 */
public class ProductDaoImpl implements ProductDao{
	
    private final String DELIMITER = ",";
    private final String PRODUCT_FILE;
    
    private List<Product> allProducts = new ArrayList<>();
    
    /**
     * Constructor for Product dao implementation
     */
    public ProductDaoImpl() {
    	this.PRODUCT_FILE="src/main/resources/SampleFileData/Data/Products.txt";
    }

    /**
     * Product dao implemenattion with constructor
     * @param filePath
     */
    public ProductDaoImpl(String filePath) {
        this.PRODUCT_FILE = filePath;
    }

    /**
     * Get all products from the file
     */
	@Override
	public List<Product> getAllProducts() throws DataPersistenceException {
		loadProducts();
		return this.allProducts;
	}
	
	/**
	 * Load product from Product.txt file
	 * @throws DataPersistenceException
	 */
    private void loadProducts() throws DataPersistenceException {
        //Loads one file for a specific date
        Scanner sc;

		try {
			sc = new Scanner(new BufferedReader(new FileReader(this.PRODUCT_FILE)));
		}
		catch(FileNotFoundException e) {
			throw new DataPersistenceException("Could not locate the file", e);
		}
		String currentLine;
		Product currentProduct;
		sc.nextLine();
		while(sc.hasNextLine()) {
			currentLine = sc.nextLine();
			currentProduct = unmarshallProduct(currentLine);
			allProducts.add(currentProduct);
		}
		sc.close();
    }
    
    /**
     * Unmarshall product from textFile
     * @param ItemAsText
     * @return
     */
	private Product unmarshallProduct(String ItemAsText) {
		
		String[] ItemAsElements = ItemAsText.split(DELIMITER);
		Product productFromFile = new Product();
		productFromFile.setProductType(ItemAsElements[0]);
		
		productFromFile.setCostPerSquareFoot(new BigDecimal(ItemAsElements[1]));
		productFromFile.setLaborCostPerSquareFoot(new BigDecimal(ItemAsElements[2]));
		
		return productFromFile;
	}

}
