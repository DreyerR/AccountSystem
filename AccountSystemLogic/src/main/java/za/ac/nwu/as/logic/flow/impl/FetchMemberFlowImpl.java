package za.ac.nwu.as.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.logic.flow.FetchMemberFlow;
import za.ac.nwu.as.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class FetchMemberFlowImpl implements FetchMemberFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchMemberFlowImpl.class);

    private final MemberTranslator memberTranslator;

    @Autowired
    public FetchMemberFlowImpl(MemberTranslator memberTranslator) {
        this.memberTranslator = memberTranslator;
    }

    @Override
    public List<MemberDto> fetchAllMembers() {
        LOGGER.info("Fetching all the members");
        return memberTranslator.fetchAllMembers();
    }

    @Override
    public MemberDto findMemberById(Integer id) {
        LOGGER.info("Fetching member ID {}", id);
        return memberTranslator.fetchMemberById(id);
    }
}
