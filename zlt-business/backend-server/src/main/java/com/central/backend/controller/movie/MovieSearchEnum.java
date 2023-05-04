package com.central.backend.controller.movie;

public enum MovieSearchEnum {
    MOVIE_NAME(0, "影片名"),
    MOVIE_ID(1, "影片ID"),
    ACTOR_NAME(2, "演员名");

    private Integer code;
    private String remark;

    MovieSearchEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
