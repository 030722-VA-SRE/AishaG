package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.TdModels;

@Repository
public interface TdModelRepository extends JpaRepository<TdModels, Integer> {

}