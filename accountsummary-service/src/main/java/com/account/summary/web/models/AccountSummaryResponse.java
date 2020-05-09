package com.account.summary.web.models;

import lombok.Data;

@Data
public class AccountSummaryResponse {
    private String accountCode;
    private Integer currentLimit = 0;

	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public Integer getCurrentLimit() {
		return currentLimit;
	}
	public void setCurrentLimit(Integer currentLimit) {
		this.currentLimit = currentLimit;
	}
}
