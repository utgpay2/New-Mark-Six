package com.central.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.central.backend.model.dto.CopyBettingSiteDto;
import com.central.backend.service.*;
import com.central.common.constant.MarksixConstants;
import com.central.common.model.*;
import com.central.common.redis.lock.RedissLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


/**
 * 拷贝彩种规则
 *
 * @author zlt
 * @date 2023-05-09 18:41:24
 */
@Slf4j
@Service
public class CopyBettingRulesServiceImpl implements ICopyBettingRulesService {
    @Autowired
    private ISiteLotteryService siteLotteryService;
    @Autowired
    private ISiteCategoryLotteryService siteCategoryLotteryService;
    @Autowired
    private IQuizService quizService;
    @Autowired
    private IQuizDetailsService quizDetailsService;
    @Autowired
    private IQuizChooseService quizChooseService;

    /**
     * 整个商户拷贝彩种规则
     * @param copyBettingSiteDto
     * @param user
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Result copybettingsite(CopyBettingSiteDto copyBettingSiteDto, SysUser user){
        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_SITE_COPY_BETTING_LOCK, user.getSiteId(), user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                return Result.failed("加锁失败");
            }
            LambdaQueryWrapper<SiteLottery> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SiteLottery::getSiteId,copyBettingSiteDto.getSourceSiteId());
            List<SiteLottery> siteLotteryList = siteLotteryService.list(wrapper);
            for (SiteLottery siteLottery : siteLotteryList){
                SiteLottery newSiteLottery = new SiteLottery();
                newSiteLottery.setCreateBy(user.getUsername());
                newSiteLottery.setCreateTime(new Date());
                newSiteLottery.setUpdateTime(new Date());
                newSiteLottery.setUpdateBy(user.getUsername());
                newSiteLottery.setLotteryId(siteLottery.getLotteryId());
                newSiteLottery.setSiteId(copyBettingSiteDto.getTargetSiteId());
                newSiteLottery.setSiteCode(copyBettingSiteDto.getTargetSiteCode());
                newSiteLottery.setSiteName(copyBettingSiteDto.getTargetSiteName());
                siteLotteryService.save(newSiteLottery);

                LambdaQueryWrapper<SiteCategoryLottery> sclwrapper = new LambdaQueryWrapper<>();
                sclwrapper.eq(SiteCategoryLottery::getSiteLotteryId,siteLottery.getId());
                List<SiteCategoryLottery> categoryLotteryList = siteCategoryLotteryService.list(sclwrapper);
                for(SiteCategoryLottery categoryLottery:categoryLotteryList){
                    SiteCategoryLottery newCategoryLottery = new SiteCategoryLottery();
                    newCategoryLottery.setCategoryId(categoryLottery.getCategoryId());
                    newCategoryLottery.setSiteLotteryId(newSiteLottery.getId());
                    newCategoryLottery.setSiteId(Long.valueOf(newSiteLottery.getSiteId()));
                    newCategoryLottery.setCreateBy(user.getUsername());
                    newCategoryLottery.setCreateTime(new Date());
                    newCategoryLottery.setUpdateTime(new Date());
                    newCategoryLottery.setUpdateBy(user.getUsername());
                    siteCategoryLotteryService.save(newCategoryLottery);

                    LambdaQueryWrapper<Quiz> quizwrapper = new LambdaQueryWrapper<>();
                    quizwrapper.eq(Quiz::getSiteCategoryId,categoryLottery.getId());
                    List<Quiz> quizList = quizService.list(quizwrapper);
                    for(Quiz quiz:quizList){
                        Quiz newQuiz = new Quiz();
                        BeanUtils.copyProperties(quiz, newQuiz);
                        newQuiz.setId(null);
                        newQuiz.setSiteCategoryId(newCategoryLottery.getId());
                        newQuiz.setSiteId(Long.valueOf(newSiteLottery.getSiteId()));
                        newQuiz.setSiteLotteryId(newCategoryLottery.getSiteLotteryId());
                        newQuiz.setCreateBy(user.getUsername());
                        newQuiz.setCreateTime(new Date());
                        newQuiz.setUpdateTime(new Date());
                        newQuiz.setUpdateBy(user.getUsername());
                        quizService.save(newQuiz);

                        LambdaQueryWrapper<QuizDetails> quizdetailswrapper = new LambdaQueryWrapper<>();
                        quizdetailswrapper.eq(QuizDetails::getQuizId,quiz.getId());
                        List<QuizDetails> quizDetailsList = quizDetailsService.list(quizdetailswrapper);
                        for(QuizDetails quizDetails:quizDetailsList){
                            QuizDetails newQuizDetails = new QuizDetails();
                            BeanUtils.copyProperties(quizDetails, newQuizDetails);
                            newQuizDetails.setId(null);
                            newQuizDetails.setQuizId(newQuiz.getId());
                            newQuizDetails.setSiteCategoryId(newCategoryLottery.getId());
                            newQuiz.setSiteId(Long.valueOf(newSiteLottery.getSiteId()));
                            newQuiz.setSiteLotteryId(newCategoryLottery.getSiteLotteryId());
                            newQuizDetails.setCreateBy(user.getUsername());
                            newQuizDetails.setCreateTime(new Date());
                            newQuizDetails.setUpdateTime(new Date());
                            newQuizDetails.setUpdateBy(user.getUsername());
                            quizDetailsService.save(newQuizDetails);

                            LambdaQueryWrapper<QuizChoose> quizchoosewrapper = new LambdaQueryWrapper<>();
                            quizchoosewrapper.eq(QuizChoose::getQuizDetailsId,newQuizDetails.getId());
                            List<QuizChoose> QuizChooseList = quizChooseService.list(quizchoosewrapper);
                            for(QuizChoose quizChoose:QuizChooseList){
                                QuizChoose newQuizChoose = new QuizChoose();
                                BeanUtils.copyProperties(quizChoose, newQuizChoose);
                                newQuizChoose.setId(null);
                                newQuizChoose.setQuizDetailsId(newQuiz.getId());
                                newQuizChoose.setQuizId(newQuiz.getId());
                                newQuizChoose.setSiteCategoryId(newCategoryLottery.getId());
                                newQuiz.setSiteId(Long.valueOf(newSiteLottery.getSiteId()));
                                newQuiz.setSiteLotteryId(newCategoryLottery.getSiteLotteryId());
                                newQuizChoose.setCreateBy(user.getUsername());
                                newQuizChoose.setCreateTime(new Date());
                                newQuizChoose.setUpdateTime(new Date());
                                newQuizChoose.setUpdateBy(user.getUsername());
                                quizChooseService.save(newQuizChoose);
                            }
                        }
                    }
                }
            }
            return Result.succeed("拷贝成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("拷贝失败");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
    }
    /**
     * 整个彩种拷贝彩种规则
     * @param sourceSiteLotteryId
     * @param targetSiteLotteryId
     * @param user
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Result copybettinglottery(Long sourceSiteLotteryId,Long targetSiteLotteryId, SysUser user){
        String lockKey = StrUtil.format(MarksixConstants.Lock.USER_LOTTERY_COPY_BETTING_LOCK, user.getSiteId(), user.getId());
        try {
            boolean lockedSuccess = RedissLockUtil.tryLock(lockKey, MarksixConstants.Lock.WAIT_TIME, MarksixConstants.Lock.LEASE_TIME);
            if (!lockedSuccess) {
                return Result.failed("加锁失败");
            }
            LambdaQueryWrapper<SiteCategoryLottery> sclwrapper = new LambdaQueryWrapper<>();
            sclwrapper.eq(SiteCategoryLottery::getSiteLotteryId,sourceSiteLotteryId);
            List<SiteCategoryLottery> categoryLotteryList = siteCategoryLotteryService.list(sclwrapper);
            SiteCategoryLottery targetSiteLottery = siteCategoryLotteryService.getById(targetSiteLotteryId);
            for(SiteCategoryLottery categoryLottery:categoryLotteryList){
                SiteCategoryLottery newCategoryLottery = new SiteCategoryLottery();
                newCategoryLottery.setCategoryId(categoryLottery.getCategoryId());
                newCategoryLottery.setSiteLotteryId(targetSiteLotteryId);
                newCategoryLottery.setSiteId(targetSiteLottery.getSiteId());
                newCategoryLottery.setCreateBy(user.getUsername());
                newCategoryLottery.setCreateTime(new Date());
                newCategoryLottery.setUpdateTime(new Date());
                newCategoryLottery.setUpdateBy(user.getUsername());
                siteCategoryLotteryService.save(newCategoryLottery);

                LambdaQueryWrapper<Quiz> quizwrapper = new LambdaQueryWrapper<>();
                quizwrapper.eq(Quiz::getSiteCategoryId,categoryLottery.getId());
                List<Quiz> quizList = quizService.list(quizwrapper);
                for(Quiz quiz:quizList){
                    Quiz newQuiz = new Quiz();
                    BeanUtils.copyProperties(quiz, newQuiz);
                    newQuiz.setId(null);
                    newQuiz.setSiteCategoryId(newCategoryLottery.getId());
                    newQuiz.setSiteId(targetSiteLottery.getSiteId());
                    newQuiz.setSiteLotteryId(targetSiteLotteryId);
                    newQuiz.setCreateBy(user.getUsername());
                    newQuiz.setCreateTime(new Date());
                    newQuiz.setUpdateTime(new Date());
                    newQuiz.setUpdateBy(user.getUsername());
                    quizService.save(newQuiz);

                    LambdaQueryWrapper<QuizDetails> quizdetailswrapper = new LambdaQueryWrapper<>();
                    quizdetailswrapper.eq(QuizDetails::getQuizId,quiz.getId());
                    List<QuizDetails> quizDetailsList = quizDetailsService.list(quizdetailswrapper);
                    for(QuizDetails quizDetails:quizDetailsList){
                        QuizDetails newQuizDetails = new QuizDetails();
                        BeanUtils.copyProperties(quizDetails, newQuizDetails);
                        newQuizDetails.setId(null);
                        newQuizDetails.setQuizId(newQuiz.getId());
                        newQuizDetails.setSiteCategoryId(newCategoryLottery.getId());
                        newQuizDetails.setSiteId(targetSiteLottery.getSiteId());
                        newQuizDetails.setSiteLotteryId(targetSiteLotteryId);
                        newQuizDetails.setCreateBy(user.getUsername());
                        newQuizDetails.setCreateTime(new Date());
                        newQuizDetails.setUpdateTime(new Date());
                        newQuizDetails.setUpdateBy(user.getUsername());
                        quizDetailsService.save(newQuizDetails);

                        LambdaQueryWrapper<QuizChoose> quizchoosewrapper = new LambdaQueryWrapper<>();
                        quizchoosewrapper.eq(QuizChoose::getQuizDetailsId,newQuizDetails.getId());
                        List<QuizChoose> QuizChooseList = quizChooseService.list(quizchoosewrapper);
                        for(QuizChoose quizChoose:QuizChooseList){
                            QuizChoose newQuizChoose = new QuizChoose();
                            BeanUtils.copyProperties(quizChoose, newQuizChoose);
                            newQuizChoose.setId(null);
                            newQuizChoose.setQuizDetailsId(newQuiz.getId());
                            newQuizChoose.setQuizId(newQuiz.getId());
                            newQuizChoose.setSiteCategoryId(newCategoryLottery.getId());
                            newQuizChoose.setSiteId(targetSiteLottery.getSiteId());
                            newQuizChoose.setSiteLotteryId(targetSiteLotteryId);
                            newQuizChoose.setCreateBy(user.getUsername());
                            newQuizChoose.setCreateTime(new Date());
                            newQuizChoose.setUpdateTime(new Date());
                            newQuizChoose.setUpdateBy(user.getUsername());
                            quizChooseService.save(newQuizChoose);
                        }
                    }
                }
            }
            return Result.succeed("拷贝成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failed("拷贝失败");
        } finally {
            RedissLockUtil.unlock(lockKey);
        }
    }
}
