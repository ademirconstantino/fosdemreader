package com.constantinoit.fosdemreader.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FindEventRequest {
    private List<String> keywords;
}
