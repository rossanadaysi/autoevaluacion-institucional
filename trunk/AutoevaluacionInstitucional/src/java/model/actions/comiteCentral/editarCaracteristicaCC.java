/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.controller.CaracteristicaJpaController;
import entity.controller.FactorJpaController;
import entity.controller.IndicadorJpaController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Ususario
 */
public class editarCaracteristicaCC implements Action{
    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        
        CaracteristicaJpaController conCaracteristica = new CaracteristicaJpaController(); 
        FactorJpaController conFactor = new FactorJpaController(); 
        IndicadorJpaController conIndi = new IndicadorJpaController(); 
        HttpSession session = request.getSession();
        String idcaracteristica = request.getParameter("idC");
        
        
        session.setAttribute("caracteristica",conCaracteristica.findCaracteristica(Integer.parseInt(idcaracteristica)));
        session.setAttribute("listindicadores", conIndi.findIndicadorEntities());
        session.setAttribute("listfactores", conFactor.findFactorEntities());
        
        
        String url = "/WEB-INF/vista/comiteCentral/caracteristica/editar.jsp";
        return url;
    }
    
}
