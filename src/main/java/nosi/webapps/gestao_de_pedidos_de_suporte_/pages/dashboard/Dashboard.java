package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.dashboard;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;
import nosi.core.gui.components.IGRPChart3D;

public class Dashboard extends Model{		

	@RParam(rParamName = "p_stats_total_title")
	private String stats_total_title;

	@RParam(rParamName = "p_stats_total_val")
	private String stats_total_val;

	@RParam(rParamName = "p_stats_total_txt")
	private String stats_total_txt;

	@RParam(rParamName = "p_stats_total_url")
	private String stats_total_url;

	@RParam(rParamName = "p_stats_total_bg")
	private String stats_total_bg;

	@RParam(rParamName = "p_stats_total_icn")
	private String stats_total_icn;

	@RParam(rParamName = "p_stats_abertos_title")
	private String stats_abertos_title;

	@RParam(rParamName = "p_stats_abertos_val")
	private String stats_abertos_val;

	@RParam(rParamName = "p_stats_abertos_txt")
	private String stats_abertos_txt;

	@RParam(rParamName = "p_stats_abertos_url")
	private String stats_abertos_url;

	@RParam(rParamName = "p_stats_abertos_bg")
	private String stats_abertos_bg;

	@RParam(rParamName = "p_stats_abertos_icn")
	private String stats_abertos_icn;

	@RParam(rParamName = "p_stats_execucao_title")
	private String stats_execucao_title;

	@RParam(rParamName = "p_stats_execucao_val")
	private String stats_execucao_val;

	@RParam(rParamName = "p_stats_execucao_txt")
	private String stats_execucao_txt;

	@RParam(rParamName = "p_stats_execucao_url")
	private String stats_execucao_url;

	@RParam(rParamName = "p_stats_execucao_bg")
	private String stats_execucao_bg;

	@RParam(rParamName = "p_stats_execucao_icn")
	private String stats_execucao_icn;

	@RParam(rParamName = "p_stats_concluidos_title")
	private String stats_concluidos_title;

	@RParam(rParamName = "p_stats_concluidos_val")
	private String stats_concluidos_val;

	@RParam(rParamName = "p_stats_concluidos_txt")
	private String stats_concluidos_txt;

	@RParam(rParamName = "p_stats_concluidos_url")
	private String stats_concluidos_url;

	@RParam(rParamName = "p_stats_concluidos_bg")
	private String stats_concluidos_bg;

	@RParam(rParamName = "p_stats_concluidos_icn")
	private String stats_concluidos_icn;

	@RParam(rParamName = "p_data_inicio")
	private String data_inicio;

	@RParam(rParamName = "p_data_fim")
	private String data_fim;

	@RParam(rParamName = "p_departamento")
	private String departamento;
	
	private List<Chart_estado> chart_estado = new ArrayList<>();	
	public void setChart_estado(List<Chart_estado> chart_estado){
		this.chart_estado = chart_estado;
	}
	public List<Chart_estado> getChart_estado(){
		return this.chart_estado;
	}

	
	private List<Table_1> table_1 = new ArrayList<>();	
	public void setTable_1(List<Table_1> table_1){
		this.table_1 = table_1;
	}
	public List<Table_1> getTable_1(){
		return this.table_1;
	}

	
	public void setStats_total_title(String stats_total_title){
		this.stats_total_title = stats_total_title;
	}
	public String getStats_total_title(){
		return this.stats_total_title;
	}
	
	public void setStats_total_val(String stats_total_val){
		this.stats_total_val = stats_total_val;
	}
	public String getStats_total_val(){
		return this.stats_total_val;
	}
	
	public void setStats_total_txt(String stats_total_txt){
		this.stats_total_txt = stats_total_txt;
	}
	public String getStats_total_txt(){
		return this.stats_total_txt;
	}
	
	public void setStats_total_url(String stats_total_url){
		this.stats_total_url = stats_total_url;
	}
	public String getStats_total_url(){
		return this.stats_total_url;
	}
	
	public void setStats_total_bg(String stats_total_bg){
		this.stats_total_bg = stats_total_bg;
	}
	public String getStats_total_bg(){
		return this.stats_total_bg;
	}
	
	public void setStats_total_icn(String stats_total_icn){
		this.stats_total_icn = stats_total_icn;
	}
	public String getStats_total_icn(){
		return this.stats_total_icn;
	}
	
	public void setStats_abertos_title(String stats_abertos_title){
		this.stats_abertos_title = stats_abertos_title;
	}
	public String getStats_abertos_title(){
		return this.stats_abertos_title;
	}
	
	public void setStats_abertos_val(String stats_abertos_val){
		this.stats_abertos_val = stats_abertos_val;
	}
	public String getStats_abertos_val(){
		return this.stats_abertos_val;
	}
	
	public void setStats_abertos_txt(String stats_abertos_txt){
		this.stats_abertos_txt = stats_abertos_txt;
	}
	public String getStats_abertos_txt(){
		return this.stats_abertos_txt;
	}
	
	public void setStats_abertos_url(String stats_abertos_url){
		this.stats_abertos_url = stats_abertos_url;
	}
	public String getStats_abertos_url(){
		return this.stats_abertos_url;
	}
	
	public void setStats_abertos_bg(String stats_abertos_bg){
		this.stats_abertos_bg = stats_abertos_bg;
	}
	public String getStats_abertos_bg(){
		return this.stats_abertos_bg;
	}
	
	public void setStats_abertos_icn(String stats_abertos_icn){
		this.stats_abertos_icn = stats_abertos_icn;
	}
	public String getStats_abertos_icn(){
		return this.stats_abertos_icn;
	}
	
	public void setStats_execucao_title(String stats_execucao_title){
		this.stats_execucao_title = stats_execucao_title;
	}
	public String getStats_execucao_title(){
		return this.stats_execucao_title;
	}
	
	public void setStats_execucao_val(String stats_execucao_val){
		this.stats_execucao_val = stats_execucao_val;
	}
	public String getStats_execucao_val(){
		return this.stats_execucao_val;
	}
	
	public void setStats_execucao_txt(String stats_execucao_txt){
		this.stats_execucao_txt = stats_execucao_txt;
	}
	public String getStats_execucao_txt(){
		return this.stats_execucao_txt;
	}
	
	public void setStats_execucao_url(String stats_execucao_url){
		this.stats_execucao_url = stats_execucao_url;
	}
	public String getStats_execucao_url(){
		return this.stats_execucao_url;
	}
	
	public void setStats_execucao_bg(String stats_execucao_bg){
		this.stats_execucao_bg = stats_execucao_bg;
	}
	public String getStats_execucao_bg(){
		return this.stats_execucao_bg;
	}
	
	public void setStats_execucao_icn(String stats_execucao_icn){
		this.stats_execucao_icn = stats_execucao_icn;
	}
	public String getStats_execucao_icn(){
		return this.stats_execucao_icn;
	}
	
	public void setStats_concluidos_title(String stats_concluidos_title){
		this.stats_concluidos_title = stats_concluidos_title;
	}
	public String getStats_concluidos_title(){
		return this.stats_concluidos_title;
	}
	
	public void setStats_concluidos_val(String stats_concluidos_val){
		this.stats_concluidos_val = stats_concluidos_val;
	}
	public String getStats_concluidos_val(){
		return this.stats_concluidos_val;
	}
	
	public void setStats_concluidos_txt(String stats_concluidos_txt){
		this.stats_concluidos_txt = stats_concluidos_txt;
	}
	public String getStats_concluidos_txt(){
		return this.stats_concluidos_txt;
	}
	
	public void setStats_concluidos_url(String stats_concluidos_url){
		this.stats_concluidos_url = stats_concluidos_url;
	}
	public String getStats_concluidos_url(){
		return this.stats_concluidos_url;
	}
	
	public void setStats_concluidos_bg(String stats_concluidos_bg){
		this.stats_concluidos_bg = stats_concluidos_bg;
	}
	public String getStats_concluidos_bg(){
		return this.stats_concluidos_bg;
	}
	
	public void setStats_concluidos_icn(String stats_concluidos_icn){
		this.stats_concluidos_icn = stats_concluidos_icn;
	}
	public String getStats_concluidos_icn(){
		return this.stats_concluidos_icn;
	}
	
	public void setData_inicio(String data_inicio){
		this.data_inicio = data_inicio;
	}
	public String getData_inicio(){
		return this.data_inicio;
	}
	
	public void setData_fim(String data_fim){
		this.data_fim = data_fim;
	}
	public String getData_fim(){
		return this.data_fim;
	}
	
	public void setDepartamento(String departamento){
		this.departamento = departamento;
	}
	public String getDepartamento(){
		return this.departamento;
	}


	public static class Table_1 extends IGRPTable.Table{
	}
	public static class Chart_estado extends IGRPChart3D{
		public Chart_estado(String eixoX, String eixoY, Object eixoZ) {
			super(eixoX, eixoY,eixoZ);
		}
		public Chart_estado() {
		}
	}

	public void loadChart_estado(BaseQueryInterface query) {
		this.setChart_estado(this.loadTable(query,Chart_estado.class));
	}

	public void loadTable_1(BaseQueryInterface query) {
		this.setTable_1(this.loadTable(query,Table_1.class));
	}

}