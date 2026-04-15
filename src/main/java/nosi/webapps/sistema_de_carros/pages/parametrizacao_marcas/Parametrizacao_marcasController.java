package nosi.webapps.sistema_de_carros.pages.parametrizacao_marcas;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import

import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/
import jakarta.inject.Inject;
import nosi.webapps.sistema_de_carros.PageHelpers.ParametrizacaoMarcasPageHelper;


/*----#end-code----*/
		
public class Parametrizacao_marcasController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marcas();
		model.load();
		var view = new Parametrizacao_marcasView();
		view.id_marca_tbl.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT 'Laudantium consectetur ipsum v' as marca_tbl,'hidden-1137_7df6' as id_marca_tbl "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		//chamada
		parametrizacaoMarcasPageHelper.index(model,view);
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marcas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_marca_tbl",Core.getParam("p_id_marca_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
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
		return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString());
	}/* End-Code-Block  */
		/*----#start-code(salvar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marcas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_marca_tbl",Core.getParam("p_id_marca_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		    Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (editar)  */
	this.addQueryString("p_id_marca_tbl", Core.getParam("p_id_marca_tbl"));
	
	this.addQueryString("isEdit", "true");/* End-Code-Block  */
		/*----#start-code(editar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Parametrizacao_marcas();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_id_marca_tbl",Core.getParam("p_id_marca_tbl"));
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
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
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/
	@Inject
	ParametrizacaoMarcasPageHelper parametrizacaoMarcasPageHelper;

/*----#end-code----*/
}