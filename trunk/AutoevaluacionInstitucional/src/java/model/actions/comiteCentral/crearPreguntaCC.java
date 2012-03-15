/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import model.Action;

/**
 *
 * @author Ususario
 */
public class crearPreguntaCC implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {

        String url = "/WEB-INF/vista/comiteCentral/pregunta/crear.jsp";
        return url;
    }
    
}
