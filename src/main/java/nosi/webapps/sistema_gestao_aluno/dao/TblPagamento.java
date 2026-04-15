package nosi.webapps.sistema_gestao_aluno.dao;

import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serial;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Column;
import jakarta.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;

/**
 * @author: Nositeste 2026-03-09
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "tbl_pagamento", schema = "public")
@NamedQuery(name = "TblPagamento.findAll", query = "SELECT t FROM TblPagamento t")
public class TblPagamento extends BaseActiveRecord<TblPagamento> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_PAGAMENTO";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pagamento", nullable = false, updatable = false)
 	private Integer idPagamento;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_matricula", foreignKey = @ForeignKey(name = "id_matricula_fk"), referencedColumnName="id_matricula")
	private TblMatricula idMatricula;
	@NotBlank
	@Size(max = 50)
	@Column(name = "mes_referencia")
	private String mesReferencia;
	@NotNull
	@Column(name = "valor_pago")
	private Double valorPago;
	@NotNull
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	@NotBlank
	@Column(name = "metodo_pagamento")
	private String metodoPagamento;
	@NotBlank
	@Column(name = "numero_recibo")
	private String numeroRecibo;

   public Integer getIdPagamento() {
      return this.idPagamento;
   }

   public void setIdPagamento(Integer idPagamento) {
      this.idPagamento = idPagamento;
   }

   public TblMatricula getIdMatricula() {
      return this.idMatricula;
   }

   public void setIdMatricula(TblMatricula idMatricula) {
      this.idMatricula = idMatricula;
   }

   public String getMesReferencia() {
      return this.mesReferencia;
   }

   public void setMesReferencia(String mesReferencia) {
      this.mesReferencia = mesReferencia;
   }

   public Double getValorPago() {
      return this.valorPago;
   }

   public void setValorPago(Double valorPago) {
      this.valorPago = valorPago;
   }

   public LocalDate getDataPagamento() {
      return this.dataPagamento;
   }

   public void setDataPagamento(LocalDate dataPagamento) {
      this.dataPagamento = dataPagamento;
   }

   public String getMetodoPagamento() {
      return this.metodoPagamento;
   }

   public void setMetodoPagamento(String metodoPagamento) {
      this.metodoPagamento = metodoPagamento;
   }

   public String getNumeroRecibo() {
      return this.numeroRecibo;
   }

   public void setNumeroRecibo(String numeroRecibo) {
      this.numeroRecibo = numeroRecibo;
   }

   public static final class Field {
      public static final String ID_PAGAMENTO = "idPagamento";
      public static final String ID_MATRICULA = "idMatricula";
      public static final String MES_REFERENCIA = "mesReferencia";
      public static final String VALOR_PAGO = "valorPago";
      public static final String DATA_PAGAMENTO = "dataPagamento";
      public static final String METODO_PAGAMENTO = "metodoPagamento";
      public static final String NUMERO_RECIBO = "numeroRecibo";

	  private Field() {}
	}
}