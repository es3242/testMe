package com.eastflag.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL) // not_null만 포함한다. 즉 property가 null이면 만들지 않는다.
@Data // @Data는 setter, getter를 자동으로 만들어 준다.
public class BoardVO {
    private Integer id;
    private String title;
    private String content;
    private String created;
    private String updated;
}