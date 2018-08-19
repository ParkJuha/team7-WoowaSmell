package com.woowahan.smell.bazzangee.dto;

import com.woowahan.smell.bazzangee.domain.Chat;
import com.woowahan.smell.bazzangee.domain.ChatMessage;
import com.woowahan.smell.bazzangee.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageDto {
    private String contents;

    public ChatMessageDto(String contents) {
        this.contents = contents;
    }

    public ChatMessage toEntity(Chat chat) {
        return new ChatMessage(chat, this.contents);
    }
}
