package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.Contacts;

public interface PostgresContactsRepository extends Repository<Contacts, Long> {

	public Contacts save(Contacts contact);

	List<Contacts> findByFirstname(String firstName);

	List<Contacts> findByLastname(String lastName);
	
	List<Contacts> findAll();

}