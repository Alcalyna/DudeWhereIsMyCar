package com.parkshark.dudewheremycar.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkshark")
public class ParkSharkController {

    @GetMapping(produces = "application/json", path = "/public-parkshark")
    public String publicHelloWorld() {
        return "public parkshark";
    }

    @GetMapping(produces = "application/json", path = "/customer-parkshark")
    @PreAuthorize("hasAuthority('GET_CUSTOMER_PARKSHARK')")
    public String customerHelloWorld() {
        return "customer parkshark";
    }

    @GetMapping(produces = "application/json", path = "/admin-parkshark")
    @PreAuthorize("hasAuthority('GET_ADMIN_PARKSHARK')")
    public String adminHelloWorld() {
        return "admin parkshark";
    }

}
