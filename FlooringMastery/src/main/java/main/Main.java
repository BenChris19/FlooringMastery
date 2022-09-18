package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.Controller;
import dao.DataPersistenceException;
import service.InvalidOrderNumberException;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws DataPersistenceException, InvalidOrderNumberException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = ctx.getBean("controller", Controller.class);
        controller.run();

	}

}
