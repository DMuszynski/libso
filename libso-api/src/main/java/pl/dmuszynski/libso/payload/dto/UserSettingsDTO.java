package pl.dmuszynski.libso.payload.dto;

import java.time.LocalDateTime;

public class UserSettingsDTO extends AbstractDTO {

    private String username;

    private String email;

    private LocalDateTime creationDate;
}