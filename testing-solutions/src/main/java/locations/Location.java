package locations;

public class Location {

	private String name;
	private double latitude;
	private double longitude;

	public Location(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isOnEquator() {
		return latitude == 0;
	}

	public boolean isOnPrimeMeridian() {
		return longitude == 0;
	}
}
