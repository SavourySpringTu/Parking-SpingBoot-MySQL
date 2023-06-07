package com.buixuantu.Service;

import java.util.Date;
import java.util.List;

import com.buixuantu.entity.RevenueEntity;

public interface RevenueService {
	List<RevenueEntity> getAllRevenue();
	RevenueEntity findRevenueById(long id);
	RevenueEntity findRevenueByTime(Date time);
	RevenueEntity addRevenue(RevenueEntity revenue);
}
