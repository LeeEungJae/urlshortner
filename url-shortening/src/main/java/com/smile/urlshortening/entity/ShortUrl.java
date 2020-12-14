package com.smile.urlshortening.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ShortUrl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String originUrl;

    @Column
    private String shortenUrl;

    @Column
    private long reqCount;

    @Builder
    public ShortUrl(String originUrl, String shortenUrl) {
        this.originUrl = originUrl;
        this.shortenUrl = shortenUrl;

    }

}
