package com.account.summary.services;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.account.summary.web.models.AccountSummaryResponse;

//@FeignClient(name = "summary-service")
public interface SummaryServiceFeignClient {

    @GetMapping("/api/summary")
    List<AccountSummaryResponse> getSummaryLevels();

    @GetMapping("/api/summary/{accountCode}")
    List<AccountSummaryResponse> getSummaryByAccountCode(String accountCode);

}
