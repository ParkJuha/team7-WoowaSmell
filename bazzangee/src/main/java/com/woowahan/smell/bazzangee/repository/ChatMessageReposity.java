package com.woowahan.smell.bazzangee.repository;

import com.woowahan.smell.bazzangee.domain.ChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChatMessageReposity extends CrudRepository<ChatMessage, Long> {
}
