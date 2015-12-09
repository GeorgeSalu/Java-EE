package br.com.devmedia.consultorioee.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(catalog = "odontoenterprise", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"img_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagem.findAll", query = "SELECT i FROM Imagem i"),
    @NamedQuery(name = "Imagem.findByImgId", query = "SELECT i FROM Imagem i WHERE i.imgId = :imgId"),
    @NamedQuery(name = "Imagem.findByImgDescricao", query = "SELECT i FROM Imagem i WHERE i.imgDescricao = :imgDescricao"),
    @NamedQuery(name = "Imagem.findByImgdataInclusao", query = "SELECT i FROM Imagem i WHERE i.imgdataInclusao = :imgdataInclusao"),
    @NamedQuery(name = "Imagem.findByImghoraInclusao", query = "SELECT i FROM Imagem i WHERE i.imghoraInclusao = :imghoraInclusao")})
public class Imagem implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id", nullable = false)
    @XmlElement
    private Integer imgId;
    @Basic(optional = false)
    @Column(name = "img_descricao", nullable = false, length = 255)
    @XmlElement
    private String imgDescricao;
    @Basic(optional = false)
    @Column(name = "img_dataInclusao", nullable = false)
    @Temporal(TemporalType.DATE)
    @XmlTransient
    private Date imgdataInclusao;
    @Basic(optional = false)
    @Column(name = "img_horaInclusao", nullable = false)
    @Temporal(TemporalType.TIME)
    @XmlTransient
    private Date imghoraInclusao;
    @JoinColumn(name = "img_categoria", referencedColumnName = "cig_id", nullable = false)
    @ManyToOne(optional = false)
    @XmlTransient
    private Categoriaimagem imgCategoria;
    @JoinColumn(name = "img_orcamento", referencedColumnName = "orc_id", nullable = false)
    @ManyToOne(optional = false)
    @XmlTransient
    private Orcamento imgOrcamento;
    @Basic(optional = false)
    @Column(name = "img_imagem", nullable = false)
    @Lob
    @XmlTransient
    private byte[] imgImagem;

    public byte[] getImgImagem() {
        return imgImagem;
    }

    public void setImgImagem(byte[] imgImagem) {
        this.imgImagem = imgImagem;
    }

    
    
    public Imagem() {
    }

    public Imagem(Integer imgId) {
        this.imgId = imgId;
    }

    public Imagem(Integer imgId, String imgDescricao, Date imgdataInclusao, Date imghoraInclusao, Categoriaimagem imgCategoria, Orcamento imgOrcamento) {
        this.imgId = imgId;
        this.imgDescricao = imgDescricao;
        this.imgdataInclusao = imgdataInclusao;
        this.imghoraInclusao = imghoraInclusao;
        this.imgCategoria = imgCategoria;
        this.imgOrcamento = imgOrcamento;
    }
    
    

    public Imagem(Integer imgId, String imgDescricao, Date imgdataInclusao, Date imghoraInclusao) {
        this.imgId = imgId;
        this.imgDescricao = imgDescricao;
        this.imgdataInclusao = imgdataInclusao;
        this.imghoraInclusao = imghoraInclusao;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImgDescricao() {
        return imgDescricao;
    }

    public void setImgDescricao(String imgDescricao) {
        this.imgDescricao = imgDescricao;
    }

    public Date getImgdataInclusao() {
        return imgdataInclusao;
    }

    public void setImgdataInclusao(Date imgdataInclusao) {
        this.imgdataInclusao = imgdataInclusao;
    }

    public Date getImghoraInclusao() {
        return imghoraInclusao;
    }

    public void setImghoraInclusao(Date imghoraInclusao) {
        this.imghoraInclusao = imghoraInclusao;
    }

    public Categoriaimagem getImgCategoria() {
        return imgCategoria;
    }

    public void setImgCategoria(Categoriaimagem imgCategoria) {
        this.imgCategoria = imgCategoria;
    }

    public Orcamento getImgOrcamento() {
        return imgOrcamento;
    }

    public void setImgOrcamento(Orcamento imgOrcamento) {
        this.imgOrcamento = imgOrcamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imgId != null ? imgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagem)) {
            return false;
        }
        Imagem other = (Imagem) object;
        if ((this.imgId == null && other.imgId != null) || (this.imgId != null && !this.imgId.equals(other.imgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.devmedia.consultorioee.entities.Imagem[ imgId=" + imgId + " ]";
    }

}
