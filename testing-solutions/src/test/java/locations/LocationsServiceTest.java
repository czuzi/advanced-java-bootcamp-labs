package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsServiceTest {

	@Mock
	LocationsRepository locationsRepository;

	@InjectMocks
	LocationsService locationsService;

	@Test
	void testCalculateDistanceWithoutMock() {
		LocationsRepository repository = new LocationsRepository() {
			@Override
			public Optional<Location> findByName(String name) {
				return Optional.empty();
			}

			@Override
			public Optional<Double> findLatitudeByName(String name) {
				return Optional.empty();
			}
		};
		LocationsService service = new LocationsService(repository);
		assertEquals(Optional.empty(), service.calculateDistance("Mako", "Jeruzsalem"));
	}

	@Test
	void testWhenFirstStringIsInvalid() {
		when(locationsRepository.findByName("Budapest")).thenReturn(Optional.empty());
		when(locationsRepository.findByName("Paris")).thenReturn(Optional.of(new Location("Paris", 48.87376, 2.25120)));
		assertEquals(Optional.empty(), locationsService.calculateDistance("Budapest", "Paris"));
		verify(locationsRepository).findByName(argThat(locationName -> locationName.equals("Budapest")));
		verify(locationsRepository).findByName(argThat(locationName -> locationName.equals("Paris")));
	}

	@Test
	void testWhenSecondStringIsInvalid() {
		when(locationsRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest", 48.87376, 2.25120)));
		when(locationsRepository.findByName("Paris")).thenReturn(Optional.empty());
		assertEquals(Optional.empty(), locationsService.calculateDistance("Budapest", "Paris"));
	}

	@Test
	void testWithTheSameStrings() {
		when(locationsRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest", 47.49791d, 19.04023d)));
		assertEquals(Optional.of(0.0), locationsService.calculateDistance("Budapest", "Budapest"));
	}

	@Test
	void testWithDifferentValidStrings() {
		when(locationsRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Budapest", 48.87376, 2.25120)));
		when(locationsRepository.findByName("Budapest")).thenReturn(Optional.of(new Location("Debrecen", 47.52997d, 21.63916d)));
		assertEquals(Optional.of(0.0), locationsService.calculateDistance("Budapest", "Budapest"));
	}
}