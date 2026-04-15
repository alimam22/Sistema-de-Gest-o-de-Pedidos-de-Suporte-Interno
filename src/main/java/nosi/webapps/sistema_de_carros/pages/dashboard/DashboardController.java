package nosi.webapps.sistema_de_carros.pages.dashboard;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
import java.util.ArrayList; //block import
import java.util.List; //block import
import java.util.stream.Collectors; //block import
import java.util.LongSummaryStatistics; //block import
import nosi.webapps.sistema_de_carros.pages.dashboard.Dashboard.Chart_1; //block import
import java.util.Map; //block import
import java.util.ArrayList; //block import
import java.util.List; //block import
import java.util.stream.Collectors; //block import
import java.util.LongSummaryStatistics; //block import
import nosi.webapps.sistema_de_carros.pages.dashboard.Dashboard.Chart_2; //block import
import java.util.Map; //block import
import nosi.webapps.sistema_de_carros.dao.TblCarro; //block import
import nosi.webapps.sistema_de_carros.dao.TblModelo; //block import
import nosi.webapps.sistema_de_carros.dao.TblMarca; //block import
import java.time.LocalDate; //block import
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class DashboardController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Dashboard();
		model.load();
		var view = new DashboardView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		view.chart_1.loadQuery(Core.query(null,"SELECT 'X1' as EixoX, 'Y1' as EixoY, 15 as EixoZ"
                                      +" UNION SELECT 'X2' as EixoX, 'Y2' as EixoY, 10 as EixoZ"
                                      +" UNION SELECT 'X2' as EixoX, 'Y2' as EixoY, 23 as EixoZ"
                                      +" UNION SELECT 'X3' as EixoX, 'Y3' as EixoY, 40 as EixoZ"));
		view.chart_2.loadQuery(Core.query(null,"SELECT 'X1' as EixoX, 'Y1' as EixoY, 15 as EixoZ"
                                      +" UNION SELECT 'X2' as EixoX, 'Y2' as EixoY, 10 as EixoZ"
                                      +" UNION SELECT 'X2' as EixoX, 'Y2' as EixoY, 23 as EixoZ"
                                      +" UNION SELECT 'X3' as EixoX, 'Y3' as EixoY, 40 as EixoZ"));
		  ----#gen-example */
		/* Start-Code-Block (index) */
	try{
	TblCarro tblcarrofilter = new TblCarro().find();
	if(Core.isNotNullOrZero( true )){
		tblcarrofilter.andWhere("vendido","=", true );
	}
	if(Core.isNotNullOrZero(model.getIntervalo_de_data())){
		String datas[]= model.getIntervalo_de_data().trim().split(" / ");
		tblcarrofilter.andWhereBetween("dataRegistro", Core.formatDate(datas[0], "dd-mm-yyyy", "yyyy-mm-dd"),Core.formatDate(datas[1], "dd-mm-yyyy", "yyyy-mm-dd"));
	}
	List<TblCarro> tblcarroList  = tblcarrofilter.all();
	Map<Integer, LongSummaryStatistics> value = tblcarroList.stream().collect(Collectors.groupingBy(tblcarro -> tblcarro.getIdModeloFk().getIdModelo() , Collectors.summarizingLong(tblcarro -> tblcarro.getIdModeloFk().getIdModelo())));
	if(Core.isNotNull(tblcarroList)){
		model.setChart_1(new ArrayList<>());
		tblcarroList.stream().forEach(tblcarro->{
			Chart_1 c = new Chart_1();
			c.setEixoX(tblcarro.getIdModeloFk().getNomeModelo());
	
			c.setEixoY(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca());
	
			c.setEixoZ(value.get(tblcarro.getIdModeloFk().getIdModelo()).getCount());
			model.getChart_1().add(c);
		});
	}
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	
	
	try{
	TblCarro tblcarrofilter = new TblCarro().find();
	if(Core.isNotNullOrZero(model.getIntervalo_de_data())){
		String datas[]= model.getIntervalo_de_data().trim().split(" / ");
		tblcarrofilter.andWhereBetween("dataRegistro", Core.formatDate(datas[0], "dd-mm-yyyy", "yyyy-mm-dd"),Core.formatDate(datas[1], "dd-mm-yyyy", "yyyy-mm-dd"));
	}
	List<TblCarro> tblcarroList  = tblcarrofilter.all();
	Map<String, LongSummaryStatistics> value = tblcarroList.stream().collect(Collectors.groupingBy(tblcarro -> tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca() , Collectors.summarizingLong(tblcarro -> Core.toLong(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca()))));
	if(Core.isNotNull(tblcarroList)){
		model.setChart_2(new ArrayList<>());
		tblcarroList.stream().forEach(tblcarro->{
			Chart_2 c = new Chart_2();
			c.setEixoX(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca());
	
			c.setEixoY(value.get(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca()).getCount());
			model.getChart_2().add(c);
		});
	}
	}catch ( Exception e ) {
		e.printStackTrace();
	}
	/* End-Code-Block (index) */
		/*----#start-code(index)----*/
		
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionFiltro() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Dashboard();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_de_carros","Parametrizacao_marcas","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (filtro)  *//* End-Code-Block  */
		/*----#start-code(filtro)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_de_carros","Parametrizacao_marcas","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}