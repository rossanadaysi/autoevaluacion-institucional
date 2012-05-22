/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "asignacionencuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignacionencuesta.findAll", query = "SELECT a FROM Asignacionencuesta a"),
    @NamedQuery(name = "Asignacionencuesta.findById", query = "SELECT a FROM Asignacionencuesta a WHERE a.id = :id")})
public class Asignacionencuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;
    @JoinColumn(name = "fuente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fuente fuenteId;
    @JoinColumn(name = "encuesta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encuesta encuestaId;

    public Asignacionencuesta() {
    }

    public Asignacionencuesta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
    }

    public Fuente getFuenteId() {
        return fuenteId;
    }

    public void setFuenteId(Fuente fuenteId) {
        this.fuenteId = fuenteId;
    }

    public Encuesta getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Encuesta encuestaId) {
        this.encuestaId = encuestaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignacionencuesta)) {
            return false;
        }
        Asignacionencuesta other = (Asignacionencuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Asignacionencuesta[ id=" + id + " ]";
    }

}
