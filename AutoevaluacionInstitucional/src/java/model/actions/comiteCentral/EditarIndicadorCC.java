/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.controller.CaracteristicaJpaController;
import entity.controller.IndicadorJpaController;
import entity.controller.InstrumentoJpaController;
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
public class EditarIndicadorCC implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        IndicadorJpaController conIndicador = new IndicadorJpaController(); 
        CaracteristicaJpaController conCaracteristica = new CaracteristicaJpaController(); 
        InstrumentoJpaController conInst = new InstrumentoJpaController();
        PreguntaJpaController conPregunta = new PreguntaJpaController(); 
        HttpSession session = request.getSession();
        String idindicador = request.getParameter("idI");
        
        
        session.setAttribute("indicador",conIndicador.findIndicador(Integer.parseInt(idindicador)));
        session.setAttribute("instrumentos",conInst.findInstrumentoEntities());
        session.setAttribute("listpreguntas", conPregunta.findPreguntaEntities());
        session.setAttribute("listcaracteristicas", conCaracteristica.findCaracteristicaEntities());
        
        
        String url = "/WEB-INF/vista/comiteCentral/indicador/editar.jsp";
        return url;
    }
    
}
