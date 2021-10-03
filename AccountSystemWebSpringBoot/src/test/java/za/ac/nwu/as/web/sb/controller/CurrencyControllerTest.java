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
import za.ac.nwu.as.domain.dto.CurrencyDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.FetchCurrencyFlow;
import za.ac.nwu.as.logic.flow.ModifyCurrencyFlow;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    private static final String BASE_URL = "/account-system/mvc";
    private static final String CURRENCY_CONTROLLER_URL = BASE_URL + "/currency";

    @Mock
    private ModifyCurrencyFlow modifyCurrencyFlow;
    @Mock
    private FetchCurrencyFlow fetchCurrencyFlow;

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

        when(modifyCurrencyFlow.addCurrency(1, BigDecimal.valueOf(5))).thenReturn(generalResponse);

        MvcResult mvcResult = mockMvc.perform(put(String.format("%s/%s", CURRENCY_CONTROLLER_URL, "add/1"))
                .param("amount", "5")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(modifyCurrencyFlow, times(1)).addCurrency(eq(1), eq(BigDecimal.valueOf(5)));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void subtractCurrency() throws Exception {
        String expectedOutput = "{\"payload\":\"Successfully subtracted 15 currency\",\"successful\":true}";

        GeneralResponse<String> generalResponse = new GeneralResponse<>(true,
                "Successfully subtracted 15 currency");

        when(modifyCurrencyFlow.subtractCurrency(1, BigDecimal.valueOf(15))).thenReturn(generalResponse);
        MvcResult mvcResult = mockMvc.perform(put(String.format("%s/%s", CURRENCY_CONTROLLER_URL, "sub/1"))
                        .param("amount", "15")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(modifyCurrencyFlow, times(1)).subtractCurrency(eq(1), eq(BigDecimal.valueOf(15)));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void fetchCurrencyById() throws Exception {
        String expectedOutput = "{\"payload\":{\"currencyAmount\":50,\"currencyTypeName\":\"RAND\"," +
                "\"memberDto\":null},\"successful\":true}";

        CurrencyDto currencyDto = new CurrencyDto(BigDecimal.valueOf(50), "RAND");
        when(fetchCurrencyFlow.fetchCurrencyById(3)).thenReturn(currencyDto);

        MvcResult mvcResult = mockMvc.perform(get(String.format("%s/%s", CURRENCY_CONTROLLER_URL, "/3"))
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchCurrencyFlow, times(1)).fetchCurrencyById(eq(3));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void fetchCurrencyByMemberId() throws Exception {
        String expectedOutput = "{\"payload\":{\"currencyAmount\":15,\"currencyTypeName\":\"MILES\"," +
                "\"memberDto\":null},\"successful\":true}";

        CurrencyDto currencyDto = new CurrencyDto(BigDecimal.valueOf(15), "MILES");
        when(fetchCurrencyFlow.fetchCurrencyByMemberId(1)).thenReturn(currencyDto);

        MvcResult mvcResult = mockMvc.perform(get(CURRENCY_CONTROLLER_URL)
                        .param("memberId", "1")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchCurrencyFlow, times(1)).fetchCurrencyByMemberId(eq(1));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateCurrencyTypesByName() throws Exception {
        String expectedOutput = "{\"payload\":\"Successfully updated currency types from RAND to MILES\"," +
                "\"successful\":true}";

        when(modifyCurrencyFlow.updateCurrencyTypes("RAND", "MILES")).thenReturn(1);
        MvcResult mvcResult = mockMvc.perform(put(String.format("%s%s", CURRENCY_CONTROLLER_URL, "/currency-type"))
                        .param("fromCT", "RAND")
                        .param("toCT", "MILES")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(modifyCurrencyFlow, times(1)).updateCurrencyTypes("RAND", "MILES");
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateCurrencyTypesByNameIfMethodNameNotFound() throws Exception {
        String expectedOutput = "{\"payload\":\"One or more currency type names could not be found (method)\"," +
                "\"successful\":false}";

        when(modifyCurrencyFlow.updateCurrencyTypes("RAND", "MILES")).thenReturn(-1);
        MvcResult mvcResult = mockMvc.perform(put(String.format("%s%s", CURRENCY_CONTROLLER_URL, "/currency-type"))
                        .param("fromCT", "RAND")
                        .param("toCT", "MILES")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(modifyCurrencyFlow, times(1)).updateCurrencyTypes("RAND", "MILES");
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateCurrencyTypesByNameIfDatabaseNameNotFound() throws Exception {
        String expectedOutput = "{\"payload\":\"Unable to update: One or more currency types not found (database)\"," +
                "\"successful\":false}";

        when(modifyCurrencyFlow.updateCurrencyTypes("RAND", "MILES")).thenReturn(0);
        MvcResult mvcResult = mockMvc.perform(put(String.format("%s%s", CURRENCY_CONTROLLER_URL, "/currency-type"))
                        .param("fromCT", "RAND")
                        .param("toCT", "MILES")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(modifyCurrencyFlow, times(1)).updateCurrencyTypes("RAND", "MILES");
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void fetchCurrencyByIdIfNull() throws Exception {
        String expectedOutput = "{\"payload\":null,\"successful\":true}";

        when(fetchCurrencyFlow.fetchCurrencyById(4)).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(get(String.format("%s/%s", CURRENCY_CONTROLLER_URL, "/4"))
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(fetchCurrencyFlow, times(1)).fetchCurrencyById(eq(4));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void fetchCurrencyByMemberIdIfNull() throws Exception {
        String expectedOutput = "{\"payload\":null,\"successful\":true}";

        when(fetchCurrencyFlow.fetchCurrencyByMemberId(2)).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(get(CURRENCY_CONTROLLER_URL)
                        .param("memberId", "2")
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(fetchCurrencyFlow, times(1)).fetchCurrencyByMemberId(eq(2));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }
}