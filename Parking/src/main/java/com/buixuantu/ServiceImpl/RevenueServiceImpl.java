package com.buixuantu.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixuantu.Repository.RevenueRepository;
import com.buixuantu.Service.RevenueService;
import com.buixuantu.entity.RevenueEntity;

@Service
public class RevenueServiceImpl implements RevenueService{
	@Autowired
	private RevenueRepository revenueRepository;

	@Override
	public List<RevenueEntity> getAllRevenue() {
		return revenueRepository.findAll();
	}

	@Override
	public RevenueEntity findRevenueById( long id) {	
		return revenueRepository.findById(id).orElse(null);
	}

	@Override
	public RevenueEntity addRevenue(RevenueEntity revenue) {
		if(revenue!=null) {
			return revenueRepository.save(revenue);
		}
		return null;
	}

	@Override
	public RevenueEntity findRevenueByTime(Date time) {
		List<RevenueEntity> list = revenueRepository.findAll();
		for(RevenueEntity a : list) {
			if(a.getTime()==time) {
				return a;
			}
		}
		return null;
	}
}
