package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.State;

/**
 * Implements state dao implementation
 * @author benat
 *
 */
public class StateDaoImpl implements StateDao{
	
    private final String DELIMITER = ",";
    private final String TAX_FILE;
    
    private List<State> allStates = new ArrayList<>();
    
    /**
     * Constructor for state implmentation with file path specified
     */
    public StateDaoImpl() {
    	this.TAX_FILE="src/main/resources/SampleFileData/Data/Taxes.txt";
    }

    /**
     * Constructor for state dao implementation, user specifies file path
     * @param filePath
     */
    public StateDaoImpl(String filePath) {
        this.TAX_FILE=filePath;
    }

    /**
     * Get all states from the state.txt file
     */
	@Override
	public List<State> getAllStates() throws DataPersistenceException {
		loadTaxes();
		return this.allStates;
	}
	
	/**
	 * Load taxes from state file
	 * @throws DataPersistenceException
	 */
    private void loadTaxes() throws DataPersistenceException {
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
    
    /**
     * Unmarshall state from text
     * @param ItemAsText
     * @return
     */
	private State unmarshallProduct(String ItemAsText) {
		
		String[] ItemAsElements = ItemAsText.split(DELIMITER);
		State stateFromFile = new State();
		stateFromFile.setStateAbbreviation(ItemAsElements[0]);
		
		stateFromFile.setStateName(ItemAsElements[1]);
		stateFromFile.setTaxRate(new BigDecimal(ItemAsElements[2]));
		
		return stateFromFile;
	}

}
