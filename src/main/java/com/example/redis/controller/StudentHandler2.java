package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.List;
import java.util.Set;

/**
 * 操作redis中的5种数据类型
 */
@RestController
@RequestMapping("/redis")
public class StudentHandler2 {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * String
     * @return
     */
    @GetMapping("/string")
    public String testString() {
        redisTemplate.opsForValue().set("string", "hello world");
        return (String) redisTemplate.opsForValue().get("string");
    }

    /**
     * List
     * 可左插入、右插入
     * @return
     */
    @GetMapping("/list")
    public List<String> testList() {
        redisTemplate.delete("list");
        // ListOperations<key泛型, value泛型>
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        // 左插入
        listOperations.leftPush("list", "hello");
        listOperations.leftPush("list", "world");
        // 右插入
        listOperations.rightPush("list", "wo");
        listOperations.rightPush("list", "ai");
        listOperations.rightPush("list", "ni");
        return listOperations.range("list", 0,4);
    }

    /**
     * Set
     * 无序、不重复
     * @return
     */
    @GetMapping("/set")
    public Set<String> testSet() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("set", "hello");
        setOperations.add("set", "hello");
        setOperations.add("set", "world");
        setOperations.add("set", "world");
        setOperations.add("set", "redis");
        setOperations.add("set", "redis");
        Set<String> set = setOperations.members("set");
        return set;
    }

    /**
     * Sorted Set
     * 有序、不重复
     * @return
     */
    @GetMapping("/zset")
    public Set<String> testZSet() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset", "hello", 1);
        zSetOperations.add("zset", "hello", 2);
        zSetOperations.add("zset", "world", 3);
        zSetOperations.add("zset", "world", 4);
        zSetOperations.add("zset", "redis" ,5);
        Set<String> zset = zSetOperations.range("zset", 0, 4);
        return zset;
    }

    /**
     * Hash
     * {key1: {key2: value2}}
     * @return
     */
    @GetMapping("/hash")
    public String testHash() {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("hash", "id", "1");
        hashOperations.put("hash", "name", "zhangsan");
        hashOperations.put("hash", "score", "77.7");
        hashOperations.put("hash", "birthday", "1998-05-16");
        String name = hashOperations.get("hash", "name");
        return name;
    }
}
