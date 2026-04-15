package nosi.webapps.sistema_gestao_aluno.pages.historico_de_pagamentos;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Historico_de_pagamentosView extends View {

	public Field mes_de_referencia;
	public Field form_1_number_1;
	public Field metodo_de_pagamento;
	public Field data;
	public Field n_recibo;
	public IGRPForm form_1;

	public IGRPButton btn_salvar;

	public Historico_de_pagamentosView(){

		this.setPageTitle("Histórico de Pagamentos");
			
		form_1 = new IGRPForm("form_1","");

		mes_de_referencia = new TextField(model,"mes_de_referencia");
		mes_de_referencia.setLabel(gt("Mês de Referência"));
		mes_de_referencia.propertie().add("name","p_mes_de_referencia").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		form_1_number_1 = new NumberField(model,"form_1_number_1");
		form_1_number_1.setLabel(gt("Number"));
		form_1_number_1.propertie().add("name","p_form_1_number_1").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");
		
		metodo_de_pagamento = new TextField(model,"metodo_de_pagamento");
		metodo_de_pagamento.setLabel(gt("Método de Pagamento:"));
		metodo_de_pagamento.propertie().add("name","p_metodo_de_pagamento").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		data = new DateField(model,"data");
		data.setLabel(gt("Data"));
		data.propertie().add("name","p_data").add("type","date").add("range","false").add("disableWeekends","false").add("disabledBeforetoday","false").add("daysoff","false").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false").add("class","primary");
		
		n_recibo = new TextField(model,"n_recibo");
		n_recibo.setLabel(gt("Nº Recibo"));
		n_recibo.propertie().add("name","p_n_recibo").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		


		btn_salvar = new IGRPButton("Salvar","sistema_gestao_aluno","Historico_de_pagamentos","salvar","_blank","success|fa-save","","");
		btn_salvar.propertie.add("id","button_2943_cf64").add("type","form").add("class","success").add("rel","salvar").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(mes_de_referencia);
		form_1.addField(form_1_number_1);
		form_1.addField(metodo_de_pagamento);
		form_1.addField(data);
		form_1.addField(n_recibo);

		form_1.addButton(btn_salvar);
		this.addToPage(form_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		mes_de_referencia.setValue(model);
		form_1_number_1.setValue(model);
		metodo_de_pagamento.setValue(model);
		data.setValue(model);
		n_recibo.setValue(model);	

		}
}