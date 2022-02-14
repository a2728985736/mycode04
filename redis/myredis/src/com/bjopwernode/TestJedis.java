package com.bjopwernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJedis {
    public static void main(String[] args) {
        //创建jedis对象
        //参数是ip地址，端口号
        //Jedis jedis = new Jedis("192.168.220.131", 6379);
        //方式二:从连接池中拿到jedis对象
        //创建jedis对象
        JedisPool jedisPool = RedisUtils.open("192.168.220.131", 6379);
        //获取jedis对象
        Jedis jedis = jedisPool.getResource();
        //删除数据库中的所有数据
        jedis.flushDB();
        //编写内容到key中
        jedis.set("str1","aaaa");
        jedis.set("str2","bbbb");
        String a = jedis.get("str1");
        System.out.println(a);
        System.out.println("====================");
        //创建Map集合对象
        Map<String,String> map = new HashMap<String, String>();
        //存放数据到map集合中
        map.put("id","10001");
        map.put("name","陈冠希");
        map.put("age","35");
        //hmset命令表示将一个或多个数据存放到某一个key中
        //如果这个key不存在就会自动创建这个key然后再存放进去。
        jedis.hmset("student",map);

        //hmget命令表示获取一个或多个key中属性的值
        //参数:1.指定某一个key 2.指定获取那些属性的值
        List<String> hmget = jedis.hmget("student", "id", "name", "age");
        //循环
        for (String s : hmget) {
            System.out.println(s);
        }

        //关闭连接池
        jedisPool.close();

    }
}
