/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vanesa
 */
@Entity
@Table(name = "representantehasprivilegio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Representantehasprivilegio.findAll", query = "SELECT r FROM Representantehasprivilegio r"),
    @NamedQuery(name = "Representantehasprivilegio.findById", query = "SELECT r FROM Representantehasprivilegio r WHERE r.id = :id")})
public class Representantehasprivilegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "privilegio_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Privilegio privilegioId;
    @JoinColumn(name = "representante_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Representante representanteId;

    public Representantehasprivilegio() {
    }

    public Representantehasprivilegio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Privilegio getPrivilegioId() {
        return privilegioId;
    }

    public void setPrivilegioId(Privilegio privilegioId) {
        this.privilegioId = privilegioId;
    }

    public Representante getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Representante representanteId) {
        this.representanteId = representanteId;
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
        if (!(object instanceof Representantehasprivilegio)) {
            return false;
        }
        Representantehasprivilegio other = (Representantehasprivilegio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Representantehasprivilegio[ id=" + id + " ]";
    }
    
}
