/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.controller.PreguntaJpaController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Ususario
 */
public class crearEncuestaCC implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        PreguntaJpaController conPregunta = new PreguntaJpaController(); 
        HttpSession session = request.getSession();
        session.setAttribute("listpreguntas", conPregunta.findPreguntaEntities());        
        String url = "/WEB-INF/vista/comiteCentral/encuesta/crear.jsp";
        return url;
    }
    
}
