package cn.luxu.springsecurityjwt.config.auth;

import cn.luxu.springsecurityjwt.response.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 拒绝访问处理器（登录状态下，无权限会触发）
 */
@Component
@AllArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper jacksonObjectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        R<String> result = new R<>();
        result.setCode(HttpStatus.UNAUTHORIZED.value());
        if (accessDeniedException != null) {
            result.setMsg("UNAUTHORIZED");
            result.setDate(accessDeniedException.getMessage());
        }
        response.getWriter().write(jacksonObjectMapper.writeValueAsString(result));
    }
}
