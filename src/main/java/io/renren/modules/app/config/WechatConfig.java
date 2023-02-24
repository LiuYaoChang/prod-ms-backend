package io.renren.modules.app.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Data
@ConfigurationProperties(prefix = "wechat.public")
public class WechatConfig {

    // 公众号APPID
    private String appId;
    // 公众号secret
    private String secret;
    // 公众号token
    private String token;


}

