package com.central.marksix.service;

import com.central.common.model.Result;
import com.central.marksix.entity.dto.*;

public interface ILotteryBetCalculationService {
    Result duplexLotteryBetNumber(DuplexLotteryBetDto duplexLotteryBetDto);
    Result braveryTowLotteryBetNumber(BraveryTowLotteryBetDto braveryTowLotteryBetDto);

    Result zodiacBumpLotteryBetNumber(ZodiacBumpLotteryBetDto zodiacBumpLotteryBetDto);

    Result tailBumpLotteryBetNumber(TailBumpLotteryBetDto tailBumpLotteryBetDto);

    Result ZodiacTailBumpLotteryBetNumber(ZodiacTailBumpLotteryBetDto zodiacTailBumpLotteryBetDto);
}
