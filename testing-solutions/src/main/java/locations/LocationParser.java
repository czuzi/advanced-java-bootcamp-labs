package locations;

public class LocationParser {

	public Location parse(String text) {
		String[] split = text.split(",");
		return new Location(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]));
	}
}
