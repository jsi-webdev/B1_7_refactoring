import java.util.Date;

public class Video {
	private String title ;

	private int priceCode ;

	private int videoType ;

	private Date registeredDate ;
	private boolean rented ;

	public Video(String title, int videoType, int priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	public int getLateReturnPointPenalty() {
		int pentalty = 0 ;
		switch ( videoType ) {
			case VideoEnum.VHS: pentalty = 1 ; break ;
			case VideoEnum.CD: pentalty = 2 ; break ;
			case VideoEnum.DVD: pentalty = 3 ; break ;
		}
		return pentalty ;
	}
	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}

	int getLimitByVideoType(int limit) {
		switch ( videoType ) {
			case VideoEnum.VHS: limit = 5 ; break ;
			case VideoEnum.CD: limit = 3 ; break ;
			case VideoEnum.DVD: limit = 2 ; break ;
		}
		return limit;
	}
}
