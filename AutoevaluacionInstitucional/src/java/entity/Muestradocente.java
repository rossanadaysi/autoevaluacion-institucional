/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "muestradocente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestradocente.findAll", query = "SELECT m FROM Muestradocente m"),
    @NamedQuery(name = "Muestradocente.findById", query = "SELECT m FROM Muestradocente m WHERE m.id = :id")})
public class Muestradocente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Docente docenteId;
    @JoinColumn(name = "muestra_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestra muestraId;

    public Muestradocente() {
    }

    public Muestradocente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Docente getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Docente docenteId) {
        this.docenteId = docenteId;
    }

    public Muestra getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Muestra muestraId) {
        this.muestraId = muestraId;
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
        if (!(object instanceof Muestradocente)) {
            return false;
        }
        Muestradocente other = (Muestradocente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Muestradocente[ id=" + id + " ]";
    }

}
