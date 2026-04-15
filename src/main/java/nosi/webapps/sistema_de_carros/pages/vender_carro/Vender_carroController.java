package nosi.webapps.sistema_de_carros.pages.vender_carro;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import org.hibernate.Session; //block import
import org.hibernate.Transaction; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import nosi.webapps.sistema_de_carros.dao.TblVenda; //block import
import java.time.LocalDate; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Vender_carroController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Vender_carro();
		model.load();
		model.setManual("sistema_de_carros","Parametrizacao_marcas","index");
		model.setView_1_img("../images/IGRP/IGRP2.3/assets/img/jon_doe.jpg");
		var view = new Vender_carroView();
		/* Start-Code-Block (index) */
	try{
	
		TblCarro tblcarro = new TblCarro().findOne(Core.getParamInt("p_id_carro_tbl"));
		if (tblcarro!=null && !tblcarro.hasError()) {
			model.setMarca(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca());
			model.setModelo(tblcarro.getIdModeloFk().getNomeModelo());
			model.setPreco(tblcarro.getPreco());
			model.setManual(Core.getLinkFileByUuid(tblcarro.getIdManual()));
			model.setView_1_img(Core.getLinkFileByUuid(tblcarro.getIdFoto()));
			model.setId_carro(tblcarro.getIdCarro().toString());
		}
	}catch ( Exception e ) {
		e.printStackTrace();
	}/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionVenda() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Vender_carro();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (venda)  */
	try{
		TblCarro tblcarro = new TblCarro().findOne(Core.toInt(model.getId_carro()));
		if (tblcarro != null && !tblcarro.hasError()) {
		tblcarro.setVendido( true );
	
	Session session = null;
	Transaction transaction = null;
	try{
	if (model.validate()) {
		session = Core.getSession(Core.defaultConnection());
		transaction = session.getTransaction();
		if(!transaction.isActive())
			transaction.begin();
		TblVenda tblvenda  = new TblVenda();
			tblvenda.setVendedor(Core.getCurrentUser().getName());
			tblvenda.setComprador(model.getComprador());
			tblvenda.setDataVenda(LocalDate.now());
	TblCarro tblcarroForeign = session.find(TblCarro.class, Core.toInt(model.getId_carro()));
	tblvenda.setIdCarroFk(tblcarroForeign);
		session.persist(tblvenda);
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
	
		tblcarro.update();
		Core.setMessageSuccess();
		}
		else
			Core.setMessageError();
	}catch ( Exception e ) {
		e.printStackTrace();
	}	/* End-Code-Block  */
		/*----#start-code(venda)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}