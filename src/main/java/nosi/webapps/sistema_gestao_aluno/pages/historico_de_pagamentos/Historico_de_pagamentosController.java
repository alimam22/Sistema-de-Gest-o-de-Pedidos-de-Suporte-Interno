package nosi.webapps.sistema_gestao_aluno.pages.historico_de_pagamentos;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Historico_de_pagamentosController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Historico_de_pagamentos();
		model.load();
		var view = new Historico_de_pagamentosView();
		/* Start-Code-Block (index) *//* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Historico_de_pagamentos();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_gestao_aluno","Lista_de_alunos","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (salvar)  *//* End-Code-Block  */
		/*----#start-code(salvar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_gestao_aluno","Lista_de_alunos","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}