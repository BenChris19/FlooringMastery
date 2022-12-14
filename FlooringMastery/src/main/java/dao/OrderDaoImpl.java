package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Order;
import service.OrderValidationException;

/**
 * Order dao implementation
 * @author benat
 *
 */
public class OrderDaoImpl implements OrderDao {

    private Map<Integer,Order> ordersByDate = new HashMap<>();
    

    private final String DELIMITER = ",";
    private final String ORDER_FILE;
    
    /**
     * Constructor with order folders
     */
    public OrderDaoImpl() {
    	this.ORDER_FILE="src/main/resources/SampleFileData/Orders/";
    }

    /**
     * Constructor with order folders ask for file path
     * @param filePath
     */
    public OrderDaoImpl(String filePath) {
        this.ORDER_FILE = filePath;
    }

    /**
     * Get all orders from a date (Search file with date)
     * @param dateChoice
     */
	public List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException {
		return loadOrdersByDate(dateChoice);
	}
	
	/**
	 * Get the last order number
	 */
	public int getLastOrderNumber() throws DataPersistenceException {
		return this.loadLastOrderNumber();
	}

	/**
	 * Add order to the file
	 * @param o
	 */
	public Order addOrder(Order o) throws DataPersistenceException, OrderValidationException {
		this.ordersByDate = new HashMap<>();
		this.loadOrdersByDate(o.getDate());
		Order order = this.ordersByDate.put(o.getOrderNumber(), o);
		writeOrder(o,new ArrayList<>(this.ordersByDate.values()));
		return order;
	}

	/**
	 * Edit order from the file
	 * @param editedOrder
	 */
	public Order editOrder(Order editedOrder) throws DataPersistenceException, OrderValidationException {
		this.loadOrdersByDate(editedOrder.getDate());
		Order order=this.ordersByDate.replace(editedOrder.getOrderNumber(), editedOrder);
		writeOrder(editedOrder,new ArrayList<>(this.ordersByDate.values()));
		return order;
	}

	/**
	 * Remove order from file
	 * @param o
	 */
	public Order removeOrder(Order o) throws DataPersistenceException, OrderValidationException {
		this.loadOrdersByDate(o.getDate());
		Order order=this.ordersByDate.remove(o.getOrderNumber());
		writeOrder(o,new ArrayList<>(this.ordersByDate.values()));
		return order;
	}
	
	private int loadLastOrderNumber() throws DataPersistenceException{
        Scanner scanner;

        File file = new File(this.ORDER_FILE);
        File[] directoryListing = file.listFiles();

        int lastNum = 0;
        
        if (directoryListing != null) {
            for (File f : directoryListing) {
                if (f.isFile()) {
                    try {
                        scanner = new Scanner(
                                new BufferedReader(
                                        new FileReader(f)));
                        String currentLine;
                        String[] currentTokens;
                        scanner.nextLine();//Skips scanning document headers
                        while (scanner.hasNextLine()) {
                            currentLine = scanner.nextLine();
                            currentTokens = currentLine.split(DELIMITER);
                            int num = Integer.parseInt(currentTokens[0]);
                            lastNum = lastNum>num ? lastNum : num;
                        }
                        scanner.close();
                        
                    } catch (FileNotFoundException e) {
                        throw new DataPersistenceException("Could not load order data into memory.", e);
                    }
                }
                else {
                	throw new DataPersistenceException("Incorrect directory file path.");
                }
		
            }
        }
        return lastNum+1;
	}
	
	private void writeOrder(Order order, List<Order> orders) throws OrderValidationException, DataPersistenceException{
		PrintWriter out;
		File file;
		
		try {
			file = new File(this.ORDER_FILE+"Orders_"+order.getDate().format(DateTimeFormatter.ofPattern("MMddyyyy"))+".txt");
			out = new PrintWriter(new FileWriter(file.getAbsolutePath()));
		}
		catch(Exception e) {
			throw new OrderValidationException("Could not save Order data", e);
		}
		
		String OrderAsText="OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total\n";
		for(Order o:orders) {
			OrderAsText += o.getOrderNumber() + DELIMITER;
			OrderAsText += o.getCustomerName() + DELIMITER;
			OrderAsText += o.getState() + DELIMITER;
			OrderAsText += o.getTaxRate() + DELIMITER;
			OrderAsText += o.getProductType() + DELIMITER;
			OrderAsText += o.getArea() + DELIMITER;
			OrderAsText += o.getCostPerSquareFoot() + DELIMITER;
			OrderAsText += o.getLaborCostPerSquareFoot() + DELIMITER;
			OrderAsText += o.getMaterialCost() + DELIMITER;
			OrderAsText += o.getLaborCost() + DELIMITER;
			OrderAsText += o.getTax() + DELIMITER;
			OrderAsText += o.getTotal();
				out.println(OrderAsText);
				out.flush();
				OrderAsText="";
		}
		out.close();
	}
	
    private List<Order> loadOrdersByDate(LocalDate chosenDate) throws DataPersistenceException {
        //Loads one file for a specific date
        Scanner scanner;
        String fileDate = chosenDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        File f = new File(this.ORDER_FILE + "Orders_"+fileDate+".txt");

        if (f.isFile()) {
            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(f)));
            } catch (FileNotFoundException e) {
                throw new DataPersistenceException(
                        "Could not load order data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            scanner.nextLine();//Skips scanning document headers
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                
                Order currentOrder = new Order();
                currentOrder.setDate(LocalDate.parse(fileDate,
                            DateTimeFormatter.ofPattern("MMddyyyy")));
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.setProductType(currentTokens[4]);
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                this.ordersByDate.put(currentOrder.getOrderNumber(), currentOrder);

            }
            scanner.close();
            return new ArrayList<Order>(this.ordersByDate.values());
        } else {
            return new ArrayList<Order>(this.ordersByDate.values());
        }
    }

}
