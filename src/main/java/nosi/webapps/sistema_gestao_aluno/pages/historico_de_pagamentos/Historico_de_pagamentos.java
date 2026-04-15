package nosi.webapps.sistema_gestao_aluno.pages.historico_de_pagamentos;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

import nosi.core.validator.constraints.*;

public class Historico_de_pagamentos extends Model{		

	@NotNull()
	@RParam(rParamName = "p_mes_de_referencia")
	private String mes_de_referencia;

	@RParam(rParamName = "p_form_1_number_1")
	private Integer form_1_number_1;

	@NotNull()
	@RParam(rParamName = "p_metodo_de_pagamento")
	private String metodo_de_pagamento;

	@RParam(rParamName = "p_data")
	private String data;

	@NotNull()
	@RParam(rParamName = "p_n_recibo")
	private String n_recibo;
	
	public void setMes_de_referencia(String mes_de_referencia){
		this.mes_de_referencia = mes_de_referencia;
	}
	public String getMes_de_referencia(){
		return this.mes_de_referencia;
	}
	
	public void setForm_1_number_1(Integer form_1_number_1){
		this.form_1_number_1 = form_1_number_1;
	}
	public Integer getForm_1_number_1(){
		return this.form_1_number_1;
	}
	
	public void setMetodo_de_pagamento(String metodo_de_pagamento){
		this.metodo_de_pagamento = metodo_de_pagamento;
	}
	public String getMetodo_de_pagamento(){
		return this.metodo_de_pagamento;
	}
	
	public void setData(String data){
		this.data = data;
	}
	public String getData(){
		return this.data;
	}
	
	public void setN_recibo(String n_recibo){
		this.n_recibo = n_recibo;
	}
	public String getN_recibo(){
		return this.n_recibo;
	}



}