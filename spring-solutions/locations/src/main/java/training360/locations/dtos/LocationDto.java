package training360.locations.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private Long id;
    private String name;
    private double lat;
    private double lon;
}
