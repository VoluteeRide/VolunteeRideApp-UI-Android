package com.volunteeride.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.volunteeride.jackson.CustomDateDeserializer;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by mthosani on 12/19/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTOObject implements Serializable {

    protected String id;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    protected DateTime createdDatetime;

    protected Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public DateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(DateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}

