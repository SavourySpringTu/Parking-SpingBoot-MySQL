package com.buixuantu.parking.Service;

import java.time.LocalDate;
import java.util.List;

import com.buixuantu.parking.entity.RevenueEntity;

public interface RevenueService {
	List<RevenueEntity> getAllRevenue();
	RevenueEntity findRevenueById(long id);
	RevenueEntity findRevenueByTime(LocalDate time);
	RevenueEntity addRevenue(RevenueEntity revenue);
	RevenueEntity updateRevenueDay();
}
