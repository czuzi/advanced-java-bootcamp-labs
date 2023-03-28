package training360.locations.dtos;

import lombok.Data;

@Data
public class UpdateLocationCommand {

    private Long id;
    private String name;
    private double lat;
    private double lon;
}
