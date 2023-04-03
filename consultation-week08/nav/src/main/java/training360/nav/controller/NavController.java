package training360.nav.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training360.nav.dtos.NavTypeDto;
import training360.nav.service.NavService;
import training360.nav.model.NavType;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NavController {

    private NavService service;

    @GetMapping("/types")
    public List<NavTypeDto> getTypes() {
        return service.getTypes();
    }
}
