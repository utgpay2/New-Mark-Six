package com.central.marksix.service;

import com.central.common.model.Result;
import com.central.marksix.entity.dto.DuplexLotteryBetDto;

public interface ILotteryBetCalculationService {
    Result duplexLotteryBetNumber(DuplexLotteryBetDto duplexLotteryBetDto);
}
