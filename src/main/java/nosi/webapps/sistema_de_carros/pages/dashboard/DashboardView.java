package nosi.webapps.sistema_de_carros.pages.dashboard;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class DashboardView extends View {

	public Field intervalo_de_data;
	public IGRPForm form_1;
	public IGRPChart chart_1;
	public IGRPChart chart_2;

	public IGRPButton btn_filtro;

	public DashboardView(){

		this.setPageTitle("DashBoard");
			
		form_1 = new IGRPForm("form_1","Monitorização de Venda");

		chart_1 = new IGRPChart("chart_1","Carros Vendido por Modelo");

		chart_2 = new IGRPChart("chart_2","Marca mais produzida");

		intervalo_de_data = new DateField(model,"intervalo_de_data");
		intervalo_de_data.setLabel(gt("Intervalo de Data"));
		intervalo_de_data.propertie().add("name","p_intervalo_de_data").add("type","date").add("range","true").add("disableWeekends","false").add("disabledBeforetoday","false").add("daysoff","false").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false").add("class","primary");
		


		btn_filtro = new IGRPButton("Filtro","sistema_de_carros","Dashboard","filtro","submit_ajax","primary|fa-filter","","");
		btn_filtro.propertie.add("id","button_352c_de95").add("type","form").add("class","primary").add("rel","filtro").add("refresh_components","chart_1,chart_2");

		
		chart_1.setCaption("");
		chart_1.setChart_type("column");
		chart_1.setXaxys("Eixo de X");
		chart_1.setYaxys("Eixo de Y");
		chart_1.setUrl("#");
		//ex: chart_1.addColor("#b23a0c").addColor("#1456e9").addColor("#4a7d9a").addColor("#16f83e");

		chart_2.setCaption("");
		chart_2.setChart_type("pie");
		chart_2.setXaxys("Eixo de X");
		chart_2.setYaxys("Eixo de Y");
		chart_2.setUrl("#");
		//ex: chart_2.addColor("#2d57fa").addColor("#a2a7cf").addColor("#ca88d5").addColor("#baa32d");

	}
		
	@Override
	public void render(){
		
		form_1.addField(intervalo_de_data);



		form_1.addButton(btn_filtro);
		this.addToPage(form_1);
		this.addToPage(chart_1);
		this.addToPage(chart_2);
	}
		
	@Override
	public void setModel(Model model) {
		
		intervalo_de_data.setValue(model);	

		chart_1.loadModel(((Dashboard) model).getChart_1());
		chart_2.loadModel(((Dashboard) model).getChart_2());
		}
}