/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;
import entity.controller.CaracteristicaJpaController;
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
public class CrearIndicadorCC implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        PreguntaJpaController conPregunta = new PreguntaJpaController(); 
        InstrumentoJpaController conIns = new InstrumentoJpaController();
        CaracteristicaJpaController conCaracteristica = new CaracteristicaJpaController(); 
        HttpSession session = request.getSession();
        session.setAttribute("listpreguntas", conPregunta.findPreguntaEntities());        
        session.setAttribute("instrumentos", conIns.findInstrumentoEntities());   
        session.setAttribute("listcaracteristicas", conCaracteristica.findCaracteristicaEntities());        
        String url = "/WEB-INF/vista/comiteCentral/indicador/crear.jsp";
        return url;
    }
    
}
