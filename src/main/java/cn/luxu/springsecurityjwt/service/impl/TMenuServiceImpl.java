package cn.luxu.springsecurityjwt.service.impl;

import cn.luxu.springsecurityjwt.entity.TMenu;
import cn.luxu.springsecurityjwt.mapper.TMenuMapper;
import cn.luxu.springsecurityjwt.service.ITMenuService;
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
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements ITMenuService {

}
