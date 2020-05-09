package com.payday.history.service;

import com.payday.history.dto.HistoryDto;

public interface AccountHistoryService {
	
	 void findHistoryByCriteria(HistoryDto historyDto);
	 
	 void saveAccount(HistoryDto historyDto);

}
