package com.account.summary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.account.summary.web.models.AccountSummaryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SummaryServiceClient {
    private final RestTemplate restTemplate;
    private final SummaryServiceFeignClient summaryServiceFeignClient;
    //TODO; move this to config file
    private static final String SUMMARY_API_PATH = "http://summary-service/api/";


    @Autowired
    public SummaryServiceClient(RestTemplate restTemplate, SummaryServiceFeignClient summaryServiceFeignClient) {
        this.restTemplate = restTemplate;
        this.summaryServiceFeignClient = summaryServiceFeignClient;
    }

    public List<AccountSummaryResponse> getAccountSummaryLevels() {
        return this.summaryServiceFeignClient.getSummaryLevels();
    }

    @SuppressWarnings("unused")
    List<AccountSummaryResponse> getDefaultAccountSummaryLevels() {
        return new ArrayList<>();
    }

    public Optional<AccountSummaryResponse> getAccountSummaryByCode(int customerCode)
    {
        ResponseEntity<AccountSummaryResponse> itemResponseEntity =
                restTemplate.getForEntity(SUMMARY_API_PATH + "summary/{customerCode}",
                        AccountSummaryResponse.class,
                        customerCode);

        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) 
        {
            return Optional.ofNullable(itemResponseEntity.getBody());
        } else {
            return Optional.empty();
        }
    }

    Optional<AccountSummaryResponse> getDefaultAccountSummaryByCode(String accountCode) {
        AccountSummaryResponse response = new AccountSummaryResponse();
        response.setAccountCode(accountCode);
        response.setCurrentLimit(15000);
        return Optional.ofNullable(response);
    }

}
