package nosi.webapps.sistema_gestāo_carro.pages.parametrizacao_marca;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import
import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import nosi.webapps.sistema_gestāo_carro.dao.TblMarca; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Parametrizacao_marcaController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marca();
		model.load();
		var view = new Parametrizacao_marcaView();
		view.id_marca_tbl.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_2(Core.query(null,"SELECT '' as marca_tbl_1,'hidden-ee48_e59f' as id_marca_tbl "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
	String isEdit = Core.getParam("isEdit");
	if (Core.isNotNull(isEdit)) {
		TblMarca tblmarca = new TblMarca().findOne(Core.getParamInt("p_id_marca_tbl"));
		if (tblmarca!=null && !tblmarca.hasError()) {
			model.setMarca(tblmarca.getNomeMarca());
			model.setId_marca(tblmarca.getIdMarca().toString());
	
	view.btn_salvar.addParameter("isEdit", "true");
		}
	}
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	try{
		TblMarca tblmarcafilter = new TblMarca().find();
	List<TblMarca> tblmarcaList = tblmarcafilter.all();
	List<Parametrizacao_marca.Table_2> tblmarcaTable = new ArrayList<>();
	if(Core.isNotNull(tblmarcaList)){
		for(TblMarca tblmarca : tblmarcaList){
			Parametrizacao_marca.Table_2 row  = new Parametrizacao_marca.Table_2();
			row.setMarca_tbl_1(tblmarca.getNomeMarca());
			row.setId_marca_tbl(tblmarca.getIdMarca().toString());
			tblmarcaTable.add(row);
		}
		model.setTable_2(tblmarcaTable);
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
		var model = new Parametrizacao_marca();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_marca_tbl",Core.getParam("p_id_marca_tbl"));
		  return this.forward("sistema_gestāo_carro","Parametrizacao_marca","index",this.queryString()); //if submit, loads the values
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
		TblMarca tblmarca  = new TblMarca();
		if(Core.isNotNull(isEdit)) {
			 tblmarca = session.find(TblMarca.class, Core.getParamInt("p_id_marca"));
		}
		if (tblmarca != null){
			tblmarca.setNomeMarca(model.getMarca());
		}
		session.persist(tblmarca);
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
		return this.forward("sistema_gestāo_carro","Parametrizacao_marca","index",this.queryString());
	}/* End-Code-Block  */
		/*----#start-code(salvar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_gestāo_carro","Parametrizacao_marca","index", this.queryString());	
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marca();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_marca_tbl",Core.getParam("p_id_marca_tbl"));
		  return this.forward("sistema_gestāo_carro","Parametrizacao_marca","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (editar)  */
	this.addQueryString("p_id_marca_tbl", Core.getParam("p_id_marca_tbl"));
	
	this.addQueryString("isEdit", "true");/* End-Code-Block  */
		/*----#start-code(editar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_gestāo_carro","Parametrizacao_marca","index", this.queryString());	
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marca();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_marca_tbl",Core.getParam("p_id_marca_tbl"));
		  return this.forward("sistema_gestāo_carro","Parametrizacao_marca","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (eliminar)  */
		TblMarca tblmarca = new TblMarca().findOne(Core.getParamInt("p_id_marca_tbl"));
	if (tblmarca != null && !tblmarca.hasError()) {
		boolean del = tblmarca.delete(Core.getParamInt("p_id_marca_tbl"));
		if (del)
			Core.setMessageSuccess();
		else
			Core.setMessageError();
	} 
	/* End-Code-Block  */
		/*----#start-code(eliminar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_gestāo_carro","Parametrizacao_marca","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}