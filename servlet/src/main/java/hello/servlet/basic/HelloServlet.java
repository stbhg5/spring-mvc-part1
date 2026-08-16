package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); // 단축기 soutm
        System.out.println("request = " + request); // 단축기 soutv
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 쿼리 파라미터 값 조회
        System.out.println("username = " + username);

        // 헤더
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // euc-kr 쓰면 안됨 : 옛날 시스템 아니면 utf-8 쓰기
        // 바디
        response.getWriter().write("hello " + username); // HTTP 메시지 바디에 데이터 넣기
    }

}