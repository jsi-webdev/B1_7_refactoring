import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    List<Customer> customers = new ArrayList<Customer>();
    private static Scanner scanner = new Scanner(System.in) ;
    public CustomerManager() {
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + customer.getRentals().size());
            for (Rental rental : customer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }
        }
        System.out.println("End of list");
    }

    public void getCustomerReport() {
        System.out.println("Enter customer name: ");
        String customerName = scanner.next();

        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            String result = foundCustomer.getReport();
            System.out.println(result);
        }
    }

    void registerCustomer() {
        System.out.println("Enter customer name: ") ;
        String name = scanner.next();
        Customer customer = new Customer(name) ;
        customers.add(customer) ;
    }
}