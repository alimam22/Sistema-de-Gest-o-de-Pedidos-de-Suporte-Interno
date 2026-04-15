package nosi.webapps.sistema_de_carros.pages.insserir_acessorio;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.stream.Collectors; //block import
import java.util.ArrayList; //block import
import java.util.Arrays; //block import
import java.util.List; //block import
import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import nosi.webapps.sistema_de_carros.dao.TblAcessorio; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Insserir_acessorioController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Insserir_acessorio();
		model.load();
		var view = new Insserir_acessorioView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadSeparatorlist_1(Core.query(null,"SELECT 'Rem omnis magna sit mollit' as nome_acessorio,'63' as preco "));
		  ----#gen-example */
		/* Start-Code-Block (index) */		model.setId_carro(Core.getParamInt("p_id_carro").toString());/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Insserir_acessorio();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (salvar)  */
	Session session = null;
	Transaction transaction = null;
	try{
	if (model.validate()) {
		session = Core.getSession(Core.defaultConnection());
		transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction.begin();
		List<String> tblacessorioeditList = new ArrayList<>();
		if(model.getP_separatorlist_1_edit() != null){
			tblacessorioeditList =  new ArrayList<>(Arrays.asList(model.getP_separatorlist_1_edit()));
		}
		for(Insserir_acessorio.Separatorlist_1 row : model.getSeparatorlist_1()){
			TblAcessorio tblacessorio = new TblAcessorio();
			if( Core.isNotNullOrZero(row.getSeparatorlist_1_id().getKey())){
				if(!tblacessorioeditList.isEmpty() && tblacessorioeditList.remove(row.getSeparatorlist_1_id().getKey())){
					tblacessorio = session.find(TblAcessorio.class, Core.toInt(row.getSeparatorlist_1_id().getKey()));
				}
				else
					continue;
			}
				
			TblCarro tblcarroForeign = session.find(TblCarro.class, Core.toInt(model.getId_carro()));
	tblacessorio.setIdCarroFk(tblcarroForeign);
			tblacessorio.setNomeAcessorio(row.getNome_acessorio().getKey());
			tblacessorio.setPreco(Core.toInt(row.getPreco().getKey()));
			session.persist(tblacessorio);
		}
	String [] tblacessoriodeletedIdsArray = model.getP_separatorlist_1_del();
	if ( Core.isNotNull(tblacessoriodeletedIdsArray ) ) {
	for ( String docId : tblacessoriodeletedIdsArray ) {
		if ( Core.isNotNull( docId ) && !docId.isEmpty() ) {
			TblAcessorio tblacessorio = session.find(TblAcessorio.class, Core.toInt(docId));
			session.delete(tblacessorio);
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
		/*----#start-code(salvar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}