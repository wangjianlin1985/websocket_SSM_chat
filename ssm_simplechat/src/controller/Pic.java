// 
// 
// 

package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Pic
{
    @RequestMapping({ "/authImg" })
    public String getpic() {
        return "authImg";
    }
}
