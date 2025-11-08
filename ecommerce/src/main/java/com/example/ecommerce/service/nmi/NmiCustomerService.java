package com.example.ecommerce.service.nmi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class NmiCustomerService {

    private static final String NMI_URL = "https://secure.nmi.com/api/transact.php";

    @Value("${nmi.partnerKey}")
    private String partnerKey;

    @Value("${nmi.merchantKey}")
    private String merchantKey;

    private final RestTemplate restTemplate;

    public NmiCustomerService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String addCustomerVault() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> params = new LinkedHashMap<>();
        params.put("customer_vault", "add_customer");
        params.put("ccnumber", "4111111111111111");
        params.put("ccexp", "1234");
        params.put("security_key", "6457Thfj624V5r7WUwc5v6a68Zsd6YEm");

        StringBuilder bodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (bodyBuilder.length() > 0) bodyBuilder.append("&");
            bodyBuilder.append(entry.getKey()).append("=")
                    .append(entry.getValue());
        }

        HttpEntity<String> request = new HttpEntity<>(bodyBuilder.toString(), headers);
        ResponseEntity<String> response =
                restTemplate.exchange(NMI_URL, HttpMethod.POST, request, String.class);

        return response.getBody();
    }

}
