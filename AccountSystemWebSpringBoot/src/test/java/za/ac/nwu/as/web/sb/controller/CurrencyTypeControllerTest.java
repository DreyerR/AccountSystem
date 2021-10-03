package za.ac.nwu.as.web.sb.controller;

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
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CreateCurrencyTypeFlow;
import za.ac.nwu.as.logic.flow.FetchCurrencyTypeFlow;
import za.ac.nwu.as.logic.flow.ModifyCurrencyTypeFlow;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyTypeControllerTest {

    private static final String BASE_URL = "/account-system/mvc";
    private static final String CT_CONTROLLER_URL = BASE_URL + "/currency-type";

    @Mock
    private FetchCurrencyTypeFlow fetchCurrencyTypeFlow;
    @Mock
    private CreateCurrencyTypeFlow createCurrencyTypeFlow;
    @Mock
    private ModifyCurrencyTypeFlow modifyCurrencyTypeFlow;

    @InjectMocks
    private CurrencyTypeController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void fetchAllCurrencyTypes() throws Exception {
        String expectedOutput = "{\"payload\":[{\"currencyTypeId\":1,\"currencyTypeName\":\"RAND\"}," +
                "{\"currencyTypeId\":2,\"currencyTypeName\":\"MILES\"}],\"successful\":true}";

        List<CurrencyTypeDto> currencyTypeDtoList = new ArrayList<>();
        currencyTypeDtoList.add(new CurrencyTypeDto(1, "RAND"));
        currencyTypeDtoList.add(new CurrencyTypeDto(2, "MILES"));

        when(fetchCurrencyTypeFlow.fetchAllCurrencyTypes()).thenReturn(currencyTypeDtoList);
        MvcResult mvcResult = mockMvc.perform(get(String.format("%s%s", CT_CONTROLLER_URL, "/all"))
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveCurrencyType() throws Exception {
        String expectedOutput = "{\"payload\":{\"currencyTypeId\":7,\"currencyTypeName\":\"TEST\"},\"successful\":true}";
        String currencyTypeToBeCreated = "{\"currencyTypeId\":7,\"currencyTypeName\":\"TEST\"}";

        CurrencyTypeDto currencyTypeDto = new CurrencyTypeDto(null, "TEST");
        when(createCurrencyTypeFlow.saveCurrencyType(eq(currencyTypeDto))).then(returnsFirstArg());

        MvcResult mvcResult = mockMvc.perform(post(CT_CONTROLLER_URL)
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(currencyTypeToBeCreated)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(createCurrencyTypeFlow, times(1)).saveCurrencyType(eq(currencyTypeDto));
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteCurrencyTypeById() throws Exception {
        String expectedOutput = "{\"payload\":\"Successfully deleted currency type with ID 4\",\"successful\":true}";

        MvcResult mvcResult = mockMvc.perform(delete(String.format("%s%s", CT_CONTROLLER_URL, "/delete/4"))
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(modifyCurrencyTypeFlow, times(1)).deleteCurrencyTypeById(4);
        assertEquals(expectedOutput, expectedOutput);
    }
}