package com.ultrafit.ultrafit.controller;

import com.ultrafit.ultrafit.model.Member;
import com.ultrafit.ultrafit.service.MemberService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return (member == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member created = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member updated = memberService.updateMember(id, member);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Member> patchMember(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Member patched = memberService.patchMember(id, updates);
        return ResponseEntity.ok(patched);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
