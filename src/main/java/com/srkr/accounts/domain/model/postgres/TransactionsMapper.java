package com.srkr.accounts.domain.model.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionsMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	@Autowired
	private HeadersMapper headersMapper;

	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {
		Transactions pgTransactions = new Transactions();
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setLine_item_number(transactions.line_item_number());
		pgTransactions.setName(transactions.name());
		pgTransactions.setAccounts(accountsMapper.toPostgresObject(transactions.accounts()));
		pgTransactions.setHeaders(headersMapper.toPostgresObject(transactions.header()));
		return pgTransactions;
	}

	public com.srkr.accounts.domain.model.Transactions toDomainObject(Transactions pgTransactions) {
		com.srkr.accounts.domain.model.Transactions transactions = new com.srkr.accounts.domain.model.Transactions(
				pgTransactions.getId(), pgTransactions.getUserId(), pgTransactions.getUserName(),
				pgTransactions.getLine_item_number(), pgTransactions.getName(),
				accountsMapper.toDomainObject(pgTransactions.getAccounts()), pgTransactions.getQuantity(),
				pgTransactions.getPrice(), pgTransactions.getAmount(),
				headersMapper.toDomainObject(pgTransactions.getHeaders()));
		return transactions;
	}

}
