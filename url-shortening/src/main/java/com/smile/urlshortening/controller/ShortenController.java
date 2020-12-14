package com.smile.urlshortening.controller;

import com.smile.urlshortening.service.impl.UrlShortenServiceImpl;
import com.smile.urlshortening.vo.ShortUrlResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
public class ShortenController {

    private UrlShortenServiceImpl convertServiceImpl;

    @Autowired
    public ShortenController(UrlShortenServiceImpl convertServiceImpl) {
        this.convertServiceImpl = convertServiceImpl;
    }

    @Cacheable(value = "shortenUrl", key = "#url", unless = "#result.sucessFlag == false or #result.ShortUrl.reqCount < 10 ", cacheManager = "cacheManager")
    @GetMapping("/api/shorten")
    public ShortUrlResult saveUrl(@RequestParam String url) {
        return convertServiceImpl.getShortUrl(url);
    }

    @GetMapping("/{param}")
    public RedirectView redirectUrl(@PathVariable String param) {
        ShortUrlResult shortUrlResult = new ShortUrlResult();
        String url = "http://localhost:9000/" + param;
        shortUrlResult = convertServiceImpl.getOriginUrl(url);
        if (shortUrlResult.isSucessFlag()) {
            return new RedirectView(shortUrlResult.getShortUrl().getOriginUrl());
        } else {
            return new RedirectView("http://localhost:8080/wrong");
        }

    }
}
