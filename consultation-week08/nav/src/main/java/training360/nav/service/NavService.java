package training360.nav.service;

import org.springframework.stereotype.Service;
import training360.nav.controller.mappers.NavTypeMapper;
import training360.nav.dtos.NavTypeDto;
import training360.nav.model.NavType;

import java.util.ArrayList;
import java.util.List;

@Service
public class NavService {

    private NavTypeMapper mapper;
    private List<NavType> types = new ArrayList<>(List.of(
            new NavType("001", "adobevallas"),
            new NavType("002", "befizetes")));

    public NavService(NavTypeMapper mapper) {
        this.mapper = mapper;
    }

    public List<NavTypeDto> getTypes() {
        return mapper.toDto(types);
    }
}
