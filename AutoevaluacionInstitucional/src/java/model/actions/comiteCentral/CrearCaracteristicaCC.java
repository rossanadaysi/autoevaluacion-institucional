/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;
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
public class CrearCaracteristicaCC implements Action{

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        IndicadorJpaController conIndicador = new IndicadorJpaController(); 
        FactorJpaController conFactor = new FactorJpaController(); 
        HttpSession session = request.getSession();
        session.setAttribute("listindicadores", conIndicador.findIndicadorEntities());
        session.setAttribute("listfactores", conFactor.findFactorEntities());
        String url = "/WEB-INF/vista/comiteCentral/caracteristica/crear.jsp";

        return url;
    }
    
}
