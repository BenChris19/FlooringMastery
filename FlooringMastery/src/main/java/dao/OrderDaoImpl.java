package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Order;


public class OrderDaoImpl implements OrderDao {
	
    private int lastOrderNumber;
    private final String HEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,"
    		+ "Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";
    private final String DELIMITER = ",";
    private final String ORDER_FILE;
    
    public OrderDaoImpl() {
    	this.ORDER_FILE="SampleFileData/Orders/";
    }

    public OrderDaoImpl(String filePath) {
        this.ORDER_FILE = filePath;
    }

	public List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException {
		return loadOrders(dateChoice);
	}

	public Order addOrder(Order o) throws DataPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order editOrder(Order editedOrder) throws DataPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Order removeOrder(Order o) throws DataPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}
	
    private List<Order> loadOrders(LocalDate chosenDate) throws DataPersistenceException {
        //Loads one file for a specific date
        Scanner scanner;
        String fileDate = chosenDate.format(DateTimeFormatter.ofPattern("YYYY-MM-DD"));

        File f = new File(String.format(this.ORDER_FILE + "Orders_%s.txt", fileDate));

        List<Order> orders = new ArrayList<>();

        if (f.isFile()) {
            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(f)));
            } catch (FileNotFoundException e) {
                throw new DataPersistenceException(
                        "-_- Could not load order data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            scanner.nextLine();//Skips scanning document headers
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                if (currentTokens.length == 12) {
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
                    orders.add(currentOrder);
                } else {
                    //Ignore line.
                }
            }
            scanner.close();
            return orders;
        } else {
            return orders;
        }
    }



}
