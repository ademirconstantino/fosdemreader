package com.constantinoit.fosdemreader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@JsonIgnoreProperties({"dateObj"})
public class FosdemResponse implements Comparable<FosdemResponse> {

    private String event;
    private String date;
    private Date dateObj;
    private String description;

    @Override
    public int compareTo(FosdemResponse object) {
        System.out.println("compareTo");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        try {
            ZonedDateTime dtItem1 = ZonedDateTime.ofInstant(dateObj.toInstant(), ZoneId.of("CET"));
            ZonedDateTime dtItem2 = ZonedDateTime.ofInstant(object.getDateObj().toInstant(), ZoneId.of("CET"));
            if (dtItem1.isBefore(dtItem2)) {
                return -1;
            } else {
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
