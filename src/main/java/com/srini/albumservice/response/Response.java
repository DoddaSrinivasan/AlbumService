package com.srini.albumservice.response;

public class Response<T> {

    private T content;
    private Error error;

    public Response(T content, Error error) {
        this.content = content;
        this.error = error;
    }

    public T getContent() {
        return content;
    }

    public Error getError() {
        return error;
    }

    public static <T> Response<T> withError(Error error) {
        return new Response<>(null, error);
    }

    public static <T> Response<T> withContent(T content) {
        return new Response<>(content, null);
    }

}
