package training360.locations.dtos;

import lombok.Data;

@Data
public class LocationDto {

    private Long id;
    private String name;
    private double lat;
    private double lon;
}
