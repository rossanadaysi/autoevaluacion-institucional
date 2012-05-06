/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.fuente;

import entity.controller.EncuestaJpaController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

public class ResponderEncuesta implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        EncuestaJpaController conEnc = new EncuestaJpaController();
        HttpSession session = request.getSession();
        String idencuesta = request.getParameter("idE");


        session.setAttribute("encuesta", conEnc.findEncuesta(Integer.parseInt(idencuesta)));
       String url = "/WEB-INF/vista/fuente/responderEncuesta.jsp";
        return url;
    }
}
