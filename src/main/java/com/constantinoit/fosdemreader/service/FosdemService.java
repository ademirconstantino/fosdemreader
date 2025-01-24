package com.constantinoit.fosdemreader.service;


import com.constantinoit.fosdemreader.model.FosdemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FosdemService {
    @Autowired
    private XMLReader xmlReader;

    public List<FosdemResponse> findEvent(List<String> keywords) {
        return xmlReader.read(keywords);
    }
}
