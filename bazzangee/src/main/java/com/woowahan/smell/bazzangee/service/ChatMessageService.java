package com.woowahan.smell.bazzangee.service;

import com.woowahan.smell.bazzangee.domain.Chat;
import com.woowahan.smell.bazzangee.domain.ChatMessage;
import com.woowahan.smell.bazzangee.dto.ChatMessageDto;
import com.woowahan.smell.bazzangee.domain.User;
import com.woowahan.smell.bazzangee.repository.ChatMessageReposity;
import com.woowahan.smell.bazzangee.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
    @Autowired
    ChatMessageReposity chatMessageReposity;
    @Autowired
    ChatRepository chatRepository;

    public ChatMessage send(ChatMessageDto chatMessageDto, String chatType) {
        Chat chat = chatRepository.findByName(chatType).orElseThrow(() -> new IllegalArgumentException("잘못 된 접근입니다."));
        return chatMessageReposity.save(chatMessageDto.toEntity(chat));
    }
}
