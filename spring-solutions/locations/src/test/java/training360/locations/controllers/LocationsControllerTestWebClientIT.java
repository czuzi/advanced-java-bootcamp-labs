package training360.locations.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import training360.locations.dtos.CreateLocationCommand;
import training360.locations.dtos.LocationDto;
import training360.locations.services.LocationsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationsControllerTestWebClientIT {

    @MockBean
    LocationsService service;

    @Autowired
    WebTestClient webClient;

    @Test
    void testCreateLocation() {
        when(service.createLocation(any())).thenReturn(new LocationDto(1L, "bp", 21.213, 21.322));
        webClient.post().uri("/api/locations")
                .bodyValue(new CreateLocationCommand("bp", 21.23, 2.123))
                .exchange()
                .expectStatus().isCreated();
    }
}