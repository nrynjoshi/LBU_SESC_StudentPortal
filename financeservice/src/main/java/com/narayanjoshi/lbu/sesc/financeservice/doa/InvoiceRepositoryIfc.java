package com.narayanjoshi.lbu.sesc.financeservice.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Invoice;

@Repository
public interface InvoiceRepositoryIfc extends JpaRepository<Invoice, Long> {

	List<Invoice> findAllByAccountId(String accountId);

	Invoice findByReferenceId(String referenceId);

	List<Invoice> findByAccount(Account account);

}
