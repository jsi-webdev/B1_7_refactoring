import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}


	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			this.status = 1;
			returnDate = new Date() ;
		}
	}

	public Date getReturnDate() {
		return returnDate;
	}


	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented = 0;
		if (getStatus() == 1) { // returned Video
			daysRented = getDaysRented(returnDate, daysRented);
		} else { // not yet returned
			daysRented = getDaysRented(new Date(), daysRented);
		}
		if ( daysRented <= 2) return limit ;

		limit = video.getLimitByVideoType(limit);
		return limit ;
	}

	public int getDaysRented(Date returnDate, int daysRented) {
		long diff = returnDate.getTime() - rentDate.getTime();
		daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		return daysRented;
	}

	String getRentalTitle() {
		return video.getTitle();
	}

	boolean isRented() {
		return video.isRented();
	}

	void setRented() {
		video.setRented(false);
	}

	int getPriceCode() {
		return video.getPriceCode();
	}

	int getVideoPriceCode() {
		return getVideo().getPriceCode();
	}
}
