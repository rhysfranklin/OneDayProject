package com.fdmgroup.OneDayProject;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<UserAccount, Long> {
	

}
