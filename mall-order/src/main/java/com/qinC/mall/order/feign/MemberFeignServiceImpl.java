package com.qinC.mall.order.feign;

import com.qinC.mall.order.vo.MemberAddressVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberFeignServiceImpl implements MemberFeignService {

    @Override
    public List<MemberAddressVo> getAddress(Long memberId) {
        return null;
    }

}
