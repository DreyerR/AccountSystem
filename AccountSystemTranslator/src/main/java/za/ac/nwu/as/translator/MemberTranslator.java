package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.MemberDto;

import java.util.List;

public interface MemberTranslator {
    List<MemberDto> fetchAllMembers();
}
