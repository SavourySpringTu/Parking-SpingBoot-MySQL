package com.buixuantu.parking.Service;

import java.util.List;

import com.buixuantu.parking.entity.PositionsEntity;

public interface PositionsService {
	List<PositionsEntity> getAllPositions();
	PositionsEntity findPositionById(String id);
	void updatePositionById(String id, boolean a);
	PositionsEntity addPosition(String id,boolean status);
	void deletePositionById(String id);
	boolean checkPosition(String id);
}
