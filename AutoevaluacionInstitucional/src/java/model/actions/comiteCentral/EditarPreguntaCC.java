/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.controller.IndicadorJpaController;
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
public class EditarPreguntaCC implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        PreguntaJpaController conPregunta = new PreguntaJpaController(); 
        HttpSession session = request.getSession();
        String idpregunta = request.getParameter("idP");
        IndicadorJpaController conIndicador = new IndicadorJpaController();
        
        session.setAttribute("pregunta",conPregunta.findPregunta(Integer.parseInt(idpregunta)));
        session.setAttribute("listindicadores", conIndicador.findIndicadorEntities());
        
        String url = "/WEB-INF/vista/comiteCentral/pregunta/editar.jsp";
        return url;
    }
    
}
