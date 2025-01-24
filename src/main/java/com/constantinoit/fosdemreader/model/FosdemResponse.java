package com.constantinoit.fosdemreader.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class FosdemResponse {

    private String event;
    private String date;
    private String description;
}
