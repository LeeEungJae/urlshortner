package com.smile.urlshortening.component;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class UrlValidate {
    public boolean urlValidationCheck(String url) {
        return new UrlValidator().isValid(url);
    }
}
