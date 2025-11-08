package com.example.ecommerce.controller.nmi;

import com.example.ecommerce.service.nmi.NmiCustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nmi")
public class NmiController {

    private final NmiCustomerService nmiClient;

    public NmiController(NmiCustomerService nmiClient) {
        this.nmiClient = nmiClient;
    }

    @GetMapping("/addCustomer")
    public String addCustomer() {
        return nmiClient.addCustomerVault();
    }
}