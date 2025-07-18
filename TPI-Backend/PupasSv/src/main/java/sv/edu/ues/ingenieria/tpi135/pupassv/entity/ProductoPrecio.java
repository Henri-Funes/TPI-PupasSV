/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.ingenieria.tpi135.pupassv.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author samuel
 */
@Entity
@Table(name = "producto_precio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoPrecio.findAll", query = "SELECT p FROM ProductoPrecio p"),
    @NamedQuery(name = "ProductoPrecio.findByIdProductoPrecio", query = "SELECT p FROM ProductoPrecio p WHERE p.idProductoPrecio = :idProductoPrecio"),
    @NamedQuery(name = "ProductoPrecio.findByFechaDesde", query = "SELECT p FROM ProductoPrecio p WHERE p.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "ProductoPrecio.findByFechaHasta", query = "SELECT p FROM ProductoPrecio p WHERE p.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "ProductoPrecio.findByPrecioSugerido", query = "SELECT p FROM ProductoPrecio p WHERE p.precioSugerido = :precioSugerido"),
        @NamedQuery(name = "ProductoPrecio.findByIdPrecioProducto",
                query = "SELECT p FROM ProductoPrecio p WHERE p.idProducto.idProducto = :idProducto ORDER BY p.fechaDesde DESC"),
        @NamedQuery(name = "ProductoPrecio.findProductoPrecioByIdProducto",
                query = "SELECT p FROM ProductoPrecio p WHERE p.idProducto.idProducto = :idProducto "
                        + "AND (p.fechaHasta IS NULL OR p.fechaHasta >= CURRENT_DATE) "
                        + "ORDER BY p.fechaDesde DESC"),
        @NamedQuery(name = "ProductoPrecio.findByIdProducto",
                query = "SELECT p FROM ProductoPrecio p WHERE p.idProducto.idProducto = :idProducto "
                        + "ORDER BY p.fechaDesde DESC"),
        @NamedQuery(name = "ProductoPrecio.deleteRelacionPrecio",
                query = "DELETE FROM ProductoPrecio p WHERE p.idProducto.idProducto = :idProducto"),
        @NamedQuery(name = "OrdenDetalle.deleteByProductoPrecioProducto",
                query = "DELETE FROM OrdenDetalle od WHERE od.productoPrecio.idProducto.idProducto = :idProducto"),
        @NamedQuery(name = "ProductoPrecio.findCurrentByProducto",
                query = "SELECT p FROM ProductoPrecio p WHERE p.idProducto.idProducto = :idProducto "
                        + "AND (p.fechaHasta IS NULL OR p.fechaHasta >= CURRENT_DATE) "
                        + "ORDER BY p.fechaDesde DESC")})
public class ProductoPrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_precio")
    private Long idProductoPrecio;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_sugerido")
    private BigDecimal precioSugerido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoPrecio")
    @JsonBackReference("hyy")
    private List<OrdenDetalle> ordenDetalleList;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne
    @JsonBackReference //Evita la recursividad
    private Producto idProducto;

    public ProductoPrecio() {
    }

    public ProductoPrecio(Long idProductoPrecio) {
        this.idProductoPrecio = idProductoPrecio;
    }

    public Long getIdProductoPrecio() {
        return idProductoPrecio;
    }

    public void setIdProductoPrecio(Long idProductoPrecio) {
        this.idProductoPrecio = idProductoPrecio;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public BigDecimal getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(BigDecimal precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    @XmlTransient
    public List<OrdenDetalle> getOrdenDetalleList() {
        return ordenDetalleList;
    }

    public void setOrdenDetalleList(List<OrdenDetalle> ordenDetalleList) {
        this.ordenDetalleList = ordenDetalleList;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoPrecio != null ? idProductoPrecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPrecio)) {
            return false;
        }
        ProductoPrecio other = (ProductoPrecio) object;
        if ((this.idProductoPrecio == null && other.idProductoPrecio != null) || (this.idProductoPrecio != null && !this.idProductoPrecio.equals(other.idProductoPrecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.ingenieria.tpi135.pupassv.entity.ProductoPrecio[ idProductoPrecio=" + idProductoPrecio + " ]";
    }
    
}
