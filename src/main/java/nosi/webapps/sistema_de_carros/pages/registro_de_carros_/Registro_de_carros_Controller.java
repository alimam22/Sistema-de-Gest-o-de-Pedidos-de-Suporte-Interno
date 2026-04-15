package nosi.webapps.sistema_de_carros.pages.registro_de_carros_;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import java.util.List; //block import
import java.util.LinkedHashMap; //block import
import static nosi.core.i18n.Translator.gt; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import java.time.LocalDate; //block import
import nosi.core.webapp.UploadedFile; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Registro_de_carros_Controller extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Registro_de_carros_();
		model.load();
		model.setMostrar_documento("sistema_de_carros","Parametrizacao_marcas","index");
		var view = new Registro_de_carros_View();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		view.marca.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.modelo.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/* Start-Code-Block (index) */view.mostrar_documento.setVisible(false);
	
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
	TblModelo tblmodelofilter = new TblModelo().find();
	if(Core.isNotNullOrZero(Core.getParamInt("p_marca"))){
		tblmodelofilter.andWhere("idMarcaFk","=",Core.getParamInt("p_marca"));
	}
	List<TblModelo> tblmodeloList = tblmodelofilter.all();
	LinkedHashMap<String, String> modelo = new LinkedHashMap<>();
	modelo.put(null, gt("-- Selecionar --"));
	for(TblModelo tblmodelo : tblmodeloList){
		modelo.put(tblmodelo.getIdModelo().toString(), gt(tblmodelo.getNomeModelo()));
	}
	view.modelo.setValue(modelo);
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	try{
	String isEdit = Core.getParam("isEdit");
	if (Core.isNotNull(isEdit)) {
		TblCarro tblcarro = new TblCarro().findOne(Core.getParamInt("p_id_carro_tbl"));
		if (tblcarro!=null && !tblcarro.hasError()) {
	view.mostrar_documento.setVisible(true);
			view.foto.setValue(Core.getLinkFileByUuid(tblcarro.getIdFoto()));
			model.setMarca(tblcarro.getIdModeloFk().getIdModelo().toString());
			model.setModelo(tblcarro.getIdModeloFk().getIdMarcaFk().getIdMarca().toString());
			model.setData_registro(Core.convertLocalDateToString(tblcarro.getDataRegistro(), Core.DD_MM_YYYY));
			model.setPreco(tblcarro.getPreco());
			view.manual_de_utilizacao.setValue("Can not fill the upload field form!");
			model.setId_carro(tblcarro.getIdCarro().toString());
	
	view.btn_registro.addParameter("isEdit", "true");
		}
	}
	}catch ( Exception e ) {
		e.printStackTrace();
	}/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionRegistro() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Registro_de_carros_();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Registro_de_carros_","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (registro)  */
	Session session = null;
	Transaction transaction = null;
	String isEdit = Core.getParam("isEdit");
	try{
	if (model.validate()) {
		session = Core.getSession(Core.defaultConnection());
		transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction.begin();
		TblCarro tblcarro  = new TblCarro();
		if(Core.isNotNull(isEdit)) {
			 tblcarro = session.find(TblCarro.class, Core.toInt(model.getId_carro()));
		}
		if (tblcarro != null){
			tblcarro.setIdFoto(model.getFoto_uuid());
			tblcarro.setDataRegistro(Core.convertStringToLocalDate(model.getData_registro(), Core.DD_MM_YYYY));
			tblcarro.setIdManual(model.getManual_de_utilizacao()!= null && model.getManual_de_utilizacao().isUploaded() ? tblcarro.getIdManual() == null ? Core.saveFileNGetUuid(model.getManual_de_utilizacao()) : Core.updateFile(model.getManual_de_utilizacao(),tblcarro.getIdManual()) ? tblcarro.getIdManual() : tblcarro.getIdManual() : null);
	TblModelo tblmodeloForeign = session.find(TblModelo.class, Core.toInt(model.getModelo()));
	tblcarro.setIdModeloFk(tblmodeloForeign);
			tblcarro.setPreco(model.getPreco());
			tblcarro.setVendido( false );
		}
		session.persist(tblcarro);
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
		return this.forward("sistema_de_carros","Registro_de_carros_","index",this.queryString());
	}/* End-Code-Block  */
		/*----#start-code(registro)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Registro_de_carros_","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}