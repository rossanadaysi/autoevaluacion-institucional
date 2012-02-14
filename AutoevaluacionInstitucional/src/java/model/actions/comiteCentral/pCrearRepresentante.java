/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import connection.exceptions.PreexistingEntityException;
import controller.sqlController;
import entity.Persona;
import entity.Privilegio;
import entity.Representante;
import entity.Representantehasprivilegio;
import entity.controller.PersonaJpaController;
import entity.controller.PrivilegioJpaController;
import entity.controller.ProgramaJpaController;
import entity.controller.RepresentanteJpaController;
import entity.controller.RepresentantehasprivilegioJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class pCrearRepresentante implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {


        String url = "confirmation.jsp";
        HttpSession session = request.getSession();
        ProgramaJpaController conPrograma = new ProgramaJpaController();
        PrivilegioJpaController conPivilegio = new PrivilegioJpaController();
        RepresentanteJpaController conRepresentante = new RepresentanteJpaController();
        PersonaJpaController conPersona = new PersonaJpaController();
        RepresentantehasprivilegioJpaController conReprePrivi = new RepresentantehasprivilegioJpaController();
        sqlController conSql = new sqlController();

        try {
            Persona persona = new Persona();
            Representante representante = new Representante();
            Representantehasprivilegio repre_privi = new Representantehasprivilegio();

            persona.setId(Integer.parseInt(request.getParameter("cedula")));
            persona.setNombre(request.getParameter("nombres"));
            persona.setApellido(request.getParameter("apellidos"));
            persona.setPassword(request.getParameter("password"));
            persona.setMail(request.getParameter("mail"));


            conPersona.create(persona);


            representante.setPersonaId(persona);
            representante.setProgramaId(conPrograma.findPrograma(Integer.parseInt(request.getParameter("programas"))));
            representante.setRol(request.getParameter("rol"));
            conRepresentante.create(representante);

            List<Privilegio> listPriviegios = conPivilegio.findPrivilegioEntities();
            for (Privilegio p : listPriviegios) {
                if (request.getParameter(p.getNombre()).equals("1")) {
                    System.out.println(p.getNombre());
                    repre_privi.setPrivilegioId(p);
                    repre_privi.setRepresentanteId(representante);
                    conReprePrivi.create(repre_privi);
                }
            }


        } catch (PreexistingEntityException ex) {
        } catch (Exception ex) {
        }

        session.setAttribute("listrepresentantes", conRepresentante.findRepresentanteEntities());

        session.setAttribute("mensaje", "Representante Registrado con Exito!!");
        session.setAttribute("action", "indexCC");


        return url;
    }
}
