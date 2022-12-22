package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMapping rm;

    @Override
    public void init() throws ServletException {
        rm = new RequestMapping();
        rm.initMapping();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("Method : {}, Request URI : {}, ServletPath : {}",
                request.getMethod(), request.getRequestURI(), request.getServletPath());
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();

        System.out.println("-----------");
        
        // URL 중 servletPath에 대응되는 controller를 구함
        Controller controller = rm.findController(servletPath);
        try {
            // controller를 통해 request 처리 후, 이동할 uri를 반환 받음
        	System.out.println("-----------");
            String uri = controller.execute(request, response);

            System.out.println("-----------");
            if (uri == null) return;	// Ajax request 처리 완료
            
            System.out.println("-----------");

            // 반환된 uri에 따라 forwarding 또는 redirection 여부를 결정하고 이동
            if (uri.startsWith("redirect:")) {
            	System.out.println("-----------");
                // redirection 지시
                String targetUri = contextPath + uri.substring("redirect:".length());
                System.out.println("-----------");
                response.sendRedirect(targetUri);	// redirect to url
                System.out.println("-----------");
            }
            else {
            	System.out.println("-----------");
                // forwarding 수행
                String targetUri = "/WEB-INF" + uri;
                System.out.println("-----------");
                RequestDispatcher rd = request.getRequestDispatcher(targetUri);
                System.out.println("-----------");
                rd.forward(request, response);		// forward to the view page
                System.out.println("-----------");
            }
        } catch (Exception e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }
}

