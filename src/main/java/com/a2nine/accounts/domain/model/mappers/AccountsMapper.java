package com.a2nine.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.a2nine.accounts.domain.model.Organisation;
import com.a2nine.accounts.domain.model.postgres.AccountCategory;
import com.a2nine.accounts.domain.model.postgres.AccountTypes;
import com.a2nine.accounts.domain.model.postgres.Accounts;

@Component
public class AccountsMapper {

	public Accounts toPostgresObject(com.a2nine.accounts.domain.model.Accounts accounts) {
		if (accounts == null) {
			return null;
		}
		Accounts pgAccounts = new Accounts();
		pgAccounts.setId(null != accounts.id() ? accounts.id().intValue() : null);
		pgAccounts.setName(accounts.name());
		pgAccounts.setDescription(null != accounts.description() ? accounts.description() : "DEFAULT");

		AccountTypes accountType = new AccountTypes();
		if (accounts.account_type() != null) {
			accountType.setId(accounts.account_type().id());
			accountType.setDescription(accounts.account_type().description());
			accountType.setName(accounts.account_type().name());

			AccountCategory account_category = new AccountCategory();
			account_category.setId(accounts.account_type().accountCategory().id());
			account_category.setDescription(accounts.account_type().accountCategory().description());
			account_category.setName(accounts.account_type().accountCategory().name());
			accountType.setAccountCategory(account_category);
		}

		pgAccounts.setAccountTypes(accountType);
		pgAccounts.setCurrentBalance(accounts.currentBalance());
		pgAccounts.setDateupdated(accounts.dateUpdated());
		pgAccounts.setIsActive(accounts.getIsActive());
		pgAccounts.setOrgcode(accounts.organisation().code());
		pgAccounts.setOrgName(accounts.organisation().name());
		return pgAccounts;
	}

	public com.a2nine.accounts.domain.model.Accounts toDomainObject(Accounts pgAccounts) {
		if (pgAccounts == null) {
			return null;
		}
		com.a2nine.accounts.domain.model.AccountTypes acctType = null;
		if (pgAccounts.getAccountTypes().getId() != null) {
			acctType = new com.a2nine.accounts.domain.model.AccountTypes(pgAccounts.getAccountTypes().getId(),
					pgAccounts.getAccountTypes().getName(),
					new com.a2nine.accounts.domain.model.AccountCategory(
							pgAccounts.getAccountTypes().getAccountCategory().getId(),
							pgAccounts.getAccountTypes().getAccountCategory().getName(),
							pgAccounts.getAccountTypes().getAccountCategory().getDescription()),
					pgAccounts.getAccountTypes().getDescription());
		}

		com.a2nine.accounts.domain.model.Accounts accounts = new com.a2nine.accounts.domain.model.Accounts(
				pgAccounts.getId().longValue(), pgAccounts.getName(), pgAccounts.getDescription(), acctType,
				pgAccounts.getCurrentBalance(), pgAccounts.getDateupdated(), pgAccounts.getIsActive(),
				new Organisation(pgAccounts.getOrgName(), pgAccounts.getOrgcode()));

		return accounts;
	}

	public List<com.a2nine.accounts.domain.model.Accounts> toListDomainObjects(List<Accounts> accounts) {
		List<com.a2nine.accounts.domain.model.Accounts> listOfAccounts = new ArrayList<com.a2nine.accounts.domain.model.Accounts>();
		accounts.forEach(c -> {
			listOfAccounts.add(toDomainObject(c));
		});
		return listOfAccounts;
	}
}
