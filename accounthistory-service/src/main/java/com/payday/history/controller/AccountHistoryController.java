package com.payday.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payday.history.dto.HistoryDto;
import com.payday.history.service.AccountHistoryService;

@RestController
@RequestMapping("/history")
public abstract class AccountHistoryController {

	private  AccountHistoryService accountHistoryService;
	
	@Autowired
	public AccountHistoryController(AccountHistoryService accountHistoryService) {

		this.accountHistoryService = accountHistoryService;
	
	}
	
    @PostMapping("/api/history")
    public void createOrder(@RequestBody HistoryDto historyDto) {
         accountHistoryService.saveAccount(historyDto);
    }

    @GetMapping("/api/history/{id}")
    public void findOrderById(@PathVariable HistoryDto historyDto) {
         accountHistoryService.findHistoryByCriteria(historyDto);
    }
}
