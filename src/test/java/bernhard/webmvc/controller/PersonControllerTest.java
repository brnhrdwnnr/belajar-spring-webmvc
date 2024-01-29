package bernhard.webmvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Bernhard")
                        .param("middleName", "Winner")
                        .param("lastName", "Manurung")
                        .param("email", "bernhardwinner@gmail.com")
                        .param("phone", "085774421909")
                        .param("address.street", "Kedoya")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "11111")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Gaming")
                        .param("socialMedias[0],name", "Instagram")
                        .param("socialMedias[0],location", "instagram.com/brnhrdwnnr")
                        .param("socialMedias[0],name", "Facebook")
                        .param("socialMedias[0],location", "facebook.com/brnhrdwnnr")

        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Bernhard Winner Manurung " +
                        "with email bernhardwinner@gmail.com and phone 085774421909 " +
                        "with address Kedoya, Jakarta, Indonesia, 11111"))
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Winner")
                        .param("lastName", "Manurung")
                        .param("address.street", "Kedoya")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "11111")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Gaming")
                        .param("socialMedias[0],name", "Instagram")
                        .param("socialMedias[0],location", "instagram.com/brnhrdwnnr")
                        .param("socialMedias[0],name", "Facebook")
                        .param("socialMedias[0],location", "facebook.com/brnhrdwnnr")

        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid request"))
        );
    }

}
