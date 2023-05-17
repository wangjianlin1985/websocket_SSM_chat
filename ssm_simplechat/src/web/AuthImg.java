// 
// 
// 

package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.awt.Graphics;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = { "/WEB-INF/content/authImg.jsp" })
public class AuthImg extends HttpServlet
{
    private final Font mFont;
    private final int IMG_WIDTH = 100;
    private final int IMG_HEIGTH = 18;
    
    public AuthImg() {
        this.mFont = new Font("Arial Black", 0, 16);
    }
    
    private Color getRandColor(int fc, int bc) {
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        final BufferedImage image = new BufferedImage(100, 18, 1);
        final Graphics g = image.getGraphics();
        final Random random = new Random();
        g.setColor(this.getRandColor(200, 250));
        g.fillRect(1, 1, 99, 17);
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, 99, 17);
        g.setColor(this.getRandColor(160, 200));
        for (int i = 0; i < 30; ++i) {
            final int x = random.nextInt(99);
            final int y = random.nextInt(17);
            final int xl = random.nextInt(6) + 1;
            final int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl, y + yl);
        }
        g.setColor(this.getRandColor(160, 200));
        for (int i = 0; i < 30; ++i) {
            final int x = random.nextInt(99);
            final int y = random.nextInt(17);
            final int xl = random.nextInt(12) + 1;
            final int yl = random.nextInt(6) + 1;
            g.drawLine(x, y, x - xl, y - yl);
        }
        g.setFont(this.mFont);
        String sRand = "";
        for (int j = 0; j < 4; ++j) {
            final String tmp = this.getRandomChar();
            sRand = String.valueOf(sRand) + tmp;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(tmp, 15 * j + 10, 15);
        }
        final HttpSession session = request.getSession(true);
        session.setAttribute("rand", (Object)sRand);
        g.dispose();
        ImageIO.write(image, "JPEG", (OutputStream)response.getOutputStream());
    }
    
    private String getRandomChar() {
        final int rand = (int)Math.round(Math.random() * 2.0);
        long itmp = 0L;
        char ctmp = '\0';
        switch (rand) {
            case 1: {
                itmp = Math.round(Math.random() * 25.0 + 65.0);
                ctmp = (char)itmp;
                return String.valueOf(ctmp);
            }
            case 2: {
                itmp = Math.round(Math.random() * 25.0 + 97.0);
                ctmp = (char)itmp;
                return String.valueOf(ctmp);
            }
            default: {
                itmp = Math.round(Math.random() * 9.0);
                return new StringBuilder(String.valueOf(itmp)).toString();
            }
        }
    }
}
