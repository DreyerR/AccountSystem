package za.ac.nwu.as.web.sb.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CurrencyFlow;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    private static final String BASE_URL = "/account-system/mvc";
    private static final String CURRENCY_CONTROLLER_URL = BASE_URL + "/currency";

    @Mock
    private CurrencyFlow currencyFlow;

    @InjectMocks
    private CurrencyController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addCurrency() throws Exception {
        String expectedOutput = "{\"payload\":\"Successfully added 5 currency\",\"successful\":true}";

        GeneralResponse<String> generalResponse = new GeneralResponse<>(true,
                "Successfully added 5 currency");

        when(currencyFlow.addCurrency(1, BigDecimal.valueOf(5))).thenReturn(generalResponse);

        MvcResult mvcResult = mockMvc.perform(put(String.format("%s/%s", CURRENCY_CONTROLLER_URL, "add/1"))
                .param("amount", "5")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(currencyFlow, times(1)).addCurrency(eq(1), eq(BigDecimal.valueOf(5)));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void subtractCurrency() {
    }
}