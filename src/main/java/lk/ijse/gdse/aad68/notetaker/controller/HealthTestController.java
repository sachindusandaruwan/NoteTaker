package lk.ijse.gdse.aad68.notetaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthtest")
public class HealthTestController {
    @GetMapping
    public String HealthTest(){
        return "Note collector app run successfull!";
    }
}
//health check ekak danne hariyata run wenawada balanna(end poidekata req ekak dammama response ekak denna puluwanda balanna)