package com.buixuantu.parking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buixuantu.parking.entity.PositionsEntity;


@Repository
public interface PositionsRepository extends JpaRepository<PositionsEntity,String>{
}
