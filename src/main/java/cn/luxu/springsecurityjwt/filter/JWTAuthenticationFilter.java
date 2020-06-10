package cn.luxu.springsecurityjwt.filter;

import cn.luxu.springsecurityjwt.constants.SecurityConstants;
import cn.luxu.springsecurityjwt.response.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import cn.luxu.springsecurityjwt.entity.TUser;
import cn.luxu.springsecurityjwt.util.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper jacksonObjectMapper;
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
        this.authenticationManager = authenticationManager;
        // 设置登录请求的 URL
        super.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        JsonNode jsonNode = getRequestBody(request);
        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password,null);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        R<String> result = new R<>(200,"LOGIN SUCCESS",null);
        TUser tUser = (TUser) authResult.getPrincipal();
        ArrayList<String> roles = new ArrayList<>();
        tUser.getAuthorities().forEach(role->roles.add(role.getAuthority()));
        String token = JwtUtil.createToken(tUser.getUsername(),roles,false);
        result.setDate(token);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(jacksonObjectMapper.writeValueAsString(result));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        R<String> result = new R<>(-1,"LOGIN FAIL",null);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(jacksonObjectMapper.writeValueAsString(result));
    }

    /**
     * 获取请求体
     */
    private JsonNode getRequestBody(HttpServletRequest request) throws AuthenticationException{
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = request.getInputStream();
            byte[] bs = new byte[StreamUtils.BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                stringBuilder.append(new String(bs, 0, len));
            }
            return jacksonObjectMapper.readTree(stringBuilder.toString());
        } catch (IOException e) {
            log.error("get request body error.");
        }
        throw new AuthenticationServiceException("invalid request body");
    }
}
