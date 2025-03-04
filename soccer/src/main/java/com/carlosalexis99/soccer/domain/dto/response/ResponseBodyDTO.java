package com.carlosalexis99.soccer.domain.dto.response;

public record ResponseBodyDTO(
        String message,
        Boolean status,
        Object data
) {
}
