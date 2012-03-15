/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.controller.CaracteristicaJpaController;
import model.Action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Ususario
 */
public class CrearFactorCC implements Action {
 
    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {
        CaracteristicaJpaController conCaracteristica = new CaracteristicaJpaController(); 
        HttpSession session = request.getSession();
        session.setAttribute("listcaracteristicas", conCaracteristica.findCaracteristicaEntities());
        String url = "/WEB-INF/vista/comiteCentral/factor/crear.jsp";

        return url;
    }
}
