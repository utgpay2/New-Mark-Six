package com.central.backend.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.backend.co.KpnMoneyLogCo;
import com.central.backend.model.vo.KpnMoneyLogVO;
import com.central.common.model.KpnMoneyLog;
import com.central.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/*
 * @Author: Lulu
 * @Date: 2023/2/8
 */
@Mapper
public interface KpnMoneyLogMapper extends SuperMapper<KpnMoneyLog> {


    List<KpnMoneyLog> findList(Page<KpnMoneyLog> page, @Param("r") KpnMoneyLogCo params);

    KpnMoneyLogVO totalNumber(@Param("p") Map<String, Object> params);

}
