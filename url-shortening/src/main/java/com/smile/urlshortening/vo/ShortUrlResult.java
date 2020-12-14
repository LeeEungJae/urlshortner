package com.smile.urlshortening.vo;

import java.io.Serializable;

import com.smile.urlshortening.entity.ShortUrl;

import lombok.Data;

@Data
public class ShortUrlResult implements Serializable {
    private static final long serialVersionUID = 1975453437351529266L;
    private ShortUrl shortUrl;
    private boolean sucessFlag;
}
