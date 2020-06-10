package cn.luxu.springsecurityjwt.mapper;

import cn.luxu.springsecurityjwt.entity.TRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-04-03
 */
public interface TRoleMapper extends BaseMapper<TRole> {

    List<TRole> getRolesByUserId(Integer id);
}
