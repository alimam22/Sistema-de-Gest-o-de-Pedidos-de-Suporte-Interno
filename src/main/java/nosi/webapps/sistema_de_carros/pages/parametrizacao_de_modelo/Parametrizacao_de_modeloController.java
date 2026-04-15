package nosi.webapps.sistema_de_carros.pages.parametrizacao_de_modelo;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import
import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import java.util.List; //block import
import java.util.LinkedHashMap; //block import
import static nosi.core.i18n.Translator.gt; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Parametrizacao_de_modeloController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_de_modelo();
		model.load();
		var view = new Parametrizacao_de_modeloView();
		view.id_modelo_tbl.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT 'Deserunt doloremque sit consec' as marca_tbl,'Dolor sit aliqua labore elit' as modelo_tbl,'hidden-a665_f52d' as id_modelo_tbl "));
		view.marca.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
	TblMarca tblmarcafilter = new TblMarca().find();
	List<TblMarca> tblmarcaList = tblmarcafilter.all();
	LinkedHashMap<String, String> marca = new LinkedHashMap<>();
	marca.put(null, gt("-- Selecionar --"));
	for(TblMarca tblmarca : tblmarcaList){
		marca.put(tblmarca.getIdMarca().toString(), gt(tblmarca.getNomeMarca()));
	}
	view.marca.setValue(marca);
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	try{
	String isEdit = Core.getParam("isEdit");
	if (Core.isNotNull(isEdit)) {
		TblModelo tblmodelo = new TblModelo().findOne(Core.getParamInt("p_id_modelo_tbl"));
		if (tblmodelo!=null && !tblmodelo.hasError()) {
			model.setMarca(tblmodelo.getIdMarcaFk().getIdMarca().toString());
			model.setModelo(tblmodelo.getNomeModelo());
			model.setId_modelo(tblmodelo.getIdModelo().toString());
	
	view.btn_salvar.addParameter("isEdit", "true");
		}
	}
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	try{
		TblModelo tblmodelofilter = new TblModelo().find();
	List<TblModelo> tblmodeloList = tblmodelofilter.all();
	List<Parametrizacao_de_modelo.Table_1> tblmodeloTable = new ArrayList<>();
	if(Core.isNotNull(tblmodeloList)){
		for(TblModelo tblmodelo : tblmodeloList){
			Parametrizacao_de_modelo.Table_1 row  = new Parametrizacao_de_modelo.Table_1();
			row.setMarca_tbl(tblmodelo.getIdMarcaFk().getNomeMarca());
			row.setModelo_tbl(tblmodelo.getNomeModelo());
			row.setId_modelo_tbl(tblmodelo.getIdModelo().toString());
			tblmodeloTable.add(row);
		}
		model.setTable_1(tblmodeloTable);
	}
	}catch(Exception e){
		e.printStackTrace();
	}/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_de_modelo();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_modelo_tbl",Core.getParam("p_id_modelo_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_de_modelo","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (salvar)  */
	Session session = null;
	Transaction transaction = null;
	String isEdit = Core.getParam("isEdit");
	try{
	if (model.validate()) {
		session = Core.getSession(Core.defaultConnection());
		transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction.begin();
		TblModelo tblmodelo  = new TblModelo();
		if(Core.isNotNull(isEdit)) {
			 tblmodelo = session.find(TblModelo.class, Core.toInt(model.getId_modelo()));
		}
		if (tblmodelo != null){
	TblMarca tblmarcaForeign = session.find(TblMarca.class, Core.toInt(model.getMarca()));
	tblmodelo.setIdMarcaFk(tblmarcaForeign);
			tblmodelo.setNomeModelo(model.getModelo());
		}
		session.persist(tblmodelo);
		transaction.commit();
		Core.setMessageSuccess();
	}
	else
		Core.setMessageError();
	}catch ( Exception e ) {
		e.printStackTrace();
		Core.setMessageError("Error: "+ e.getMessage());
		if (transaction != null)
			transaction.rollback();
	}finally {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
	
	if(Core.isNotNull(isEdit)) {
		this.addQueryString("isEdit", "true");
		return this.forward("sistema_de_carros","Parametrizacao_de_modelo","index",this.queryString());
	}/* End-Code-Block  */
		/*----#start-code(salvar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_de_modelo","index", this.queryString());	
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_de_modelo();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_modelo_tbl",Core.getParam("p_id_modelo_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_de_modelo","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (editar)  */
	this.addQueryString("p_id_modelo_tbl", Core.getParam("p_id_modelo_tbl"));
	
	this.addQueryString("isEdit", "true");/* End-Code-Block  */
		/*----#start-code(editar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_de_modelo","index", this.queryString());	
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_de_modelo();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_modelo_tbl",Core.getParam("p_id_modelo_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_de_modelo","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (eliminar)  */
		TblModelo tblmodelo = new TblModelo().findOne(Core.getParamInt("p_id_modelo_tbl"));
	if (tblmodelo != null && !tblmodelo.hasError()) {
		boolean del = tblmodelo.delete(Core.getParamInt("p_id_modelo_tbl"));
		if (del)
			Core.setMessageSuccess();
		else
			Core.setMessageError();
	} 
	/* End-Code-Block  */
		/*----#start-code(eliminar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_de_modelo","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}