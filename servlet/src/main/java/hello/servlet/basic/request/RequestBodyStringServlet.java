package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 스프링이 제공하는 유틸리티 StreamUtils
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // byte 를 문자로 바꿀 때 어떤 인코딩인지 알려줘야 함 (문자 -> byte 도 마찬가지)

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }

}