package com.aravind.rest.request.objects;

import lombok.Getter;
import lombok.Setter;

/**
 * A Request Object for UrlShortening.
 *
 * @author arvind.n
 */
@Getter
@Setter
public class UrlShortenRequest {

    private String longUrl;
    private String clientId;
}
