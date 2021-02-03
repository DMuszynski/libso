package pl.dmuszynski.libso.api;

import pl.dmuszynski.libso.service.MessageService;
import pl.dmuszynski.libso.payload.dto.request.MessageRequestDTO;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public void sendContactMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        final String SERVICE_ADMIN_EMAIL = "libso.api@gmail.com";
        final String content = createContentMessage(messageRequestDTO.getFrom(), messageRequestDTO.getContent());

        this.messageService.sendMessage(SERVICE_ADMIN_EMAIL, messageRequestDTO.getSubject(), content, true);
    }

    private String createContentMessage(String emailFrom, String content) {
        return emailFrom + " wysyła wiadomość: " + content;
    }
}
