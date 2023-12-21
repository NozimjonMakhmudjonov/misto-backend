package com.example.security3.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private T payload;
    private List<Error> errors;

    public static <T> Response<T> ok(T data) {
        return new Response<T>().setPayload(data);
    }

    public static Response<Void> error(List<Error> errors) {
        return new Response<Void>().setErrors(errors);
    }
    public static Response<Boolean> ok() {return Response.ok(true);}
}
