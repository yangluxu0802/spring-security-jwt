package cn.luxu.springsecurityjwt.service.impl;

import cn.luxu.springsecurityjwt.entity.TRole;
import cn.luxu.springsecurityjwt.mapper.TRoleMapper;
import cn.luxu.springsecurityjwt.service.ITRoleService;
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
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements ITRoleService {

}
