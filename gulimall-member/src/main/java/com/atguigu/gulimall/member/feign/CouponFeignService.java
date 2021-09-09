package com.atguigu.gulimall.member.feign;

import com.atguigu.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 远程调用gulimall-coupon微服务
 *
 * @author lojoyo
 * @email lojoyo@yeah.net
 * @date 2021-09-02 15:46:04
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();

}
