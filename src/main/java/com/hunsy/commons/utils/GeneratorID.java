package com.hunsy.commons.utils;

/**
 * 分布式下主键id生成规则算法，基于twitter的snowflake算法实现
 *
 * @author hunsy
 */
public class GeneratorID {

    /**
     * 机器标识位数
     */
    private final long workerIdBits = 7L;
    /**
     * 数据中心标识位数
     */
    private final long datacenterIdBits = 3L;
    /**
     * 机器id最大值
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 数据中心id最大值
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 毫秒内自增位数
     */
    private final long sequenceBits = 12L;
    /**
     * 机器id向左偏移12位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 数据中心向左偏移17位
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间毫秒向左偏移22位
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 机器id
     */
    private long workerId;
    /**
     * 数据中心id或机房id
     */
    private long datacenterId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * @param workerId     机器id
     * @param datacenterId 数据中心id或机房id
     */
    private GeneratorID(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 对外提供获取Id的方法
     *
     * @return
     */
    public static Long getId() {
        return IDWork.INSTANCE.nextId();
    }

    private synchronized long nextId() {
        long timestamp = timeGen();
        // 时间错误
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则加1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计算满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        // id偏移组合生成最终id，并返回id
        long twepoch = 1288834974657L;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取系统当前毫秒数
     *
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 使用单列模式提供一个唯一实例
     *
     * @author yancy
     */
    private static class IDWork {
        private static final GeneratorID INSTANCE = new GeneratorID(0, 0);
    }
}
