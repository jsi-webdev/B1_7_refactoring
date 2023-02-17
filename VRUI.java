import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VRUI {
	private final CustomerManager customerManager = new CustomerManager();
	private final VideoManager videoManager = new VideoManager();

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				case 3: ui.register("customer") ; break ;
				case 4: ui.register("video") ; break ;
				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break;
				case 8: ui.clearRentals() ; break ;
				case -1: ui.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void clearRentals() {
		System.out.println("Enter customer name: ") ;
		String customerName = VideoManager.scanner.next() ;

		Customer foundCustomer = null ;
		for ( Customer customer: customerManager.customers) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			System.out.println("Name: " + foundCustomer.getName() +
					"\tRentals: " + foundCustomer.getRentals().size()) ;
			for ( Rental rental: foundCustomer.getRentals() ) {
				System.out.print("\tTitle: " + rental.getRentalTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}

			List<Rental> rentals = new ArrayList<Rental>() ;
			foundCustomer.setRentals(rentals);
		}
	}

	public void returnVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = VideoManager.scanner.next() ;

		Customer foundCustomer = foundCustmer(customerName);
		if (foundCustomer == null) return;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = VideoManager.scanner.next() ;

		List<Rental> customerRentals = foundCustomer.getRentals() ;
		for ( Rental rental: customerRentals ) {
			if ( rental.getRentalTitle().equals(videoTitle) && rental.isRented()) {
				rental.returnVideo();
				rental.setRented();
				break ;
			}
		}
	}

	private Customer foundCustmer(String customerName) {
		Customer foundCustomer = null ;
		for ( Customer customer: customerManager.customers) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}
		if ( foundCustomer == null ) return null;
		return foundCustomer;
	}

	private void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customerManager.customers.add(james) ;
		customerManager.customers.add(brown) ;

		Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
		Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
		videoManager.videos.add(v1) ;
		videoManager.videos.add(v2) ;

		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;

		james.addRental(r1) ;
		james.addRental(r2) ;
	}

	public void listVideos() {

		videoManager.listVideos();
	}

	public void listCustomers() {
		customerManager.listCustomers();
	}

	public void getCustomerReport() {

		customerManager.getCustomerReport();
	}

	public void rentVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = VideoManager.scanner.next() ;

		Customer foundCustomer = foundCustmer(customerName);
		if (foundCustomer == null) return;

		System.out.println("Enter video title to rent: ") ;
		String videoTitle = VideoManager.scanner.next() ;

		Video foundVideo = null ;
		for ( Video video: videoManager.videos) {
			if ( video.getTitle().equals(videoTitle) && video.isRented() == false ) {
				foundVideo = video ;
				break ;
			}
		}

		if ( foundVideo == null ) return ;

		Rental rental = new Rental(foundVideo) ;
		foundVideo.setRented(true);

		foundCustomer.addRental(rental);
	}

	public void register(String object) {
		if ( object.equals("customer") ) {
			customerManager.registerCustomer();
		}
		else {
			videoManager.registerVideo();
		}
	}

	private void registerVideo() {

		videoManager.registerVideo();
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = VideoManager.scanner.nextInt() ;

		return command ;

	}
}
