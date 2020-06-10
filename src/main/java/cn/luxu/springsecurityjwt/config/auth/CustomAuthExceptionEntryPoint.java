package cn.luxu.springsecurityjwt.config.auth;

import cn.luxu.springsecurityjwt.response.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class CustomAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper jacksonObjectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        R<String> result = new R<>();
        result.setCode(HttpStatus.UNAUTHORIZED.value());
        if (e != null) {
            result.setMsg("error");
            result.setDate(e.getMessage());
        }
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.append(jacksonObjectMapper.writeValueAsString(result));
    }
}
