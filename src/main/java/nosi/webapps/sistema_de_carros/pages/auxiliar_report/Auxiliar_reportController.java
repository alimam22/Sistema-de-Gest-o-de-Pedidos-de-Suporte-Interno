package nosi.webapps.sistema_de_carros.pages.auxiliar_report;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.List; //block import
import nosi.webapps.sistema_de_carros.dao.TblVenda; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import java.time.LocalDate; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Auxiliar_reportController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Auxiliar_report();
		model.load();
		var view = new Auxiliar_reportView();
		/* Start-Code-Block (index) */
	try{
	
		TblVenda tblvenda = new TblVenda().findOne(Core.toInt(Core.getReportParam("id_venda")));
				model.setComprador(tblvenda.getComprador());
			model.setData(Core.convertLocalDateToString(tblvenda.getDataVenda(), Core.DD_MM_YYYY));
			model.setVendedor(tblvenda.getVendedor());
			model.setMarca(tblvenda.getIdCarroFk().getIdModeloFk().getIdMarcaFk().getNomeMarca());
	
	}catch(Exception e){
	e.printStackTrace();
	}
	/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}