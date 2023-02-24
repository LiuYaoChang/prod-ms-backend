package io.renren.modules.app.service;

import org.springframework.stereotype.Service;

import io.renren.common.utils.R;
import io.renren.modules.app.config.WechatConfig;

public interface WechatTokenService {
  
  // 获取微信Access token
  public R getAccessToken(WechatConfig config);
}
