/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.logIn;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class cerrarSesion implements Action{

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
       
        session.invalidate();

        
        String url = "index.jsp";

        return url;
    }
}
