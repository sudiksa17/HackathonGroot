package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepoService extends JpaRepository<Account, String> {
}
