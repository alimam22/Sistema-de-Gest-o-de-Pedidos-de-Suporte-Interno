package nosi.webapps.sistema_de_carros.pages.carro_lookup_;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import java.time.LocalDate; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Carro_lookup_Controller extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Carro_lookup_();
		model.load();
		var view = new Carro_lookup_View();
		view.id_carro.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT 'Mollit adipiscing laudantium d' as marca,'Totam aliqua stract natus volu' as modelo,'Stract laudantium voluptatem s' as data_registro,'hidden-a375_6546' as id_carro "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
		TblCarro tblcarrofilter = new TblCarro().find();
	List<TblCarro> tblcarroList = tblcarrofilter.all();
	List<Carro_lookup_.Table_1> tblcarroTable = new ArrayList<>();
	if(Core.isNotNull(tblcarroList)){
		for(TblCarro tblcarro : tblcarroList){
			Carro_lookup_.Table_1 row  = new Carro_lookup_.Table_1();
			row.setMarca(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca());
			row.setModelo(tblcarro.getIdModeloFk().getNomeModelo());
			row.setData_registro(Core.convertLocalDateToString(tblcarro.getDataRegistro(), Core.DD_MM_YYYY));
			row.setId_carro(tblcarro.getIdCarro().toString());
			tblcarroTable.add(row);
		}
		model.setTable_1(tblcarroTable);
	}
	}catch(Exception e){
		e.printStackTrace();
	}/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}