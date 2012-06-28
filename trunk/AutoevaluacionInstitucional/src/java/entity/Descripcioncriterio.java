/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "descripcioncriterio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descripcioncriterio.findAll", query = "SELECT d FROM Descripcioncriterio d"),
    @NamedQuery(name = "Descripcioncriterio.findById", query = "SELECT d FROM Descripcioncriterio d WHERE d.id = :id"),
    @NamedQuery(name = "Descripcioncriterio.findByNombre", query = "SELECT d FROM Descripcioncriterio d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Descripcioncriterio.findByDescripcion", query = "SELECT d FROM Descripcioncriterio d WHERE d.descripcion = :descripcion")})
public class Descripcioncriterio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "criterio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Criterio criterioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descripcioncriterioId")
    private List<Muestracriterio> muestracriterioList;

    public Descripcioncriterio() {
    }

    public Descripcioncriterio(Integer id) {
        this.id = id;
    }

    public Descripcioncriterio(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Criterio getCriterioId() {
        return criterioId;
    }

    public void setCriterioId(Criterio criterioId) {
        this.criterioId = criterioId;
    }

    @XmlTransient
    public List<Muestracriterio> getMuestracriterioList() {
        return muestracriterioList;
    }

    public void setMuestracriterioList(List<Muestracriterio> muestracriterioList) {
        this.muestracriterioList = muestracriterioList;
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
        if (!(object instanceof Descripcioncriterio)) {
            return false;
        }
        Descripcioncriterio other = (Descripcioncriterio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Descripcioncriterio[ id=" + id + " ]";
    }
    
}
