package com.login.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tengfei
 * @version 1.0
 * @date 2018/7/13 下午4:15
 */
@Component
public class RedisUtil {
    @Autowired
    private JedisPool pool ;

    /**
     * 获取指定key的值,如果key不存在返回null，如果该Key存储的不是字符串，会抛出一个错误
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = getJedis();
        String value = null;
        value = jedis.get(key);
        close(jedis);
        return value;
    }

    /**
     * 设置key的值为value
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        if(value==null){
            return null;
        }
        Jedis jedis = getJedis();
        String values=jedis.set(key, value);
        close(jedis);
        return values;
    }

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys
     * @return
     */
    public Long del(String... keys) {
        Jedis jedis = getJedis();
        Long value=jedis.del(keys);
        close(jedis);
        return value;
    }

    /**
     * 通过key向指定的value值追加值
     *
     * @param key
     * @param str
     * @return
     */
    public Long append(String key, String str) {
        Jedis jedis = getJedis();
        Long value=jedis.append(key, str);
        close(jedis);
        return value;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Jedis jedis = getJedis();
        Boolean lean=jedis.exists(key);
        close(jedis);
        return lean;
    }

    /**
     * 设置key value,如果key已经存在则返回0
     *
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key, String value) {
        Jedis jedis = getJedis();
        Long nx=jedis.setnx(key, value);
        close(jedis);
        return nx;
    }

    /**
     * 设置key value并指定这个键值的有效期
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public String set(String key,  String value,int seconds) {
        Jedis jedis = getJedis();
        String values=jedis.setex(key, seconds, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key 和offset 从指定的位置开始将原先value替换
     *
     * @param key
     * @param offset
     * @param str
     * @return
     */
    public Long setrange(String key, int offset, String str) {
        Jedis jedis = getJedis();
        Long range=jedis.setrange(key, offset, str);
        close(jedis);
        return range;
    }

    /**
     * 通过批量的key获取批量的value
     *
     * @param keys
     * @return
     */
    public List<String> mget(String... keys) {
        Jedis jedis = getJedis();
        List<String> stringList=jedis.mget(keys);
        close(jedis);
        return stringList;
    }

    /**
     * 批量的设置key:value,也可以一个
     *
     * @param keysValues
     * @return
     */
    public String mset(String... keysValues) {
        Jedis jedis = getJedis();
        String value=jedis.mset(keysValues);
        close(jedis);
        return value;
    }

    /**
     * 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚
     *
     * @param keysValues
     * @return
     */
    public Long msetnx(String... keysValues) {
        Jedis jedis = getJedis();
        Long value=jedis.msetnx(keysValues);
        close(jedis);
        return value;
    }

    /**
     * 设置key的值,并返回一个旧值
     *
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key, String value) {
        Jedis jedis = getJedis();
        String values=jedis.getSet(key, value);
        close(jedis);
        return values;
    }

    /**
     * 通过下标 和key 获取指定下标位置的 value
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     */
    public String getrange(String key, int startOffset, int endOffset) {
        Jedis jedis = getJedis();
        String values=jedis.getrange(key, startOffset, endOffset);
        close(jedis);
        return values;
    }

    /**
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.incr(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key给指定的value加值,如果key不存在,则这是value为该值
     *
     * @param key
     * @param integer
     * @return
     */
    public Long incrBy(String key, long integer) {
        Jedis jedis = getJedis();
        Long values=jedis.incrBy(key, integer);
        close(jedis);
        return values;
    }

    /**
     * 对key的值做减减操作,如果key不存在,则设置key为-1
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.decr(key);
        close(jedis);
        return values;
    }

    /**
     * 减去指定的值
     *
     * @param key
     * @param integer
     * @return
     */
    public Long decrBy(String key, long integer) {
        Jedis jedis = getJedis();
        Long values=jedis.decrBy(key, integer);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取value值的长度
     *
     * @param key
     * @return
     */
    public Long strLen(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.strlen(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = getJedis();
        Long values=jedis.hsetnx(key, field, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key给field设置指定的值,如果key不存在,则先创建
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        Long values=jedis.hset(key, field, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key同时设置 hash的多个field
     *
     * @param key
     * @param hash
     * @return
     */
    public String hmset(String key, Map<String, String> hash) {
        Jedis jedis = getJedis();
        String values=jedis.hmset(key, hash);
        close(jedis);
        return values;
    }

    /**
     * 通过key 和 field 获取指定的 value
     *
     * @param key
     * @param failed
     * @return
     */
    public String hget(String key, String failed) {
        Jedis jedis = getJedis();
        String values=jedis.hget(key, failed);
        close(jedis);
        return values;
    }

    /**
     * 设置key的超时时间为seconds
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Jedis jedis = getJedis();
        Long values=jedis.expire(key, seconds);
        close(jedis);
        return values;
    }

    /**
     * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
     *
     * @param key
     * @param fields 可以是 一个String 也可以是 String数组
     * @return
     */
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = getJedis();
        List<String> stringList= jedis.hmget(key, fields);
        close(jedis);
        return stringList;
    }

    /**
     * 通过key给指定的field的value加上给定的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrby(String key, String field, Long value) {
        Jedis jedis = getJedis();
        Long values=jedis.hincrBy(key, field, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key和field判断是否有指定的value存在
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key, String field) {
        Jedis jedis = getJedis();
        Boolean values=jedis.hexists(key, field);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回field的数量
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.hlen(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key 删除指定的 field
     *
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return
     */
    public Long hdel(String key, String... fields) {
        Jedis jedis = getJedis();
        Long values=jedis.hdel(key, fields);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回所有的field
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.hkeys(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回所有和key有关的value
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        Jedis jedis = getJedis();
        List<String> values=jedis.hvals(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取所有的field和value
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetall(String key) {
        Jedis jedis = getJedis();
        Map<String,String> values=jedis.hgetAll(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key向list头部添加字符串
     *
     * @param key
     * @param strs 可以是一个string 也可以是string数组
     * @return 返回list的value个数
     */
    public Long lpush(String key, String... strs) {
        Jedis jedis = getJedis();
        Long values=jedis.lpush(key, strs);
        close(jedis);
        return values;
    }

    /**
     * 通过key向list尾部添加字符串
     *
     * @param key
     * @param strs 可以是一个string 也可以是string数组
     * @return 返回list的value个数
     */
    public Long rpush(String key, String... strs) {
        Jedis jedis = getJedis();
        Long values=jedis.rpush(key, strs);
        close(jedis);
        return values;
    }

    /**
     * 通过key在list指定的位置之前或者之后 添加字符串元素
     *
     * @param key
     * @param where LIST_POSITION枚举类型
     * @param pivot list里面的value
     * @param value 添加的value
     * @return
     */
    public Long linsert(String key, BinaryClient.LIST_POSITION where,
                        String pivot, String value) {
        Jedis jedis = getJedis();
        Long values=jedis.linsert(key, where, pivot, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key设置list指定下标位置的value
     * 如果下标超过list里面value的个数则报错
     *
     * @param key
     * @param index 从0开始
     * @param value
     * @return 成功返回OK
     */
    public String lset(String key, Long index, String value) {
        Jedis jedis = getJedis();
        String values=jedis.lset(key, index, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key从对应的list中删除指定的count个 和 value相同的元素
     *
     * @param key
     * @param count 当count为0时删除全部
     * @param value
     * @return 返回被删除的个数
     */
    public Long lrem(String key, long count, String value) {
        Jedis jedis = getJedis();
        Long values=jedis.lrem(key, count, value);
        close(jedis);
        return values;
    }

    /**
     * 通过key保留list中从strat下标开始到end下标结束的value值
     *
     * @param key
     * @param start
     * @param end
     * @return 成功返回OK
     */
    public String ltrim(String key, long start, long end) {
        Jedis jedis = getJedis();
        String values=jedis.ltrim(key, start, end);
        close(jedis);
        return values;
    }

    /**
     * 通过key从list的头部删除一个value,并返回该value
     *
     * @param key
     * @return
     */
    public synchronized String lpop(String key) {

        Jedis jedis = getJedis();
        String values=jedis.lpop(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key从list尾部删除一个value,并返回该元素
     *
     * @param key
     * @return
     */
    synchronized public String rpop(String key) {
        Jedis jedis = getJedis();
        String values=jedis.rpop(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value
     * 如果第一个list为空或者不存在则返回null
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    public String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = getJedis();
        String values=jedis.rpoplpush(srckey, dstkey);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取list中指定下标位置的value
     *
     * @param key
     * @param index
     * @return 如果没有返回null
     */
    public String lindex(String key, long index) {
        Jedis jedis = getJedis();
        String values=jedis.lindex(key, index);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回list的长度
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.llen(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取list指定下标位置的value
     * 如果start 为 0 end 为 -1 则返回全部的list中的value
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        List<String> values=jedis.lrange(key, start, end);
        close(jedis);
        return values;
    }

    /**
     * 通过key向指定的set中添加value
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public Long sadd(String key, String... members) {
        Jedis jedis = getJedis();
        Long values=jedis.sadd(key, members);
        close(jedis);
        return values;
    }

    /**
     * 通过key删除set中对应的value值
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    public Long srem(String key, String... members) {
        Jedis jedis = getJedis();
        Long values=jedis.srem(key, members);
        close(jedis);
        return values;
    }

    /**
     * 通过key随机删除一个set中的value并返回该值
     *
     * @param key
     * @return
     */
    public String spop(String key) {
        Jedis jedis = getJedis();
        String values=jedis.spop(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取set中的差集
     * 以第一个set为标准
     *
     * @param keys 可以 是一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public Set<String> sdiff(String... keys) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.sdiff(keys);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取set中的差集并存入到另一个key中
     * 以第一个set为标准
     *
     * @param dstkey 差集存入的key
     * @param keys   可以 是一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public Long sdiffstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        Long values=jedis.sdiffstore(dstkey, keys);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取指定set中的交集
     *
     * @param keys 可以 是一个string 也可以是一个string数组
     * @return
     */
    public Set<String> sinter(String... keys) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.sinter(keys);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取指定set中的交集 并将结果存入新的set中
     *
     * @param dstkey
     * @param keys   可以 是一个string 也可以是一个string数组
     * @return
     */
    public Long sinterstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        Long values=jedis.sinterstore(dstkey, keys);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回所有set的并集
     *
     * @param keys 可以 是一个string 也可以是一个string数组
     * @return
     */
    public Set<String> sunion(String... keys) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.sunion(keys);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回所有set的并集,并存入到新的set中
     *
     * @param dstkey
     * @param keys   可以 是一个string 也可以是一个string数组
     * @return
     */
    public Long sunionstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        Long values=jedis.sunionstore(dstkey, keys);
        close(jedis);
        return values;
    }

    /**
     * 通过key将set中的value移除并添加到第二个set中
     *
     * @param srckey 需要移除的
     * @param dstkey 添加的
     * @param member set中的value
     * @return
     */
    public Long smove(String srckey, String dstkey, String member) {
        Jedis jedis = getJedis();
        Long values=jedis.smove(srckey, dstkey, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取set中value的个数
     *
     * @param key
     * @return
     */
    public Long scard(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.scard(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key判断value是否是set中的元素
     *
     * @param key
     * @param member
     * @return
     */
    public Boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        Boolean values=jedis.sismember(key, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取set中随机的value,不删除元素
     *
     * @param key
     * @return
     */
    public String srandmember(String key) {
        Jedis jedis = getJedis();
        String values=jedis.srandmember(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取set中所有的value
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.smembers(key);
        close(jedis);
        return values;
    }


    /**
     * 通过key向zset中添加value,score,其中score就是用来排序的
     * 如果该value已经存在则根据score更新元素
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Long zadd(String key, double score, String member) {
        Jedis jedis = getJedis();
        Long values=jedis.zadd(key, score, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key删除在zset中指定的value
     *
     * @param key
     * @param members 可以 是一个string 也可以是一个string数组
     * @return
     */
    public Long zrem(String key, String... members) {
        Jedis jedis = getJedis();
        Long values=jedis.zrem(key, members);
        close(jedis);
        return values;
    }

    /**
     * 通过key增加该zset中value的score的值
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public Double zincrby(String key, double score, String member) {
        Jedis jedis = getJedis();
        Double values=jedis.zincrby(key, score, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回zset中value的排名
     * 下标从小到大排序
     *
     * @param key
     * @param member
     * @return
     */
    public Long zrank(String key, String member) {
        Jedis jedis = getJedis();
        Long values=jedis.zrank(key, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回zset中value的排名
     * 下标从大到小排序
     *
     * @param key
     * @param member
     * @return
     */
    public Long zrevrank(String key, String member) {
        Jedis jedis = getJedis();
        Long values=jedis.zrevrank(key, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key将获取score从start到end中zset的value
     * socre从大到小排序
     * 当start为0 end为-1时返回全部
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.zrevrange(key, start, end);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回指定score内zset中的value
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public Set<String> zrangebyscore(String key, String max, String min) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.zrevrangeByScore(key, max, min);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回指定score内zset中的value
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public Set<String> zrangeByScore(String key, double max, double min) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.zrevrangeByScore(key, max, min);
        close(jedis);
        return values;
    }

    /**
     * 返回指定区间内zset中value的数量
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zcount(String key, String min, String max) {
        Jedis jedis = getJedis();
        Long values=jedis.zcount(key, min, max);
        close(jedis);
        return values;
    }

    /**
     * 通过key返回zset中的value个数
     *
     * @param key
     * @return
     */
    public Long zcard(String key) {
        Jedis jedis = getJedis();
        Long values=jedis.zcard(key);
        close(jedis);
        return values;
    }

    /**
     * 通过key获取zset中value的score值
     *
     * @param key
     * @param member
     * @return
     */
    public Double zscore(String key, String member) {
        Jedis jedis = getJedis();
        Double values=jedis.zscore(key, member);
        close(jedis);
        return values;
    }

    /**
     * 通过key删除给定区间内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = getJedis();
        Long values=jedis.zremrangeByRank(key, start, end);
        close(jedis);
        return values;
    }

    /**
     * 通过key删除指定score内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremrangeByScore(String key, double start, double end) {
        Jedis jedis = getJedis();
        Long values=jedis.zremrangeByScore(key, start, end);
        close(jedis);
        return values;
    }

    /**
     * 返回满足pattern表达式的所有key
     * keys(*)
     * 返回所有的key
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = getJedis();
        Set<String> values=jedis.keys(pattern);
        close(jedis);
        return values;
    }

    /**
     * 通过key判断值得类型
     *
     * @param key
     * @return
     */
    public String type(String key) {
        Jedis jedis = getJedis();
        String values=jedis.type(key);
        close(jedis);
        return values;
    }


    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private Jedis getJedis() {
     /*   try (Jedis jedis = pool.getResource()) {
            return jedis;
        }*/
        return pool.getResource();
    }

    public static RedisUtil getRedisUtil() {
        return new RedisUtil();
    }

}