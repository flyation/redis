package com.example.redis.controller;

import com.example.redis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 快速入门
 */
@RestController
@RequestMapping("/redis")
public class StudentHandler {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存
     * @param student
     */
    @PostMapping("/set")
    public void set(@RequestBody Student student) {
        redisTemplate.opsForValue().set("student", student);
    }

    /**
     * 取
     * @param key
     * @return
     */
    @GetMapping("/get/{key}")
    public Student get(@PathVariable("key") String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删
     * @param key
     * @return
     */
    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key) {
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }
}
