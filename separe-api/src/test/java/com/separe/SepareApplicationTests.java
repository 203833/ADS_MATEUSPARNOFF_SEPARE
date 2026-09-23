package com.separe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.data.mongodb.uri=mongodb://localhost:27017/separe-test",
        "app.jwt.secret=TestSecretKeyParaTestes2026ComMaisDe256BitsDeSeguranca!!"
})
class SepareApplicationTests {

    @Test
    void contextLoads() {
    }
}
