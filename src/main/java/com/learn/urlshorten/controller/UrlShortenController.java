package com.learn.urlshorten.controller;

import com.learn.urlshorten.model.Url;
import com.learn.urlshorten.service.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/url-shortener")
public class UrlShortenController {

    @Autowired
    private UrlManager urlManager;

    @RequestMapping(value = "/url", method = RequestMethod.POST)
    public ResponseEntity shortenUrl(@RequestBody String url) {
        Url shortUrlEntry = urlManager.shortenUrl(url);
        return ResponseEntity.ok(shortUrlEntry);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public ResponseEntity getUrl(@PathVariable String key) {
        String url = urlManager.getUrlByKey(key);
        return ResponseEntity.ok(url);
    }
}
