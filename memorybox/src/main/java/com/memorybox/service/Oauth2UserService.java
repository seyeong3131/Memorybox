package com.memorybox.service;

import com.memorybox.constant.Role;
import com.memorybox.constant.SocialLoginType;
import com.memorybox.entity.Member;
import com.memorybox.repository.MemberRepository;
import com.memorybox.repository.PrincipalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired private MemberRepository memberRepository;

    private final HttpServletResponse response;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();    //google
        String providerId = oAuth2User.getAttribute("sub");
        String username = oAuth2User.getAttribute("email");  			// 사용자가 입력한 적은 없지만 만들어준다

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);  // 사용자가 입력한 적은 없지만 만들어준다

        String email = oAuth2User.getAttribute("email");
        Role role = Role.USER;

        Member byUsername = memberRepository.findByName(username);

        //DB에 없는 사용자라면 회원가입처리
        if(byUsername == null){
            byUsername = Member.oauth2Register()
                    .name(username).password(password).email(email).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            memberRepository.save(byUsername);
        }

        return new PrincipalRepository(byUsername, oAuth2User.getAttributes());
    }


//    public void request(SocialLoginType socialLoginType) {
//        String redirectURL;
//        switch (socialLoginType) {
//            case GOOGLE: {
//                redirectURL = googleOauth.getOauthRedirectURL();
//            } break;
//            default: {
//                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
//            }
//        }
//        try {
//            response.sendRedirect(redirectURL);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
