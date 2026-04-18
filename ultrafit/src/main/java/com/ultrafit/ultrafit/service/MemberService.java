package com.ultrafit.ultrafit.service;

import com.ultrafit.ultrafit.model.Member;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {

    private final Map<Long, Member> members = new HashMap<>();
    private Long nextId = 1L;

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public Member getMemberById(Long id) {
        return members.get(id);
    }

    public Member createMember(Member member) {
        member.setId(nextId++);
        members.put(member.getId(), member);
        return member;
    }

    public Member updateMember(Long id, Member updatedMember) {
        updatedMember.setId(id);
        members.put(id, updatedMember);
        return updatedMember;
    }

    public Member patchMember(Long id, Map<String, Object> updates) {
        Member member = members.get(id);
        if (member == null) return null;

        if (updates.containsKey("name")) {
            member.setName((String) updates.get("name"));
        }
        if (updates.containsKey("surname")) {
            member.setSurname((String) updates.get("surname"));
        }
        if (updates.containsKey("email")) {
            member.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("phone")) {
            member.setPhone((String) updates.get("phone"));
        }

        return member;
    }

    public void deleteMember(Long id) {
        members.remove(id);
    }
}
