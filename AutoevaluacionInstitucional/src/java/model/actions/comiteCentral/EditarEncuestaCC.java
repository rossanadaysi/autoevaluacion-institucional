/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.Encuesta;
import entity.controller.EncuestaJpaController;
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
public class EditarEncuestaCC implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        
        EncuestaJpaController conEncuesta = new EncuestaJpaController(); 
        PreguntaJpaController conPregunta = new PreguntaJpaController(); 
        HttpSession session = request.getSession();
        String idencuesta = request.getParameter("id");
        
        
        Encuesta e =  conEncuesta.findEncuesta(Integer.parseInt(idencuesta));
        session.setAttribute("encuesta",e);
        session.setAttribute("listpreguntas", conPregunta.findPreguntaEntities());
        
        
        String url = "/WEB-INF/vista/comiteCentral/encuesta/editar.jsp";
        return url;
    }
    
}
