package com.payday.history.service;

import org.springframework.stereotype.Service;

import com.payday.history.dto.HistoryDto;
import com.payday.history.entities.History;
import com.payday.history.repositories.AccountHistoryRepository;

@Service
public class AccountHistoryServiceImpl implements AccountHistoryService{
	
	private final AccountHistoryRepository accountHistoryRepository;
	
	
	public AccountHistoryServiceImpl(AccountHistoryRepository accountHistoryRepository) {
		
		this.accountHistoryRepository = accountHistoryRepository;
	}

	

	@Override
	public void saveAccount(HistoryDto historyDto) {
		
		History history = new History();
		history.setAccountNumber(historyDto.getAccountNumber());
		history.setAmount(historyDto.getAmount());
		history.setDesciption(historyDto.getDesciption());
		history.setProcessDate(historyDto.getProcessDate());
		
		accountHistoryRepository.save(history);
		
	}


	@Override
	public void findHistoryByCriteria(HistoryDto historyDto) {
		
		accountHistoryRepository.findById(historyDto.getId());
		
	}




}
