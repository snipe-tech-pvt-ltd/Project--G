package com.example.demo.mapper.parent;

import org.springframework.stereotype.Component;

import com.example.demo.domain.parent.Parent;
import com.example.demo.mapper.AbstractModelMapper;
import com.example.demo.model.parent.ParentModel;


@Component
public class ParentMapper extends AbstractModelMapper<ParentModel, Parent>{

	@Override
	public Class<ParentModel> entityType() {
		return ParentModel.class;
	}

	@Override
	public Class<Parent> modelType() {
		return Parent.class;
	}


}
