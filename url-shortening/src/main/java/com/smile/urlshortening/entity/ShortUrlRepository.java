package com.smile.urlshortening.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    boolean existsByShortenUrlOrOriginUrl(String shorten_url, String origin_url);

    ShortUrl findByShortenUrlOrOriginUrl(String shorten_url, String origin_url);

}
