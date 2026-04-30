package com.everallity.ecommerce_backend.model.dto.response;

public class TokenValidateResponseDto {
    private Boolean isValid;

    public TokenValidateResponseDto(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean isValid) {
        this.isValid = isValid;
    }
}
