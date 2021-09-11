package com.aravind.rest.controllers;

import com.aravind.rest.request.objects.UrlShortenRequest;
import com.aravind.rest.services.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Controller class that contains the endpoint to shorten a given URL.
 *
 * @author aravind.n
 */
@RestController
@RequestMapping(path="/v1/url")
public class UrlShorteningController {

    @Autowired
    private UrlShorteningService urlShorteningService;

    @PostMapping(path = "/shorten")
    public String create(@RequestBody UrlShortenRequest urlShortenRequest) {
        return urlShorteningService.getShortenedUrl(urlShortenRequest.getClientId(), urlShortenRequest.getLongUrl());
    }
}