package com.buixuantu.Service;

import java.util.List;

import com.buixuantu.entity.PositionsEntity;

public interface PositionsService {
	List<PositionsEntity> getAllPositions();
	PositionsEntity findPositionById(String id);
	void updatePositionById(String id, boolean a);
	PositionsEntity addPosition(PositionsEntity position);
	void deletePositionById(String id);
}
