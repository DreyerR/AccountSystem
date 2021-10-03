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
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.logic.flow.FetchMemberFlow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    private static final String BASE_URL = "/account-system/mvc";
    private static final String MEMBER_CONTROLLER_URL = BASE_URL + "/members";

    @Mock
    private FetchMemberFlow fetchMemberFlow;

    @InjectMocks
    private MemberController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void fetchAll() throws Exception {
        String expectedOutput = "{\"payload\":[{\"firstName\":\"Rudi\",\"lastName\":\"Dreyer\"," +
                "\"dob\":[2000,5,24],\"email\":\"rudidreyer7@gmail.com\",\"contactNr\":\"0767869466\"}]," +
                "\"successful\":true}";

        List<MemberDto> memberDtoList = new ArrayList<>();
        memberDtoList.add(new MemberDto("Rudi", "Dreyer", LocalDate.of(2000, 5, 24),
                "rudidreyer7@gmail.com", "0767869466"));

        when(fetchMemberFlow.fetchAllMembers()).thenReturn(memberDtoList);
        MvcResult mvcResult = mockMvc.perform(get(MEMBER_CONTROLLER_URL)
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchMemberFlow, times(1)).fetchAllMembers();
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void fetchById() throws Exception {
        String expectedOutput = "{\"payload\":{\"firstName\":\"Rudi\",\"lastName\":\"Dreyer\"," +
                "\"dob\":[2000,5,24],\"email\":\"rudidreyer7@gmail.com\",\"contactNr\":\"0767869466\"}," +
                "\"successful\":true}";

        MemberDto memberDto = new MemberDto("Rudi", "Dreyer", LocalDate.of(2000, 5, 24),
                "rudidreyer7@gmail.com", "0767869466");

        when(fetchMemberFlow.findMemberById(anyInt())).thenReturn(memberDto);
        assertNotNull(memberDto);
        MvcResult mvcResult = mockMvc.perform(get(String.format("%s%s", MEMBER_CONTROLLER_URL, "/3"))
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(fetchMemberFlow, times(1)).findMemberById(anyInt());
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void fetchByIdIfNull() throws Exception {
        String expectedOutput = "{\"payload\":null,\"successful\":true}";

        when(fetchMemberFlow.findMemberById(anyInt())).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(get(String.format("%s%s", MEMBER_CONTROLLER_URL, "/8"))
                        .servletPath(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(fetchMemberFlow, times(1)).findMemberById(anyInt());
        assertEquals(expectedOutput, mvcResult.getResponse().getContentAsString());
    }
}