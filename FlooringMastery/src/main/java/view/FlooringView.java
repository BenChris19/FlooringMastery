package view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class FlooringView {
	
	@Autowired
	private UserIO io;
	
	public FlooringView(UserIO io) {
		this.io = io;
	}
	
	public int displayMenu() {
		io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*<<Flooring Program>>");
        io.print("*1. Display Orders");
        io.print("*2. Add an Order");
        io.print("*3. Edit an Order");
        io.print("*4. Remove an Order");
        io.print("*5. Export All Data");
        io.print("*6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt();		
	}

}
