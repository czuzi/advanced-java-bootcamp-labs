package locations;

import java.util.Optional;

public class LocationsService {

	private LocationsRepository locationsRepository;

	public LocationsService(LocationsRepository locationsRepository) {
		this.locationsRepository = locationsRepository;
	}

	public Optional<Double> calculateDistance(String name1, String name2) {
		Optional<Location> location = locationsRepository.findByName(name1);
		Optional<Location> otherLocation = locationsRepository.findByName(name2);
		if (location.isPresent() && otherLocation.isPresent()) {
			return Optional.of(location.get().distanceFrom(otherLocation.get()));
		} else {
			return Optional.empty();
		}
	}
}
