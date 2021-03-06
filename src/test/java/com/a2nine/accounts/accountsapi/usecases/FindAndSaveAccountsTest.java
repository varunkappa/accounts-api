package com.a2nine.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.a2nine.accounts.domain.model.AccountTypes;
import com.a2nine.accounts.domain.model.Accounts;
import com.a2nine.accounts.usecases.FindAndSaveAccounts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindAndSaveAccountsTest {

	@Autowired
	private FindAndSaveAccounts findAndSaveAccounts;

	private List<Accounts> accountsList;

	@Before
	public void setUp() {
		this.accountsList = new ArrayList<>();
		// Set<AccountBalances> accountBalances = new HashSet();
		// accountBalances.add(new AccountBalances(100d, new Date(2016, 06, 06), 200d,
		// new Date(2017, 06, 06), true, 1));
		Accounts accounts = new Accounts("TEST Recievables Accounts", "Test", new AccountTypes(2l));
		accountsList.add(accounts);
	}

	@Test
	public void findAll() {
		List<Accounts> accounts = findAndSaveAccounts.findAllAccountsByOrgcode("DEFAULT");
		assertNotNull(accounts);
	}

	@Test
	public void findAccountsByName() {
		List<Accounts> accounts = findAndSaveAccounts.findAllAccountsByOrgcode("DEFAULT");
		assertNotNull(accounts);
	}

	@After
	public void destroy() {

	}

}
