package com.smile.urlshortening.service.impl;

import com.smile.urlshortening.component.UrlEncoder;
import com.smile.urlshortening.component.UrlValidate;
import com.smile.urlshortening.dao.UrlShortDao;
import com.smile.urlshortening.entity.ShortUrl;
import com.smile.urlshortening.service.UrlShortenService;
import com.smile.urlshortening.vo.ShortUrlResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenServiceImpl implements UrlShortenService {

    private UrlEncoder urlEncoder;
    private UrlValidate urlValidate;
    private UrlShortDao urlShortDao;

    @Autowired
    public UrlShortenServiceImpl(UrlEncoder urlEncoder, UrlValidate urlValidate, UrlShortDao urlShortDao) {
        this.urlEncoder = urlEncoder;
        this.urlValidate = urlValidate;
        this.urlShortDao = urlShortDao;
    }

    @Override
    public synchronized ShortUrlResult getShortUrl(String url) { // synchronized는 비효율적 다른 방법을 찾아보자
        ShortUrlResult shortUrlResult = new ShortUrlResult();
        ShortUrl shortUrl = new ShortUrl();
        if (urlValidate.urlValidationCheck(url) && !url.isEmpty()) {
            if (urlShortDao.exists(url)) {
                shortUrl = urlShortDao.findByUrl(url);
                shortUrl.setReqCount(shortUrl.getReqCount() + 1);
                urlShortDao.save(shortUrl);
                shortUrlResult.setShortUrl(shortUrl);
                shortUrlResult.setSucessFlag(true);
            } else {
                ShortUrl curShortUrl = new ShortUrl();
                curShortUrl.setOriginUrl(url);
                curShortUrl.setReqCount(1);
                long ID = urlEncoder.asciiToDecimal(url);
                String encodeUrl = "";
                try {
                    encodeUrl = urlEncoder.encoding(ID);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    curShortUrl.setShortenUrl(encodeUrl);
                }

                shortUrl = urlShortDao.save(curShortUrl);
                shortUrlResult.setShortUrl(shortUrl);
                shortUrlResult.setSucessFlag(true);
            }
        } else {
            shortUrlResult.setSucessFlag(false);
        }

        return shortUrlResult;

    }

    @Override
    public ShortUrlResult getOriginUrl(String url) {
        ShortUrl shortUrl = new ShortUrl();
        ShortUrlResult shortUrlResult = new ShortUrlResult();
        if (urlShortDao.exists(url)) {
            shortUrl = urlShortDao.findByUrl(url);
            shortUrlResult.setShortUrl(shortUrl);
            shortUrlResult.setSucessFlag(true);
        } else {
            shortUrlResult.setSucessFlag(false);
        }
        return shortUrlResult;
    }

}
