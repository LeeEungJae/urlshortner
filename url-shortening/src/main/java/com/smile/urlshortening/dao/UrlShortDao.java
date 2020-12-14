package com.smile.urlshortening.dao;

import com.smile.urlshortening.entity.ShortUrl;
import com.smile.urlshortening.entity.ShortUrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UrlShortDao {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public UrlShortDao(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl findByUrl(String url) {
        return shortUrlRepository.findByShortenUrlOrOriginUrl(url, url);
    }

    public boolean exists(String url) {
        return shortUrlRepository.existsByShortenUrlOrOriginUrl(url, url);
    }

    public ShortUrl save(ShortUrl shorturl) {
        return shortUrlRepository.save(shorturl);
    }
}
