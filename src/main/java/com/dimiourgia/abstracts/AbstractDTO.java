package com.dimiourgia.abstracts;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class AbstractDTO {

    @JsonView({ AbstractViews.WithCreationDate.class })
    private LocalDateTime createdAt;

    @JsonView({ AbstractViews.WithModificationDate.class })
    private LocalDateTime updatedAt;

    @JsonView({ AbstractViews.WithDeletionDate.class })
    private LocalDateTime deletedAt;
}
