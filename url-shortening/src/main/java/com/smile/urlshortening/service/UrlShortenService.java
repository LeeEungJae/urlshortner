package com.smile.urlshortening.service;

import com.smile.urlshortening.vo.ShortUrlResult;

public interface UrlShortenService {
    ShortUrlResult getShortUrl(String url);

    ShortUrlResult getOriginUrl(String url);
}
