import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return Collections.unmodifiableList(rentals);
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0 ;
			int daysRented = 0;

			daysRented = getDaysRented(each, daysRented);

			eachCharge = getEachCharge(each, eachCharge, daysRented);

			eachPoint = getEachPoint(each, eachPoint, daysRented);

			result = reportResult(result, each, eachCharge, eachPoint, daysRented);

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";


		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	private String reportResult(String result, Rental each, double eachCharge, int eachPoint, int daysRented) {
		result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
				+ "\tPoint: " + eachPoint + "\n";
		return result;
	}

	private int getEachPoint(Rental each, int eachPoint, int daysRented) {
		eachPoint++;

		if ((each.getVideo().getPriceCode() == VideoEnum.NEW_RELEASE) )
			eachPoint++;

		if ( daysRented > each.getDaysRentedLimit() )
			eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty()) ;
		return eachPoint;
	}

	private double getEachCharge(Rental each, double eachCharge, int daysRented) {
		switch (each.getVideo().getPriceCode()) {
		case VideoEnum.REGULAR:
			eachCharge += 2;
			if (daysRented > 2)
				eachCharge += (daysRented - 2) * 1.5;
			break;
		case VideoEnum.NEW_RELEASE:
			eachCharge = daysRented * 3;
			break;
		}
		return eachCharge;
	}

	public int getDaysRented(Rental each, int daysRented) {
		if (each.getStatus() == 1) { // returned Video
			daysRented = each.getDaysRented(each.getReturnDate(), daysRented);
		} else { // not yet returned
			daysRented = each.getDaysRented(new Date(), daysRented);
		}
		return daysRented;
	}

	int getRentalCount() {
		return rentals.size();
	}
}
