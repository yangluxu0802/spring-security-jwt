package cn.luxu.springsecurityjwt.mapper;

import org.apache.ibatis.annotations.Param;
import cn.luxu.springsecurityjwt.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-04-03
 */
public interface TUserMapper extends BaseMapper<TUser> {

    TUser loadUserByUsername(@Param("username") String username);

}
