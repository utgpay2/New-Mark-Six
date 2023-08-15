package com.central.backend.mapper;

import com.central.common.model.Line;
import com.central.common.model.Statement;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatementMapper  extends SuperMapper<Statement> {
}
