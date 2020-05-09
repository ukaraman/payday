package com.account.summary.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.summary.entities.Account;
import com.account.summary.exceptions.AccountNotFoundException;
import com.account.summary.services.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<Account> allAccounts(HttpServletRequest request) {
        return accountService.findAllAccounts();
    }

//    @GetMapping("/{code}")
//    public Account accountByCode(@PathVariable String code) {
//        return accountService.findAccountByCode(code)
//                .orElseThrow(() -> new AccountNotFoundException("Account with code ["+code+"] doesn't exist"));
//    }
    
    @GetMapping("/{customerCode}")
    public Account accountByCustomerNo(@PathVariable int customerCode) {
        return accountService.findAccountByCustomerCode(customerCode)
                .orElseThrow(() -> new AccountNotFoundException("Account with customerCode ["+customerCode+"] doesn't exist"));
    }
}
