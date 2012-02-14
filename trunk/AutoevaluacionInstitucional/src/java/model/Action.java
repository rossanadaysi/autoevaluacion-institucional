/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Estudiante
 */

public interface Action {

    public String procesar( HttpServletRequest request )
            throws IOException, ServletException;
}
