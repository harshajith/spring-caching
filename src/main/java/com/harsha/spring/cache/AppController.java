package com.harsha.spring.cache;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hazelcast")
public class AppController {

    private final Logger logger = LoggerFactory.getLogger(AppController.class);
    private final HazelcastInstance hazelcastInstance;

    @Autowired
    AppController(HazelcastInstance hazelcastInstance){
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostMapping(value = "/write-data")
    public String writeDatToHazelcast(@RequestParam String key, @RequestParam String value){
        Map<String, String> map = hazelcastInstance.getMap("my-map");
        map.put(key, value);
        return "data is stored";
    }

    @GetMapping("/read-data")
    public String readFromHazelcast(@RequestParam String key){
        Map<String, String> map = hazelcastInstance.getMap("my-map");
        return map.get(key);
    }

    @GetMapping("/read-all-data")
    public Map<String, String> readAllFromHazelcast(){
        return hazelcastInstance.getMap("my-map");
    }




}
