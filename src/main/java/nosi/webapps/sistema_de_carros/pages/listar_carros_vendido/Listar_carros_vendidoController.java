package nosi.webapps.sistema_de_carros.pages.listar_carros_vendido;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import
import nosi.core.webapp.Report; //block import
import nosi.webapps.sistema_de_carros.dao.TblVenda; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Listar_carros_vendidoController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Listar_carros_vendido();
		model.load();
		var view = new Listar_carros_vendidoView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT 'Dolor rem anim ut sed' as marca,'Accusantium officia ut adipisc' as modelo,'Doloremque stract labore molli' as comprador,'hidden-6d2f_b9e3' as id_venda "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
		TblVenda tblvendafilter = new TblVenda().find();
	List<TblVenda> tblvendaList = tblvendafilter.all();
	List<Listar_carros_vendido.Table_1> tblvendaTable = new ArrayList<>();
	if(Core.isNotNull(tblvendaList)){
		for(TblVenda tblvenda : tblvendaList){
			Listar_carros_vendido.Table_1 row  = new Listar_carros_vendido.Table_1();
			row.setMarca(tblvenda.getIdCarroFk().getIdModeloFk().getIdMarcaFk().getNomeMarca());
			row.setModelo(tblvenda.getIdCarroFk().getIdModeloFk().getNomeModelo());
			row.setComprador(tblvenda.getComprador());
			row.setId_venda(tblvenda.getIdVenda().toString());
			tblvendaTable.add(row);
		}
		model.setTable_1(tblvendaTable);
	}
	}catch(Exception e){
		e.printStackTrace();
	}/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionEmitir_relatorio() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Listar_carros_vendido();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (emitir_relatorio)  */return Core.getLinkReport("relatorio_compra", new Report().addParam("id_venda", Core.getParamInt("p_id_venda")));/* End-Code-Block  */
		/*----#start-code(emitir_relatorio)----*/
		
		
		/*----#end-code----*/
			
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}