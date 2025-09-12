package br.com.url_shortener.url;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlEntityRepository repository;

    public UrlService(UrlEntityRepository repository){
        this.repository = repository;
    }


}
