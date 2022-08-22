package com.memorybox.entity;

import com.memorybox.constant.Role;
import com.memorybox.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(unique = true,length = 20)
    private String nick;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider; // oauth2 어떤 플랫폼
    private String providerId; // oauth2 아이디값


    @Builder(builderClassName = "MemberRegister", builderMethodName = "memberRegister")
    public Member(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        this.name = memberFormDto.getName();
        this.nick = memberFormDto.getNick();
        this.password = passwordEncoder.encode(memberFormDto.getPassword());
        this.email = memberFormDto.getEmail();
        this.role = Role.ADMIN;
    }

//
//    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
//        Member member = new Member();
//        member.setName(memberFormDto.getName());
//        member.setNick(memberFormDto.getNick());
//        member.setEmail(memberFormDto.getEmail());
//        String password = passwordEncoder.encode(memberFormDto.getPassword());
//        member.setPassword(password);
//        member.setRole(Role.ADMIN);
//        return member;
//    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String name, String password, String email, Role role, String provider, String providerId){
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }
}
