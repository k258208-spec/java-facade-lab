package lab.facade;
public class Main {
public static void main(String[] args) {
		// Demonstrate using HDD implementation
		System.out.println("=== Using HDD implementation ===");
		ComputerFacade computerHdd = new ComputerFacade(new Cpu(), new Memory(), new Hdd());
		computerHdd.start();
		System.out.println("Main: doing user tasks...\n");
		computerHdd.sleep();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		computerHdd.wake();
		computerHdd.shutdown();

		// Demonstrate switching to SSD implementation
		System.out.println("=== Using SSD implementation ===");
		ComputerFacade computerSsd = new ComputerFacade(new Cpu(), new Memory(), new Ssd());
		computerSsd.start();
		System.out.println("Main: doing user tasks...\n");
		computerSsd.shutdown();
}
}