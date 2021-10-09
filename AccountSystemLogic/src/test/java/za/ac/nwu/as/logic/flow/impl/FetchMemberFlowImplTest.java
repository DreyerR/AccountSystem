package za.ac.nwu.as.logic.flow.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.translator.MemberTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FetchMemberFlowImplTest {

    @Mock
    private MemberTranslator memberTranslator;

    @InjectMocks
    private FetchMemberFlowImpl flow;

    MemberDto memberDto;

    @Before
    public void setUp() {
        memberDto = new MemberDto(
                "Rudi",
                "Dreyer",
                LocalDate.of(2000, 5, 24),
                "rudidreyer7@gmail.com",
                "0824569531"
        );
    }

    @Test
    public void fetchAllMembers() {
        List<MemberDto> memberDtoList = new ArrayList<>();
        memberDtoList.add(memberDto);

        when(memberTranslator.fetchAllMembers()).thenReturn(memberDtoList);
        List<MemberDto> output = flow.fetchAllMembers();
        assertEquals(memberDtoList.size(), output.size());
        verify(memberTranslator, times(1)).fetchAllMembers();
    }

    @Test
    public void findMemberById() {
        when(memberTranslator.fetchMemberById(anyInt())).thenReturn(memberDto);
        MemberDto output = flow.findMemberById(anyInt());
        assertNotNull(output);
        verify(memberTranslator, times(1)).fetchMemberById(anyInt());
    }
}