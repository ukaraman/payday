package com.account.summary.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.account.summary.entities.Account;
import com.account.summary.repositories.AccountRepository;
import com.account.summary.web.models.AccountSummaryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AccountService {
    private final AccountRepository AccountRepository;
    private final SummaryServiceClient summaryServiceClient;

    @Autowired
    public AccountService(AccountRepository AccountRepository, SummaryServiceClient summaryServiceClient) {
        this.AccountRepository = AccountRepository;
        this.summaryServiceClient = summaryServiceClient;
    }

    public List<Account> findAllAccounts() {
        List<Account> Accounts = AccountRepository.findAll();
        final Map<String, Integer> summaryLevels = getSummaryLevelsWithFeignClient();
        final List<Account> availableAccounts = Accounts.stream()
                .filter(p -> summaryLevels.get(p.getCode()) != null && summaryLevels.get(p.getCode()) > 0)
                .collect(Collectors.toList());
        return availableAccounts;
    }

    private Map<String, Integer> getSummaryLevelsWithFeignClient() {
        Map<String, Integer> summaryLevels = new HashMap<>();
        List<AccountSummaryResponse> summary = summaryServiceClient.getAccountSummaryLevels();
        for (AccountSummaryResponse item: summary){
        }
        return summaryLevels;
    }

//    public Optional<Account> findAccountByCode(String code) {
//        Optional<Account> AccountOptional = AccountRepository.findByCode(code);
//        if (AccountOptional.isPresent()) {
//            String correlationId = UUID.randomUUID().toString();
//            Optional<AccountSummaryResponse> itemResponseEntity =
//                    this.summaryServiceClient.getAccountSummaryByCode(code);
//            if (itemResponseEntity.isPresent()) {
//            }
//        }
//        return AccountOptional;
//    }
    
    
    public Optional<Account> findAccountByCustomerCode(int customerCode) {
        Optional<Account> AccountOptional = AccountRepository.findByCustomerCode(customerCode);
        if (AccountOptional.isPresent()) 
        {
            Optional<AccountSummaryResponse> itemResponseEntity = summaryServiceClient.getAccountSummaryByCode(customerCode);
            if (itemResponseEntity.isPresent()) 
            {
            
            }
        }
        return AccountOptional;
    }
    
}
