import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VRLogic {
    final CustomerManager customerManager = new CustomerManager();
    final VideoManager videoManager = new VideoManager();
    private static Scanner scanner = new Scanner(System.in);
    public VRLogic() {
    }

    public void clearRentals() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.next();

        Customer foundCustomer = foundCustmer(customerName);
        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + foundCustomer.getRentals().size());
            for (Rental rental : foundCustomer.getRentals()) {
                System.out.print("\tTitle: " + rental.getRentalTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideoPriceCode());
            }

            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }

    public void returnVideo() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.next();

        Customer foundCustomer = foundCustmer(customerName);
        if (foundCustomer == null) return;

        System.out.println("Enter video title to return: ");
        String videoTitle = scanner.next();

        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.getRentalTitle().equals(videoTitle) && rental.isRented()) {
                rental.returnVideo();
                rental.setRented();
                break;
            }
        }
    }

    Customer foundCustmer(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customerManager.customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        if (foundCustomer == null) return null;
        return foundCustomer;
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
        System.out.println("Enter customer name: ");
        String customerName = scanner.next();

        Customer foundCustomer = foundCustmer(customerName);
        if (foundCustomer == null) return;

        System.out.println("Enter video title to rent: ");
        String videoTitle = scanner.next();

        Video foundVideo = null;
        for (Video video : videoManager.videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }

        if (foundVideo == null) return;

        Rental rental = new Rental(foundVideo);
        foundVideo.setRented(true);

        foundCustomer.addRental(rental);
    }

    public void register(String object) {
        if (object.equals("customer")) {
            customerManager.registerCustomer();
        } else {
            videoManager.registerVideo();
        }
    }

    void init() {
        Customer james = new Customer("James") ;
        Customer brown = new Customer("Brown") ;
        customerManager.customers.add(james) ;
        customerManager.customers.add(brown) ;

        Video v1 = new Video("v1", VideoEnum.CD, VideoEnum.REGULAR, new Date()) ;
        Video v2 = new Video("v2", VideoEnum.DVD, VideoEnum.NEW_RELEASE, new Date()) ;
        videoManager.videos.add(v1) ;
        videoManager.videos.add(v2) ;

        Rental r1 = new Rental(v1) ;
        Rental r2 = new Rental(v2) ;

        james.addRental(r1) ;
        james.addRental(r2) ;
    }
}