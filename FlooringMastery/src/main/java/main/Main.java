package main;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.Controller;
import dao.DataPersistenceException;
import service.InvalidDateException;
import service.InvalidOrderNumberException;
import service.OrderValidationException;

/**
 * Starting point for flooring mastery application. Run the Spring applicationContext.xml for DI
 * @author benat
 *
 */
public class Main {

	/**
	 * Start the application
	 * @param args
	 * @throws DataPersistenceException
	 * @throws InvalidOrderNumberException
	 * @throws OrderValidationException
	 * @throws IOException
	 * @throws InvalidDateException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException, IOException, InvalidDateException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = ctx.getBean("controller", Controller.class);
        controller.run();
	}
}
