package com.memorybox.config;


import com.memorybox.constant.SocialLoginType;

import org.modelmapper.Converters;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SocialLoginTypeConverter implements Converters.Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase());
    }
}
