package training360.locations;

import lombok.Data;

@Data
public class CreateLocationCommand {

    private String name;
    private double lat;
    private double lon;
}
