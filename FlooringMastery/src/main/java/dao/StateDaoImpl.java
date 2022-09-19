package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Product;
import model.State;

public class StateDaoImpl implements StateDao{
	
    private final String HEADER = "State,StateName,TaxRate";
    private final String DELIMITER = ",";
    private final String TAX_FILE;
    
    private List<State> allStates = new ArrayList<>();
    
    public StateDaoImpl() {
    	this.TAX_FILE="src/main/resources/SampleFileData/Data/Taxes.txt";
    }

    public StateDaoImpl(String filePath) {
        this.TAX_FILE=filePath;
    }

	@Override
	public List<State> getAllStates() throws DataPersistenceException {
		loadTaxes();
		return this.allStates;
	}
	
    private void loadTaxes() throws DataPersistenceException {
        //Loads one file for a specific date
        Scanner sc;

		try {
			sc = new Scanner(new BufferedReader(new FileReader(this.TAX_FILE)));
		}
		catch(FileNotFoundException e) {
			throw new DataPersistenceException("Could not locate the file", e);
		}
		String currentLine;
		State currentState;
		sc.nextLine();
		while(sc.hasNextLine()) {
			currentLine = sc.nextLine();
			currentState = unmarshallProduct(currentLine);
			this.allStates.add(currentState);
		}
		sc.close();
    }
    
	private State unmarshallProduct(String ItemAsText) {
		
		String[] ItemAsElements = ItemAsText.split(DELIMITER);
		State stateFromFile = new State();
		stateFromFile.setStateAbbreviation(ItemAsElements[0]);
		
		stateFromFile.setStateName(ItemAsElements[1]);
		stateFromFile.setTaxRate(new BigDecimal(ItemAsElements[2]));
		
		return stateFromFile;
	}

}
