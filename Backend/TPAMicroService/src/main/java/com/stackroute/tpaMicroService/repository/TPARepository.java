package com.stackroute.tpaMicroService.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tpaMicroService.model.TPA;

@Repository
public interface TPARepository extends JpaRepository<TPA, Integer> { 

    Optional<TPA> findByEmailAndPassword(String email, String password);

	Optional<TPA> findByEmail(String email); 

 

} 

 