package com.woowahan.smell.bazzangee.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<ChatMessage> chatMessges;
}