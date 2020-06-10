package cn.luxu.springsecurityjwt.service.impl;

import cn.luxu.springsecurityjwt.entity.TUser;
import cn.luxu.springsecurityjwt.mapper.TUserMapper;
import cn.luxu.springsecurityjwt.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2020-04-03
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
