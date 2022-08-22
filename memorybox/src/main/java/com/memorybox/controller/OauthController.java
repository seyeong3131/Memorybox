//package com.memorybox.controller;
//
//import com.memorybox.constant.SocialLoginType;
//import com.memorybox.service.OauthService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@RequiredArgsConstructor
//@RequestMapping(value = "/auth")
//@Slf4j
//public class OauthController {
//    private final OauthService oauthService;
//
//
////    /**
////     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
////     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
////     */
//    @GetMapping(value = "/{socialLoginType}")
//    public void socialLoginType(@PathVariable(name = "socialLoginType") SocialLoginType socialLoginType)
//    {
//        log.info(">> Console :: {} Social Login",socialLoginType);
//        oauthService.request(socialLoginType);
//    }
//}