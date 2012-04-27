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
 * @author Ususario
 */
@Entity
@Table(name = "sectorempresarial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sectorempresarial.findAll", query = "SELECT s FROM Sectorempresarial s"),
    @NamedQuery(name = "Sectorempresarial.findById", query = "SELECT s FROM Sectorempresarial s WHERE s.id = :id"),
    @NamedQuery(name = "Sectorempresarial.findByNombre", query = "SELECT s FROM Sectorempresarial s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sectorempresarial.findByDescripcion", query = "SELECT s FROM Sectorempresarial s WHERE s.descripcion = :descripcion")})
public class Sectorempresarial implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectorempresarialId")
    private List<Empleador> empleadorList;

    public Sectorempresarial() {
    }

    public Sectorempresarial(Integer id) {
        this.id = id;
    }

    public Sectorempresarial(Integer id, String nombre) {
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

    @XmlTransient
    public List<Empleador> getEmpleadorList() {
        return empleadorList;
    }

    public void setEmpleadorList(List<Empleador> empleadorList) {
        this.empleadorList = empleadorList;
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
        if (!(object instanceof Sectorempresarial)) {
            return false;
        }
        Sectorempresarial other = (Sectorempresarial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sectorempresarial[ id=" + id + " ]";
    }
    
}
