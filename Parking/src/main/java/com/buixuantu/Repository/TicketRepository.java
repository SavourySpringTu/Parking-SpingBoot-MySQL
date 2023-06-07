package com.buixuantu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buixuantu.entity.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity,String>{

}
