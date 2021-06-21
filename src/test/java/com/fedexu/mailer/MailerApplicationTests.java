package com.fedexu.mailer;

import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MailerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SendGrid sendGridClient;

    @Mock
    private SendGridHelper sendGridHelper;

    @Test
    void shouldSendMail() throws Exception {
        Response response = new Response();
        response.setStatusCode(202);

        when(sendGridClient.api(any())).thenReturn(response);

        mockMvc.perform(post("/api/mail").contentType(MediaType.APPLICATION_JSON)
                .content(fromFile("okMail.json"))).andExpect(status().isOk())
				.andExpect(content().string(containsString("202")));
    }

	@Test
	void shouldSendMailHtmlInBody() throws Exception {
		Response response = new Response();
		response.setStatusCode(202);

		when(sendGridClient.api(any())).thenReturn(response);

		mockMvc.perform(post("/api/mail").contentType(MediaType.APPLICATION_JSON)
				.content(fromFile("okBodyMail.json"))).andExpect(status().isOk())
				.andExpect(content().string(containsString("202")));
	}

	@Test
	void shouldthrowExceptionOnBadFormatBody() throws Exception {
		Response response = new Response();
		response.setStatusCode(425);

		when(sendGridClient.api(any())).thenReturn(response);

		mockMvc.perform(post("/api/mail").contentType(MediaType.APPLICATION_JSON)
				.content(fromFile("koBodyMail.json"))).andExpect(status().is(425));
	}

	@Test
	void shouldthrowExceptionOnBadFormatSubject() throws Exception {
		Response response = new Response();
		response.setStatusCode(425);

		when(sendGridClient.api(any())).thenReturn(response);

		mockMvc.perform(post("/api/mail").contentType(MediaType.APPLICATION_JSON)
				.content(fromFile("koSubjectMail.json"))).andExpect(status().is(425));
	}



    @SneakyThrows
    private byte[] fromFile(String path) {
        return new ClassPathResource(path).getInputStream().readAllBytes();
    }

}
