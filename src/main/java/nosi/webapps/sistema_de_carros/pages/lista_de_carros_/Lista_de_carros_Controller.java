package nosi.webapps.sistema_de_carros.pages.lista_de_carros_;

import jakarta.inject.Inject;
import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import

import nosi.core.webapp.View;
import nosi.webapps.sistema_de_carros.PageHelpers.Listar_PageHelper;
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import java.time.LocalDate; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Lista_de_carros_Controller extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_carros_();
		model.load();
		var view = new Lista_de_carros_View();
		view.id_carro_tbl.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT '../images/IGRP/IGRP2.3/assets/img/jon_doe.jpg' as foto,'Aperiam consectetur elit deser' as marca_tbl,'13' as preco_tbl,'Doloremque ut perspiciatis lor' as modelo_tbl,'/IGRP/images/IGRP/IGRP2.3/app/sistema_de_carros/parametrizacao_marcas/Parametrizacao_marcas.xml' as manual_tbl,'06-05-2017' as data_resgistro_tbl,'hidden-76c4_01fb' as id_carro_tbl "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
		TblCarro tblcarrofilter = new TblCarro().find();
	if(Core.isNotNullOrZero(model.getPreco())){
		tblcarrofilter.andWhere("preco","=",Core.toInt(model.getPreco()));
	}
	if(Core.isNotNullOrZero(model.getIntervalo_de_data())){
		String datas[]= model.getIntervalo_de_data().trim().split(" / ");
		tblcarrofilter.andWhereBetween("dataRegistro", Core.formatDate(datas[0], "dd-mm-yyyy", "yyyy-mm-dd"),Core.formatDate(datas[1], "dd-mm-yyyy", "yyyy-mm-dd"));
	}
	if(Core.isNotNullOrZero( false )){
		tblcarrofilter.andWhere("vendido","=", false );
	}
	List<TblCarro> tblcarroList = tblcarrofilter.all();
	List<Lista_de_carros_.Table_1> tblcarroTable = new ArrayList<>();
	if(Core.isNotNull(tblcarroList)){
		for(TblCarro tblcarro : tblcarroList){
			Lista_de_carros_.Table_1 row  = new Lista_de_carros_.Table_1();
			row.setFoto(Core.getLinkFileByUuid(tblcarro.getIdFoto()));
			row.setMarca_tbl(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca());
			row.setPreco_tbl(tblcarro.getPreco());
			row.setModelo_tbl(tblcarro.getIdModeloFk().getNomeModelo());
			row.setManual_tbl(Core.getLinkFileByUuid(tblcarro.getIdManual()));
			row.setData_resgistro_tbl(Core.convertLocalDateToString(tblcarro.getDataRegistro(), Core.DD_MM_YYYY));
			row.setId_carro_tbl(tblcarro.getIdCarro().toString());
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
	
	public Response actionNovo() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_carros_();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_carro_tbl",Core.getParam("p_id_carro_tbl"));
		  return this.forward("sistema_de_carros","Registro_de_carros_","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (novo)  *//* End-Code-Block  */
		/*----#start-code(novo)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Registro_de_carros_","index", this.queryString());	
	}
	
	public Response actionPesquisar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_carros_();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_carro_tbl",Core.getParam("p_id_carro_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (pesquisar)  */
	return this.forward("sistema_de_carros","Registro_de_carros_","index",this.queryString());/* End-Code-Block  */
		/*----#start-code(pesquisar)----*/
		
		
		/*----#end-code----*/
			
	}
	
	public Response actionVender_carro() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_carros_();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_carro_tbl",Core.getParam("p_id_carro_tbl"));
		  return this.forward("sistema_de_carros","Vender_carro","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (vender_carro)  */
	this.addQueryString("p_id_carro_tbl", Core.getParam("p_id_carro_tbl"));/* End-Code-Block  */
		/*----#start-code(vender_carro)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Vender_carro","index", this.queryString());	
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_carros_();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_carro_tbl",Core.getParam("p_id_carro_tbl"));
		  return this.forward("sistema_de_carros","Registro_de_carros_","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (editar)  */
	this.addQueryString("p_id_carro_tbl", Core.getParam("p_id_carro_tbl"));
	
	this.addQueryString("isEdit", "true");/* End-Code-Block  */
		/*----#start-code(editar)----*/
		listar_pageHelper.index(model);
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Registro_de_carros_","index", this.queryString());	
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_carros_();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_carro_tbl",Core.getParam("p_id_carro_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (eliminar)  */
		TblCarro tblcarro = new TblCarro().findOne(Core.getParamInt("p_id_carro_tbl"));
	if (tblcarro != null && !tblcarro.hasError()) {
		boolean del = tblcarro.delete(Core.getParamInt("p_id_carro_tbl"));
		if (del)
			Core.setMessageSuccess();
		else
			Core.setMessageError();
	}
	/* End-Code-Block  */
		/*----#start-code(eliminar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/
@Inject
	Listar_PageHelper listar_pageHelper;

/*----#end-code----*/
}