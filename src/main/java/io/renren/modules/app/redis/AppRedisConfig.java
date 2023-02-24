package io.renren.modules.app.redis;

import io.renren.common.utils.RedisKeys;
import io.renren.common.utils.RedisUtils;
import io.renren.modules.app.config.Constants;
import io.renren.modules.app.config.WechatConfig;
import io.renren.modules.app.vo.WechatAccessToken;
import io.renren.modules.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置Redis
 *
 * @author Mark sunlightcs@gmail.com
 */
@Component
public class AppRedisConfig {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(WechatAccessToken config) {
        if(config == null){
            return ;
        }
        String key = Constants.WECHAT_ACCESS_TOKEN_REDIS_KEY;
        // String key = RedisKeys.getSysConfigKey(config.getParamKey());
        redisUtils.set(key, config, config.getExpires_in());
    }

    public void delete(String configKey) {
        // String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(configKey);
    }

    public WechatAccessToken get(String configKey) {
        // String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(configKey, WechatAccessToken.class);
    }
}

