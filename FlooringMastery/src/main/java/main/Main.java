package main;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.Controller;
import dao.DataPersistenceException;
import service.InvalidOrderNumberException;
import service.OrderValidationException;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws DataPersistenceException, InvalidOrderNumberException, OrderValidationException, IOException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = ctx.getBean("controller", Controller.class);
        controller.run();
	}
}
