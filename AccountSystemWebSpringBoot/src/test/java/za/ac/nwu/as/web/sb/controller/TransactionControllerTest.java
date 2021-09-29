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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.dto.TransactionDto;
import za.ac.nwu.as.domain.service.GeneralResponse;
import za.ac.nwu.as.logic.flow.CurrencyFlow;
import za.ac.nwu.as.logic.flow.FetchTransactionFlow;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class TransactionControllerTest {

    private static final String BASE_URL = "/account-system/mvc";
    private static final String TRANSACTION_CONTROLLER_URL = BASE_URL + "/transaction";

    @Mock
    private FetchTransactionFlow fetchTransactionFlow;

    @InjectMocks
    private TransactionController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findTransactionById() throws Exception {
        String expectedOutput = "{\"payload\":{\"transactionId\":2,\"transactionDate\":[2021,9,12]," +
                "\"transactionChange\":\"+30\",\"member\":null},\"successful\":true}";

        TransactionDto transactionDto = new TransactionDto(2, LocalDate.of(2021, Month.SEPTEMBER, 12),
                "+30");

        when(fetchTransactionFlow.findTransactionById(2)).thenReturn(transactionDto);

        MvcResult mvcResult = mockMvc.perform(get(String.format("%s/%s", TRANSACTION_CONTROLLER_URL, "/2"))
                .servletPath(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchTransactionFlow, times(1)).findTransactionById(2);
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }
}