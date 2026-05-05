package com.may.informatic.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.may.informatic.entity.Taker;

@Repository
public interface TakerRepository extends JpaRepository<Taker, Integer>
{
	List<Taker> findByNameContainingIgnoreCase(String name);
	List<Taker> findBySurnameContainingIgnoreCase(String surname);
	List<Taker> findByPhoneNumberContainingIgnoreCase(String phoneNumber);
	List<Taker> findByEmailContainingIgnoreCase(String email);
	Optional<Taker> findById(int id);
	List<Taker> findByHasTakenBookContainingIgnoreCase(String hasTakenBook);
}