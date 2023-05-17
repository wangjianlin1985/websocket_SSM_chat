// 
// 
// 

package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import service.LoginService;
import org.springframework.stereotype.Controller;

@Controller
public class Login
{
    @Autowired
    LoginService loginservice;
    
    @RequestMapping({ "/loginvalidate" })
    public String loginvalidate(@RequestParam("username") final String username, @RequestParam("pic") final String pic, @RequestParam("password") final String pwd, final HttpSession httpSession) {
        try {
            final String picode = (String)httpSession.getAttribute("rand");
            if (!picode.equalsIgnoreCase(pic)) {
                return "failcode";
            }
            if (username == null) {
                return "login";
            }
            final String realpwd = this.loginservice.getpwdbyname(username);
            if (realpwd != null && pwd.equals(realpwd)) {
                final long uid = this.loginservice.getUidbyname(username);
                httpSession.setAttribute("username", (Object)username);
                httpSession.setAttribute("uid", (Object)uid);
                return "chatroom";
            }
            return "fail";
        }
        catch (Exception e) {
            return "login";
        }
    }
    
    @RequestMapping({ "/login" })
    public String login() {
        return "login";
    }
    
    @RequestMapping({ "/" })
    public String index() {
        return "login";
    }
    
    @RequestMapping({ "/logout" })
    public String logout(final HttpSession httpSession) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("uid");
        return "login";
    }
}
