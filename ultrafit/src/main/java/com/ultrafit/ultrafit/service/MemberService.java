package com.ultrafit.ultrafit.service;

import com.ultrafit.ultrafit.model.Member;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {
    //With this class we are cona operate in the Member.java

    private final Map<Long, Member> members = new HashMap<>();//first we need to obtain the keys of the members and put it in a hasMap
    //here the id is the key
    private Long nextId = 1L; //sum that start's in 1 and increments with the creations of new members

    //This function gonna get back all the members
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values()); //Ordenated list
    }
    //Also we can get just one member, putting in the parameters of the funcion, the id of the member.
    public Member getMemberById(Long id) {
        return members.get(id);
    }
    //we create a new member in the HashMap, so first of all we need to increment the nextId, and after that put in the HashMap
    //with the id of the member, the member that we want to add
    public Member createMember(Member member) {
        member.setId(nextId++);
        members.put(member.getId(), member);
        return member;
    }
    //If we want to replace, so edit the member we need to reput it in the HashMap, the same thing that we do in "createMember"
    public Member updateMember(Long id, Member updatedMember) {
        updatedMember.setId(id);
        members.put(id, updatedMember);
        return updatedMember;
    }
    //this funcion do a partial update of the member. It returns the member but modified
    public Member patchMember(Long id, Map<String, Object> updates) {
        //we define the member and we get the id
        Member member = members.get(id);
        if (member == null) return null;
        //if we need to update the name of the memfer 
        if (updates.containsKey("name")) {
            member.setName((String) updates.get("name"));
        }
        //maybe the appelido
        if (updates.containsKey("surname")) {
            member.setSurname((String) updates.get("surname"));
        }
        //maybe the email or the phone
        if (updates.containsKey("email")) {
            member.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("phone")) {
            member.setPhone((String) updates.get("phone"));
        }

        return member;
    }


    //the function that we are gonna use to delete the Member
    
    public void deleteMember(Long id) {
        members.remove(id);
    }
}
