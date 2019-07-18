//package com.xding.redis.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author xding
// * @version 0.1 2017/9/12
// */
//@Component
//public class RedisDistributeLock {
//    @Autowired
//    StringRedisTemplate template;
//
//    private final int defaultExpireTime = 5;
//    private final long defaultAcquireTimeout = 10000L;
//    private final long defaultSleepTime = 1000L;
//
//    public String acquireLock(String lockName) {
//        return acquireLock(lockName, defaultAcquireTimeout, defaultExpireTime);
//    }
//
//    public String acquireLock(String lockName, long acquireTimeout, int expireTime) {
//        String identifier = UUID.randomUUID().toString();
//        String lockKey = "lock:" + lockName;
//
//        long end = System.currentTimeMillis() + acquireTimeout;
//
//        while (System.currentTimeMillis() < end) {
//            boolean isLocked = template.opsForValue().setIfAbsent(lockKey, identifier);
//            if (isLocked) {
//                // 已获得锁，并设置过期时间
//                template.expire(lockKey, expireTime, TimeUnit.MINUTES);
//                return identifier;
//            }
//            if (template.getExpire(lockKey) == -1) {
//                // 未获得锁，检查过期时间，未设置时重新设置
//                template.expire(lockKey, expireTime, TimeUnit.MINUTES);
//            }
//
//            try {
//                Thread.sleep(defaultSleepTime);
//            } catch (InterruptedException ie) {
//                Thread.currentThread().interrupt();
//            }
//
//        }
//        return null;
//    }
//
//    @Transactional
//    public boolean releaseLock(String lockName, String identifier) {
//        String lockKey = "lock:" + lockName;
//
//        while (true) {
//            if (identifier.equals(template.opsForValue().get(lockKey))) {
//                template.multi();
//                template.delete(lockKey);
//                List<Object> result = template.exec();
//                if (result != null) {
//                    continue;
//                }
//                return true;
//            }
//            break;
//        }
//
//        return false;
//    }
//}
