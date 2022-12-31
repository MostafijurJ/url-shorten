package com.learn.urlshorten.service;

import com.google.common.hash.Hashing;
import com.learn.urlshorten.model.Url;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Service
public class UrlManagerImpl implements UrlManager {

    @Autowired
    private RedisTemplate<String, Url> redisTemplate;

    @Override
    public String getUrlByKey(@NonNull String key) {
        Url url = redisTemplate.opsForValue().get(key);
        if(ObjectUtils.isEmpty(url)){
            return "Url not found";
        }
        return url.getUrl();
    }

    @Override
    public Url shortenUrl(@NonNull String url) {
        String key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
        Url build = Url.builder().key(key).createdAt(LocalDateTime.now()).url(url).build();
        redisTemplate.opsForValue().set(key, build);
        return build;
    }
}
