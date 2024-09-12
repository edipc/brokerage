package com.example.brokerage.controller.resource;

import lombok.Builder;
import java.util.List;

@Builder
public record ErrorResource(Integer status, String message, List<String> errors) {

}
