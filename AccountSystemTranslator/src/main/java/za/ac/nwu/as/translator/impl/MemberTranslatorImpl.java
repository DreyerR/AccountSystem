package za.ac.nwu.as.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.persistence.Member;
import za.ac.nwu.as.repo.persistence.MemberRepository;
import za.ac.nwu.as.translator.MemberTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberTranslatorImpl implements MemberTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberTranslatorImpl.class);
    private final MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDto> fetchAllMembers() {
        List<MemberDto> memberDtos = new ArrayList<>();
        try {
            for (Member member : memberRepository.findAll()) {
                memberDtos.add(new MemberDto(member));
            }
        }
        catch (Exception e) {
            LOGGER.error("MemberTranslator: Unable to read from database, {}", e.getMessage());
            throw new RuntimeException("MemberTranslator: Unable to read from database");
        }
        return memberDtos;
    }

    @Override
    public MemberDto fetchMemberById(Integer id) {
        try {
            Member member = memberRepository.findById(id).orElse(null);
            return member != null ? new MemberDto(member) : null;
        }
        catch (Exception e) {
            LOGGER.error("MemberTranslator: Unable to find member by ID, {}", e.getMessage());
            throw new RuntimeException("MemberTranslator: Unable to find member by ID");
        }
    }

    @Override
    public Member fetchMemberByIdPersist(Integer id) {
        try {
            return memberRepository.findById(id).orElse(null);
        }
        catch (Exception e) {
            LOGGER.error("MemberTranslator: Unable to find member by ID, {}", e.getMessage());
            throw new RuntimeException("MemberTranslator: Unable to find member by ID");
        }
    }
}
