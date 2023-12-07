package com.example.security3.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterParams extends BaseFilter {
    private String search;

    public String getSearch() {
        return search != null ? search : "";
    }
}
