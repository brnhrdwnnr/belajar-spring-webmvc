package bernhard.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HeaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void headerOk() throws Exception {
        mockMvc.perform(
                get("/header/token")
                        .header("X-TOKEN", "Bernhard")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("OK"))
        );

    }

    @Test
    public void headerKo() throws Exception {
        mockMvc.perform(
                get("/header/token")
                        .header("X-TOKEN", "Winner")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("KO"))
        );

    }
}
