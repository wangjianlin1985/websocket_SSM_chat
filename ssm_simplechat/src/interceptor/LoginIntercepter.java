// 
// 
// 

package interceptor;

import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercepter extends HandlerInterceptorAdapter
{
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final String username = (String)request.getSession().getAttribute("username");
        if (username == null) {
            request.getRequestDispatcher("/WEB-INF/content/login.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return false;
        }
        return true;
    }
    
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
    }
    
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
    }
}
