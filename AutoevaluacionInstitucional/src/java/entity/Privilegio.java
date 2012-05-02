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
@Table(name = "privilegio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p"),
    @NamedQuery(name = "Privilegio.findById", query = "SELECT p FROM Privilegio p WHERE p.id = :id"),
    @NamedQuery(name = "Privilegio.findByNombre", query = "SELECT p FROM Privilegio p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Privilegio.findByDescripcion", query = "SELECT p FROM Privilegio p WHERE p.descripcion = :descripcion")})
public class Privilegio implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privilegioId")
    private List<Representantehasprivilegio> representantehasprivilegioList;

    public Privilegio() {
    }

    public Privilegio(Integer id) {
        this.id = id;
    }

    public Privilegio(Integer id, String nombre) {
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
    public List<Representantehasprivilegio> getRepresentantehasprivilegioList() {
        return representantehasprivilegioList;
    }

    public void setRepresentantehasprivilegioList(List<Representantehasprivilegio> representantehasprivilegioList) {
        this.representantehasprivilegioList = representantehasprivilegioList;
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
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = (Privilegio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Privilegio[ id=" + id + " ]";
    }
    
}
