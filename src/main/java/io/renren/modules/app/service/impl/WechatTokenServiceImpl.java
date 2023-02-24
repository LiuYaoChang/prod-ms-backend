package io.renren.modules.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.renren.common.utils.R;
import io.renren.modules.app.config.Constants;
import io.renren.modules.app.config.WechatConfig;
import io.renren.modules.app.redis.AppRedisConfig;
// import io.renren.modules.app.config.WechatConfig;
import io.renren.modules.app.service.WechatTokenService;
import io.renren.modules.app.vo.WechatAccessToken;

@Service
public class WechatTokenServiceImpl implements WechatTokenService {

  @Autowired
  AppRedisConfig redisConfig;

  @Override
  public R getAccessToken(WechatConfig config) {

    WechatAccessToken tokenInfo = redisConfig.get(Constants.WECHAT_ACCESS_TOKEN_REDIS_KEY);
    if (tokenInfo != null && !StringUtils.isEmpty(tokenInfo.getAccess_token())) {
      return R.ok().put("data", tokenInfo);
    } else {
      return getAccessTokenFromRemote(config);
    }
  }


  public R getAccessTokenFromRemote(WechatConfig config) {
    RestTemplate restTemplate = new RestTemplate();
    //        WechatConfig config = new WechatConfig();
    System.out.println("sss" + config);
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";
    Map<String, String> query = new HashMap<>();
    //        query.put("id", "666106231640");
    query.put("grant_type", "client_credential");
    query.put("appid", config.getAppId());
    query.put("secret", config.getSecret());
    WechatAccessToken res = restTemplate.getForObject(url, WechatAccessToken.class, query);
    redisConfig.saveOrUpdate(res);
    return R.ok().put("data", res);
  }
  
}
