package com.demo.quizserver.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer quesId;
    private String userResponse;
}
