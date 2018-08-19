package com.woowahan.smell.bazzangee.web.api;

import com.woowahan.smell.bazzangee.domain.ChatMessage;
import com.woowahan.smell.bazzangee.dto.ChatMessageDto;
import com.woowahan.smell.bazzangee.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiChatController {
    @Autowired
    ChatMessageService chatMessageService;

    @MessageMapping("/hello")
    @SendTo("/topic/roomId")
    public ResponseEntity<ChatMessage> broadcasting(ChatMessageDto chatMessageDto) throws Exception {
        log.info("chat message : {}", chatMessageDto);
//        if (!HttpSessionUtils.isLoginUser((HttpSession) session))
//            throw new UnAuthenticationException("로그인 사용자만 채팅이 가능합니다.");
        ChatMessage chatMessage = chatMessageService.send(chatMessageDto, "치킨");
        return ResponseEntity.status(HttpStatus.OK).body(chatMessage);
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/roomId")
//    public ChatTestDto broadcasting(ChatTestDto chatTestDto, SimpMessageHeaderAccessor messageHeaderAccessor) throws Exception {
//        log.info("chatTestDto : {}", chatTestDto);
//        return chatTestDto;
//    }
}
