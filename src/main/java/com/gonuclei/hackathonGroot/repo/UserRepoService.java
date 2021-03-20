package com.gonuclei.hackathonGroot.repo;

import com.gonuclei.hackathonGroot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoService extends JpaRepository<Users, Long> {
}
