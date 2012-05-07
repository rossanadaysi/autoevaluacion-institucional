/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "resultadoevaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultadoevaluacion.findAll", query = "SELECT r FROM Resultadoevaluacion r"),
    @NamedQuery(name = "Resultadoevaluacion.findByIdResultadoEvaluacion", query = "SELECT r FROM Resultadoevaluacion r WHERE r.idResultadoEvaluacion = :idResultadoEvaluacion"),
    @NamedQuery(name = "Resultadoevaluacion.findByRespuesta", query = "SELECT r FROM Resultadoevaluacion r WHERE r.respuesta = :respuesta")})
public class Resultadoevaluacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idResultadoEvaluacion")
    private Integer idResultadoEvaluacion;
    @Column(name = "respuesta")
    private String respuesta;
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pregunta preguntaId;
    @JoinColumn(name = "encabezado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encabezado encabezadoId;

    public Resultadoevaluacion() {
    }

    public Resultadoevaluacion(Integer idResultadoEvaluacion) {
        this.idResultadoEvaluacion = idResultadoEvaluacion;
    }

    public Integer getIdResultadoEvaluacion() {
        return idResultadoEvaluacion;
    }

    public void setIdResultadoEvaluacion(Integer idResultadoEvaluacion) {
        this.idResultadoEvaluacion = idResultadoEvaluacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Pregunta getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Pregunta preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Encabezado getEncabezadoId() {
        return encabezadoId;
    }

    public void setEncabezadoId(Encabezado encabezadoId) {
        this.encabezadoId = encabezadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultadoEvaluacion != null ? idResultadoEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoevaluacion)) {
            return false;
        }
        Resultadoevaluacion other = (Resultadoevaluacion) object;
        if ((this.idResultadoEvaluacion == null && other.idResultadoEvaluacion != null) || (this.idResultadoEvaluacion != null && !this.idResultadoEvaluacion.equals(other.idResultadoEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Resultadoevaluacion[ idResultadoEvaluacion=" + idResultadoEvaluacion + " ]";
    }

}
