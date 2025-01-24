package com.constantinoit.fosdemreader;

import com.constantinoit.fosdemreader.model.FindEventRequest;
import com.constantinoit.fosdemreader.model.FosdemResponse;
import com.constantinoit.fosdemreader.service.FosdemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FosdemController {

    @Autowired
    private FosdemService service;

    @GetMapping(value = "/findEvent", produces = "application/json")
    public List<FosdemResponse> countDays(FindEventRequest request) {
        return service.findEvent(request.getKeywords());
    }

}