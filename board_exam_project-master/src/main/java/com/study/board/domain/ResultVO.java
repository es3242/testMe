package com.eastflag.fullstack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data는 setter, getter를 자동으로 만들어 준다.
@NoArgsConstructor // @NoArgsConstructor는 디폴트 생성자를 만들어준다.
@AllArgsConstructor // @AllArgsConstructor는 모든 파라메터를 가진 생성자를 만들어준다.
public class ResultVO {
    private int code;
    private String message;
}
