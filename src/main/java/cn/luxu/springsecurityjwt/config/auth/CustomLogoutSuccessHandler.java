package cn.luxu.springsecurityjwt.config.auth;

import cn.luxu.springsecurityjwt.response.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper jacksonObjectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        R<String> result = new R<>();
        result.setCode(201);
        result.setMsg("logout success");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(jacksonObjectMapper.writeValueAsString(result));
    }
}
