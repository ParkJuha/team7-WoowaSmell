package com.woowahan.smell.bazzangee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatTestDto {
    private String contents;

    public ChatTestDto(String contents) {
        this.contents = contents;
    }
}
