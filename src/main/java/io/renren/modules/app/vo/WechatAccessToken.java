package io.renren.modules.app.vo;

import lombok.Data;


@Data
public class WechatAccessToken {
    private String access_token;
    private int expires_in;
    private Long errcode;
    private String errmsg;

}

