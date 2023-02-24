
package io.renren.modules.app.controller;
import io.renren.common.utils.R;
import io.renren.modules.app.config.WechatConfig;
import io.renren.modules.app.service.WechatTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/wechat")
public class WechatPUblicController {

    @Autowired
    WechatConfig config;

    @Autowired
    WechatTokenService wechatTokenService;

    @GetMapping("token")
    public R getAccessToken() {
        R res = wechatTokenService.getAccessToken(config);
        return res;
    }
}

