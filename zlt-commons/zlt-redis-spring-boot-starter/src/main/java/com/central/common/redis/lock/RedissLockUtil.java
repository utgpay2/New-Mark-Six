package com.central.common.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁工具类
 *
 */
@Slf4j
@Component
public class RedissLockUtil {


    private static RedissonClient redissonClient;

    @Autowired
    public void setRedissonClient(RedissonClient locker){
        redissonClient = locker;
    }

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public static void unlock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        unlock(lock);
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public static void unlock(RLock lock){
        if(lock.isLocked() && lock.isHeldByCurrentThread()){
            lock.unlock();
        }
    }


    /**
     * 带超时时间的锁
     *
     * @param lock
     * @param timeout
     * @return
     */
    public static RLock lock(RLock lock, int timeout){
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }


    /**
     * 带超时时间的锁
     *
     * @param lock
     * @param unit
     * @param timeout
     * @return
     */
    public static RLock lock(RLock lock, TimeUnit unit, int timeout){
        lock.lock(timeout, unit);
        return lock;
    }


    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime
     * @param leaseTime
     * @return
     */
    public static boolean tryLock(String lockKey, int waitTime, int leaseTime){
        RLock lock = redissonClient.getLock(lockKey);
        return tryLock(lock, waitTime, leaseTime);
    }


    /**
     * 尝试获取锁
     *
     * @param lock
     * @param waitTime
     * @param leaseTime
     * @return
     */
    public static boolean tryLock(RLock lock, int waitTime, int leaseTime){
        try {
            boolean b = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            return b;
        }catch (InterruptedException e){
            log.error("tryLock.error.lockKey=" + lock.getName() + " errorMessage=" + e.getMessage(), e);
            return false;
        }
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param leaseTime
     * @return
     */
    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime){
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }
}
