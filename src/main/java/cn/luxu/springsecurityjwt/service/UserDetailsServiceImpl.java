package cn.luxu.springsecurityjwt.service;

import lombok.AllArgsConstructor;
import cn.luxu.springsecurityjwt.entity.TRole;
import cn.luxu.springsecurityjwt.entity.TUser;
import cn.luxu.springsecurityjwt.mapper.TRoleMapper;
import cn.luxu.springsecurityjwt.mapper.TUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private TUserMapper tUserMapper;
    private TRoleMapper tRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查数据库
        TUser user = tUserMapper.loadUserByUsername(s);
        if (null != user) {
            List<TRole> roles = tRoleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(roles);
        }else {
            throw new UsernameNotFoundException("用户名不对");
        }
        return user;
    }
}
