package za.ac.nwu.as.translator;

import za.ac.nwu.as.domain.dto.MemberDto;
import za.ac.nwu.as.domain.persistence.Member;

import java.util.List;

public interface MemberTranslator {
    List<MemberDto> fetchAllMembers();
    MemberDto fetchMemberById(Integer id);
    Member fetchMemberByIdPersist(Integer id);
}
