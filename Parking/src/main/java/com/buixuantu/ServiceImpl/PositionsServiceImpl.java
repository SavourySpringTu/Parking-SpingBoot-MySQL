package com.buixuantu.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buixuantu.Repository.PositionsRepository;
import com.buixuantu.Service.PositionsService;
import com.buixuantu.entity.PositionsEntity;

@Service
public class PositionsServiceImpl implements PositionsService{
	@Autowired
	private PositionsRepository positionsRepository;
	
	@Override
	public List<PositionsEntity> getAllPositions() {
		return positionsRepository.findAll();
	}

	@Override
	public PositionsEntity findPositionById(String id) {
		return positionsRepository.findById(id).orElse(null);
	}

	@Override
	public void updatePositionById(String id, boolean a) {
		PositionsEntity tmp = positionsRepository.findById(id).orElse(null);
		tmp.setStatus(a);
		positionsRepository.save(tmp);
	}

	@Override
	public PositionsEntity addPosition(PositionsEntity position) {
		if(position != null) {
			return positionsRepository.save(position);
		}
		return null;
	}

	@Override
	public void deletePositionById(String id) {
		if(positionsRepository.findById(id).orElse(null).isStatus()==false) {
			positionsRepository.deleteById(id);
		}
	}
}
