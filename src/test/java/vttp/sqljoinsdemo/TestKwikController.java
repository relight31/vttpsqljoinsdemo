package vttp.sqljoinsdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class TestKwikController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturn200() {
        // build the request
        RequestBuilder req = MockMvcRequestBuilders.get("/purchase_order/wilm2022")
                .accept(MediaType.TEXT_HTML);

        // call controller with mock request
        try {
            MvcResult result = mvc.perform(req).andReturn();
            MockHttpServletResponse resp = result.getResponse();
            assertEquals(resp.getStatus(), 200);
        } catch (Exception e) {
            fail("MockMvc unable to get result", e);
            return; // don't forget to return to exit test
        }
    }
}
