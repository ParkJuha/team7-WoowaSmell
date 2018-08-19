package com.woowahan.smell.bazzangee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_chat_message_chat"))
    private Chat chat;
    @Column
    @Lob
    private String contents;
    @Column
    private LocalDateTime writtenTime;

    public ChatMessage(Chat chat, String contents) {
        this.chat = chat;
        this.contents = contents;
        this.writtenTime = LocalDateTime.now();
    }
}
