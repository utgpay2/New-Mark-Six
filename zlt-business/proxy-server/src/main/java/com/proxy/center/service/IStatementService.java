package com.proxy.center.service;

import com.central.common.model.Result;
import com.central.common.model.Statement;
import com.central.common.model.SysUser;
import com.central.common.service.ISuperService;

import java.util.List;


public interface IStatementService extends ISuperService<Statement> {


    List<Statement> findList();


    Result saveOrUpdateQuiz(Statement statement, SysUser user);


}
