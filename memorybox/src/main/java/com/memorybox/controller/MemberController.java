package com.memorybox.controller;

import com.memorybox.constant.Role;
import com.memorybox.dto.MemberFormDto;
import com.memorybox.entity.Member;
import com.memorybox.repository.MemberRepository;
import com.memorybox.repository.PrincipalRepository;
import com.memorybox.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private MemberRepository memberRepository;

//    @PostMapping(value = "/new")
//    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,
//                             Model model) {
//        if (bindingResult.hasErrors()) { // 각 변수명 체크 문제가 생기면 호출 이름 X 이메일 형식 X
//            return "member/memberForm";
//        }
//        try {
//            Member member = Member.createMember(memberFormDto, passwordEncoder);
//            memberService.saveMember(member);
//        } catch (IllegalStateException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "member/memberForm";
//        }
//        return "redirect:/";
//
//    }

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(@ModelAttribute Member member, @Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { // 각 변수명 체크 문제가 생기면 호출 이름 X 이메일 형식 X
            return "member/memberForm";
        }
        try {
            member.setRole(Role.USER);

            String encodePwd = bCryptPasswordEncoder.encode(member.getPassword());
            member.setPassword(encodePwd);

            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";

    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

    @GetMapping("/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);
        // PrincipalOauth2UserService의 getAttributes내용과 같음

        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
        // attributes == attributes1

        return attributes.toString();     //세션에 담긴 user가져올 수 있음음
    }


    @GetMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalRepository principalDetails){
        String result = "";

        PrincipalRepository principal = (PrincipalRepository) authentication.getPrincipal();
        if(principal.getMember().getProvider() == null) {
            result = result + "Form 로그인 : " + principal;
        }else{
            result = result + "OAuth2 로그인 : " + principal;
        }
        return result;
    }
}
