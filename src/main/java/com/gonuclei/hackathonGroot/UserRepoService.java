package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepoService extends JpaRepository<Users, Long> {
}
