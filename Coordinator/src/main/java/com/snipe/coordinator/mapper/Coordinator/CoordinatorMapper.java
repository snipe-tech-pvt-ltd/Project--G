package com.snipe.coordinator.mapper.Coordinator;

import org.springframework.stereotype.Component;

import com.snipe.coordinator.domain.Coordinator;
import com.snipe.coordinator.mapper.AbstractModelMapper;
import com.snipe.coordinator.Model.CoordinatorModel;

@Component
public class CoordinatorMapper extends AbstractModelMapper<CoordinatorModel, Coordinator>
{

	@Override
	public Class<CoordinatorModel> entityType() {
		return CoordinatorModel.class;
	}

	@Override
	public Class<Coordinator> modelType() {
		return Coordinator.class;
	}

}
