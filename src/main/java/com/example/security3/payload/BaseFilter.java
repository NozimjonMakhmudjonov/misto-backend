package com.example.security3.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseFilter {
    @Min(1)
    @Max(20)
    private int size = 20;
    @Min(0)
    private int page = 0;
}
