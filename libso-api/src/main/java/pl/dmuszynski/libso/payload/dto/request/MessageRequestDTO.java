package pl.dmuszynski.libso.payload.dto.request;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@SuperBuilder
@NoArgsConstructor
public class MessageRequestDTO {

    private String from;

    private String to;

    private String subject;

    private String content;
}
