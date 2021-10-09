package za.ac.nwu.as.translator.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.repo.persistence.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberTranslatorImplTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberTranslatorImpl translator;

    Member member;

    @Before
    public void setUp() {
        member = new Member("Rudi", "Dreyer", LocalDate.of(2000, 5, 24),
                "rudidreyer7@gmail.com", "0824563218");
    }

    @Test
    public void fetchAllMembers() {
        List<Member> memberList = new ArrayList<>();
        memberList.add(member);

        when(memberRepository.findAll()).thenReturn(memberList);
        List<MemberDto> members = translator.fetchAllMembers();
        assertEquals(memberList.size(), members.size());
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    public void fetchMemberById() {
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
        MemberDto memberDto = translator.fetchMemberById(1);
        assertNotNull(memberDto);
        verify(memberRepository, times(1)).findById(1);
    }

    @Test
    public void fetchMemberByIdPersist() {
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
        Member testMember = translator.fetchMemberByIdPersist(1);
        assertNotNull(testMember);
        verify(memberRepository, times(1)).findById(1);
    }
}