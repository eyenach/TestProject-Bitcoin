package com.example.bitcoin.repositories;

import java.util.List;

import com.example.bitcoin.entities.Bitcoin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {

    @Override
	List<Bitcoin> findAll();

    @Query("FROM Bitcoin ORDER BY datetime ASC")
    List<Bitcoin> findAllOrder();

    @Query("FROM Bitcoin WHERE datetime >= :startDate and datetime <= :endDate")
    List<Bitcoin> findAllBetween(@Param("startDate") String startDate,@Param("endDate") String endDate);

}
