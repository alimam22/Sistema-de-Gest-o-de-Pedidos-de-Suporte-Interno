package nosi.webapps.sistema_de_carros.pages.lista_acessorio;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import nosi.core.gui.components.IGRPSeparatorList.Pair; //block import
import java.util.List; //block import
import java.util.ArrayList; //block import
import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import java.util.List; //block import
import java.util.LinkedHashMap; //block import
import static nosi.core.i18n.Translator.gt; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import nosi.webapps.sistema_de_carros.dao.TblSolicitacao; //block import
import nosi.webapps.sistema_de_carros.dao.TblAcessorio; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Lista_acessorioController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_acessorio();
		model.load();
		var view = new Lista_acessorioView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadFormlist_1(Core.query(null,"SELECT 'Iste sed deserunt rem mollit' as acessorio,'150' as preco,'106' as quantidade "));
		view.procurar_marca.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
	TblMarca tblmarcafilter = new TblMarca().find();
	List<TblMarca> tblmarcaList = tblmarcafilter.all();
	LinkedHashMap<String, String> procurar_marca = new LinkedHashMap<>();
	procurar_marca.put(null, gt("-- Selecionar --"));
	for(TblMarca tblmarca : tblmarcaList){
		procurar_marca.put(tblmarca.getIdMarca().toString(), gt(tblmarca.getNomeMarca()));
	}
	view.procurar_marca.setValue(procurar_marca);
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	try{
	String isEdit = Core.getParam("isEdit");
	if (Core.isNotNull(isEdit)) {
		TblSolicitacao tblsolicitacao = new TblSolicitacao().findOne(Core.getParamInt("p_id_solicitacao"));
		if (tblsolicitacao!=null && !tblsolicitacao.hasError()) {
			model.setProcurar_marca(tblsolicitacao.getIdAcessorioFk().getIdCarroFk().getIdModeloFk().getIdMarcaFk().getNomeMarca().toString());
			model.setEmail_de_contacto(tblsolicitacao.getEmail());
			model.setId_solicitacao(tblsolicitacao.getIdSolicitacao().toString());
	
	view.btn_solicitar.addParameter("isEdit", "true");
		}
	}
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	try{
	TblAcessorio tblacessoriofilter = new TblAcessorio().find();
	
	List<TblAcessorio> tblacessorioList = tblacessoriofilter.all();
	if ( Core.isNotNull(tblacessorioList) ) {
			List <Lista_acessorio.Formlist_1>  separatorlistDocs =new ArrayList<>(); 
			tblacessorioList.forEach(tblacessorio -> {
				Lista_acessorio.Formlist_1 row = new Lista_acessorio.Formlist_1();
				row.setAcessorio( new Pair(tblacessorio.getNomeAcessorio(),tblacessorio.getNomeAcessorio()) );
	row.setPreco( new Pair(tblacessorio.getPreco().toString(),tblacessorio.getPreco().toString()) );
	row.setFormlist_1_id( new Pair(tblacessorio.getIdAcessorio().toString(),tblacessorio.getIdAcessorio().toString()) );
				separatorlistDocs.add(row);
			});
			model.setFormlist_1( separatorlistDocs);
		}
	}catch ( Exception e ) {
		e.printStackTrace();
	}/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionInserir_acessorio() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_acessorio();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (inserir_acessorio)  *//* End-Code-Block  */
		/*----#start-code(inserir_acessorio)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	
	public Response actionSolicitar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_acessorio();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Insserir_acessorio","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (solicitar)  */
	Session session = null;
	Transaction transaction = null;
	try{
	if (model.validate()) {
		session = Core.getSession(Core.defaultConnection());
		transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction.begin();
		for ( Lista_acessorio.Formlist_1 row : model.getFormlist_1()){
			TblSolicitacao tblsolicitacao = new TblSolicitacao();
			if(Core.isNotNullOrZero( model.getId_solicitacao())){
					tblsolicitacao = session.find(TblSolicitacao.class, Core.toInt(model.getId_solicitacao()));
			}
			TblAcessorio tblacessorioForeign = session.find(TblAcessorio.class, Core.toInt(row.getAcessorio().getKey()));
	tblsolicitacao.setIdAcessorioFk(tblacessorioForeign);
			tblsolicitacao.setQuantidade(Core.toInt(row.getQuantidade().getKey()));
			tblsolicitacao.setPrecoFinal(Core.toInt(row.getQuantidade().getKey())*Core.toInt(row.getPreco().getKey()));
			tblsolicitacao.setEmail(model.getEmail_de_contacto());
			session.persist(tblsolicitacao);
		}
	if(Core.isNotNullOrZero( model.getId_solicitacao())){
	String [] tblsolicitacaodeletedIdsArray = model.getP_formlist_1_del();
	if ( Core.isNotNull( tblsolicitacaodeletedIdsArray ) ) {
	for ( String docId : tblsolicitacaodeletedIdsArray ) {
		if ( Core.isNotNull( docId ) && !docId.isEmpty() ) {
			TblSolicitacao tblsolicitacao = session.find(TblSolicitacao.class, Core.toInt(docId));
			session.delete(tblsolicitacao);
			}
		}
	}
	}
	
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
	/* End-Code-Block  */
		/*----#start-code(solicitar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Insserir_acessorio","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}