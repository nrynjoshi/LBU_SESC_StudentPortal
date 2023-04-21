package com.narayanjoshi.lbu.sesc.studentportal.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Account;

@Repository
public interface AccountRepositoryIfc extends JpaRepository<Account, Long> {

	Account findByStudentId(long studentId);

}
