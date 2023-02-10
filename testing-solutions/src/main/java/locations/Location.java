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

	public double distanceFrom(Location otherLocation) {
		int earthRadius = 6371;
		double latitudeDifference = Math.toRadians(otherLocation.getLatitude() - this.latitude);
		double longitudeDifference = Math.toRadians(otherLocation.getLongitude() - this.longitude);
		double a = Math.pow(Math.sin(latitudeDifference / 2), 2) + Math.cos(Math.toRadians(this.latitude))
				* Math.cos(Math.toRadians(otherLocation.getLatitude())) * Math.pow(Math.sin(longitudeDifference / 2), 2);
		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return Math.round(earthRadius * b * 10.0) / 10.0;
	}
}
