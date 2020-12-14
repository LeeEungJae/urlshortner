package com.smile.urlshortening.component;

import org.springframework.stereotype.Component;

@Component
public class UrlEncoder {
    private final String PREFIX_URL = "http://localhost:9000/";
    private final int BASE62 = 62;
    private final char[] BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public String encoding(long param) {
        StringBuffer sb = new StringBuffer();

        while (param > 0) {
            sb.append(BASE62_CHAR[(int) param % BASE62]);
            param /= BASE62;
        }
        System.out.println(PREFIX_URL + sb.toString());
        return PREFIX_URL + sb.toString();
    }

    public long decoding(String param) {
        long sum = 0;
        long power = 1;

        for (int i = 0; i < param.length(); i++) {
            sum += BASE62_CHAR[param.charAt(i)] * power;
            power *= BASE62;
        }

        return sum;
    }

    public long asciiToDecimal(String param) {
        long sum = 0;
        for (int i = 0; i < param.length(); i++) {
            sum += (param.charAt(i) - '0');
        }

        return sum;
    }
}
