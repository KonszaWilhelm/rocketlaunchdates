package com.launchdatesandshop.repositories;

import com.launchdatesandshop.entities.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Long> {
}
