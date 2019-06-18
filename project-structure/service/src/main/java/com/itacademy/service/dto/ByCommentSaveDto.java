package com.itacademy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ByCommentSaveDto {

    private Long commentId;
    private Long personId;
    private Long resourceId;
    private String text;

    public ByCommentSaveDto(Long personId, Long resourceId) {
        this.personId = personId;
        this.resourceId = resourceId;
    }
}
