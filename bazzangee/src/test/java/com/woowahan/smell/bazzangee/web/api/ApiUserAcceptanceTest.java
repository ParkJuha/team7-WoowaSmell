package com.woowahan.smell.bazzangee.web.api;

import com.woowahan.smell.bazzangee.domain.User;
import com.woowahan.smell.bazzangee.dto.UserJoinDto;
import com.woowahan.smell.bazzangee.dto.UserLoginDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiUserAcceptanceTest extends AcceptanceTest {
    private UserLoginDto userLoginDto;
    @Before
    public void setUp() throws Exception {
        userLoginDto =
                UserLoginDto.builder()
                .userId("serverwizard@naver.com")
                .password("password").build();
    }

    @Test
    public void 로그인_테스트() {
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", userLoginDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}