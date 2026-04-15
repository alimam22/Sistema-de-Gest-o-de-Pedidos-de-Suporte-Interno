package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.dashboard;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

/**
 * Dashboard View — Gestão de Pedidos de Suporte
 *
 * Exibe estatísticas gerais, gráfico de distribuição por estado,
 * tabela de top técnicos e filtros de pesquisa por período/departamento.
 */
public class DashboardView extends View {

	// =========================================================================
	// COMPONENTES PRINCIPAIS
	// =========================================================================

	/** Caixas de estatísticas */
	public IGRPStatBox stats_total;
	public IGRPStatBox stats_abertos;
	public IGRPStatBox stats_execucao;
	public IGRPStatBox stats_concluidos;

	/** Gráfico de distribuição por estado */
	public IGRPChart chart_estado;

	/** Tabela de top técnicos */
	public IGRPTable table_1;

	/** Formulário de navegação (botões de atalho) */
	public IGRPForm form_1;

	// =========================================================================
	// CAMPOS — STAT BOX: TOTAL
	// =========================================================================

	public Field stats_total_title;
	public Field stats_total_val;
	public Field stats_total_txt;
	public Field stats_total_url;
	public Field stats_total_bg;
	public Field stats_total_icn;

	// =========================================================================
	// CAMPOS — STAT BOX: ABERTOS
	// =========================================================================

	public Field stats_abertos_title;
	public Field stats_abertos_val;
	public Field stats_abertos_txt;
	public Field stats_abertos_url;
	public Field stats_abertos_bg;
	public Field stats_abertos_icn;

	// =========================================================================
	// CAMPOS — STAT BOX: EM EXECUÇÃO
	// =========================================================================

	public Field stats_execucao_title;
	public Field stats_execucao_val;
	public Field stats_execucao_txt;
	public Field stats_execucao_url;
	public Field stats_execucao_bg;
	public Field stats_execucao_icn;

	// =========================================================================
	// CAMPOS — STAT BOX: CONCLUÍDOS
	// =========================================================================

	public Field stats_concluidos_title;
	public Field stats_concluidos_val;
	public Field stats_concluidos_txt;
	public Field stats_concluidos_url;
	public Field stats_concluidos_bg;
	public Field stats_concluidos_icn;

	// =========================================================================
	// BOTÕES DE NAVEGAÇÃO
	// =========================================================================

	public IGRPButton btn_departamento_button;
	public IGRPButton btn_categoria;
	public IGRPButton btn_pedidos;
	public IGRPButton btn_lista_de_pedidos;

	// =========================================================================
	// CONSTRUTOR
	// =========================================================================

	public DashboardView() {
		this.setPageTitle("Dashboard");

		initComponents();
		initStatBoxFields();
		initNavigationButtons();
		configureChart();
	}

	// =========================================================================
	// INICIALIZAÇÃO — COMPONENTES
	// =========================================================================

	private void initComponents() {
		stats_total     = new IGRPStatBox("stats_total",     "");
		stats_abertos   = new IGRPStatBox("stats_abertos",   "");
		stats_execucao  = new IGRPStatBox("stats_execucao",  "");
		stats_concluidos = new IGRPStatBox("stats_concluidos", "");

		chart_estado = new IGRPChart("chart_estado", "Distribuição por Estado");
		table_1      = new IGRPTable("table_1", "Top Técnicos (Pedidos Resolvidos)");
		form_1       = new IGRPForm("form_1", "");
	}

	// =========================================================================
	// INICIALIZAÇÃO — CAMPOS DAS STAT BOXES
	// =========================================================================

	private void initStatBoxFields() {
		// --- Total ---
		stats_total_title = buildTextField("stats_total_title", "Box Title",  "p_stats_total_title");
		stats_total_val   = buildTextField("stats_total_val",   "Value",      "p_stats_total_val");
		stats_total_txt   = buildTextField("stats_total_txt",   "Url Text",   "p_stats_total_txt");
		stats_total_url   = buildTextField("stats_total_url",   "Url",        "p_stats_total_url");
		stats_total_bg    = buildTextField("stats_total_bg",    "Background", "p_stats_total_bg");
		stats_total_icn   = buildIconField("stats_total_icn",   "p_stats_total_icn",   "fa-tasks");

		// --- Abertos ---
		stats_abertos_title = buildTextField("stats_abertos_title", "Box Title",  "p_stats_abertos_title");
		stats_abertos_val   = buildTextField("stats_abertos_val",   "Value",      "p_stats_abertos_val");
		stats_abertos_txt   = buildTextField("stats_abertos_txt",   "Url Text",   "p_stats_abertos_txt");
		stats_abertos_url   = buildTextField("stats_abertos_url",   "Url",        "p_stats_abertos_url");
		stats_abertos_bg    = buildTextField("stats_abertos_bg",    "Background", "p_stats_abertos_bg");
		stats_abertos_icn   = buildIconField("stats_abertos_icn",   "p_stats_abertos_icn",   "fa-clock-o");

		// --- Em Execução ---
		stats_execucao_title = buildTextField("stats_execucao_title", "Box Title",  "p_stats_execucao_title");
		stats_execucao_val   = buildTextField("stats_execucao_val",   "Value",      "p_stats_execucao_val");
		stats_execucao_txt   = buildTextField("stats_execucao_txt",   "Url Text",   "p_stats_execucao_txt");
		stats_execucao_url   = buildTextField("stats_execucao_url",   "Url",        "p_stats_execucao_url");
		stats_execucao_bg    = buildTextField("stats_execucao_bg",    "Background", "p_stats_execucao_bg");
		stats_execucao_icn   = buildIconField("stats_execucao_icn",   "p_stats_execucao_icn",  "fa-gears");

		// --- Concluídos ---
		stats_concluidos_title = buildTextField("stats_concluidos_title", "Box Title",  "p_stats_concluidos_title");
		stats_concluidos_val   = buildTextField("stats_concluidos_val",   "Value",      "p_stats_concluidos_val");
		stats_concluidos_txt   = buildTextField("stats_concluidos_txt",   "Url Text",   "p_stats_concluidos_txt");
		stats_concluidos_url   = buildTextField("stats_concluidos_url",   "Url",        "p_stats_concluidos_url");
		stats_concluidos_bg    = buildTextField("stats_concluidos_bg",    "Background", "p_stats_concluidos_bg");
		stats_concluidos_icn   = buildIconField("stats_concluidos_icn",   "p_stats_concluidos_icn", "fa-check-circle");
	}

	// =========================================================================
	// INICIALIZAÇÃO — BOTÕES DE NAVEGAÇÃO
	// =========================================================================

	private void initNavigationButtons() {
		btn_departamento_button = new IGRPButton(
				"Departamento", "gestao_de_pedidos_de_suporte_", "Dashboard",
				"departamento_button", "submit", "primary|fa-angle-right", "", "");
		btn_departamento_button.propertie
				.add("id",                 "button_f785_ac76")
				.add("type",               "form")
				.add("class",              "primary")
				.add("rel",                "departamento_button")
				.add("refresh_components", "");

		btn_categoria = new IGRPButton(
				"Categoria", "gestao_de_pedidos_de_suporte_", "Dashboard",
				"categoria", "submit", "info|fa-angle-right", "", "");
		btn_categoria.propertie
				.add("id",                 "button_9bb0_ef2c")
				.add("type",               "form")
				.add("class",              "info")
				.add("rel",                "categoria")
				.add("refresh_components", "");

		btn_pedidos = new IGRPButton(
				"Pedidos", "gestao_de_pedidos_de_suporte_", "Dashboard",
				"pedidos", "submit", "purple|fa-angle-right", "", "");
		btn_pedidos.propertie
				.add("id",                 "button_234e_5427")
				.add("type",               "form")
				.add("class",              "purple")
				.add("rel",                "pedidos")
				.add("refresh_components", "");

		btn_lista_de_pedidos = new IGRPButton(
				"Lista de pedidos", "gestao_de_pedidos_de_suporte_", "Dashboard",
				"lista_de_pedidos", "submit", "grey|fa-angle-right", "", "");
		btn_lista_de_pedidos.propertie
				.add("id",                 "button_6eae_2cd2")
				.add("type",               "form")
				.add("class",              "grey")
				.add("rel",                "lista_de_pedidos")
				.add("refresh_components", "");
	}

	// =========================================================================
	// INICIALIZAÇÃO — GRÁFICO
	// =========================================================================

	private void configureChart() {
		chart_estado.setCaption("");
		chart_estado.setChart_type("line");
		chart_estado.setXaxys("Eixo de X");
		chart_estado.setYaxys("Eixo de Y");
		chart_estado.setUrl("#");
		// Personalizar cores do gráfico se necessário:
		// chart_estado.addColor("#1d5714").addColor("#de4b5c").addColor("#f7dd9e").addColor("#4aa706");
	}

	// =========================================================================
	// RENDER — MONTAGEM DA PÁGINA
	// =========================================================================

	@Override
	public void render() {

		// Stat Boxes
		stats_total.addField(stats_total_title);
		stats_total.addField(stats_total_val);
		stats_total.addField(stats_total_txt);
		stats_total.addField(stats_total_url);
		stats_total.addField(stats_total_bg);
		stats_total.addField(stats_total_icn);

		stats_abertos.addField(stats_abertos_title);
		stats_abertos.addField(stats_abertos_val);
		stats_abertos.addField(stats_abertos_txt);
		stats_abertos.addField(stats_abertos_url);
		stats_abertos.addField(stats_abertos_bg);
		stats_abertos.addField(stats_abertos_icn);

		stats_execucao.addField(stats_execucao_title);
		stats_execucao.addField(stats_execucao_val);
		stats_execucao.addField(stats_execucao_txt);
		stats_execucao.addField(stats_execucao_url);
		stats_execucao.addField(stats_execucao_bg);
		stats_execucao.addField(stats_execucao_icn);

		stats_concluidos.addField(stats_concluidos_title);
		stats_concluidos.addField(stats_concluidos_val);
		stats_concluidos.addField(stats_concluidos_txt);
		stats_concluidos.addField(stats_concluidos_url);
		stats_concluidos.addField(stats_concluidos_bg);
		stats_concluidos.addField(stats_concluidos_icn);

		// Botões de navegação
		form_1.addButton(btn_departamento_button);
		form_1.addButton(btn_categoria);
		form_1.addButton(btn_pedidos);
		form_1.addButton(btn_lista_de_pedidos);

		// Adição à página (ordem de exibição)
		this.addToPage(stats_total);
		this.addToPage(stats_abertos);
		this.addToPage(stats_execucao);
		this.addToPage(stats_concluidos);
		this.addToPage(chart_estado);
		this.addToPage(table_1);
		this.addToPage(form_1);
	}

	// =========================================================================
	// SET MODEL — BIND DE VALORES
	// =========================================================================

	@Override
	public void setModel(Model model) {
		// Stat Box — Total
		stats_total_title.setValue(model);
		stats_total_val.setValue(model);
		stats_total_txt.setValue(model);
		stats_total_url.setValue(model);
		stats_total_bg.setValue(model);

		// Stat Box — Abertos
		stats_abertos_title.setValue(model);
		stats_abertos_val.setValue(model);
		stats_abertos_txt.setValue(model);
		stats_abertos_url.setValue(model);
		stats_abertos_bg.setValue(model);

		// Stat Box — Em Execução
		stats_execucao_title.setValue(model);
		stats_execucao_val.setValue(model);
		stats_execucao_txt.setValue(model);
		stats_execucao_url.setValue(model);
		stats_execucao_bg.setValue(model);

		// Stat Box — Concluídos
		stats_concluidos_title.setValue(model);
		stats_concluidos_val.setValue(model);
		stats_concluidos_txt.setValue(model);
		stats_concluidos_url.setValue(model);
		stats_concluidos_bg.setValue(model);

		// Componentes dinâmicos
		chart_estado.loadModel(((Dashboard) model).getChart_estado());
		table_1.loadModel(((Dashboard) model).getTable_1());
	}

	// =========================================================================
	// HELPERS PRIVADOS
	// =========================================================================

	/**
	 * Cria um TextField padrão com as propriedades comuns.
	 */
	private TextField buildTextField(String id, String labelKey, String paramName) {
		TextField field = new TextField(model, id);
		field.setLabel(gt(labelKey));
		field.propertie()
				.add("name",      paramName)
				.add("type",      "text")
				.add("maxlength", "4000");
		return field;
	}

	/**
	 * Cria um TextField de ícone com valor padrão predefinido.
	 */
	private TextField buildIconField(String id, String paramName, String defaultIcon) {
		TextField field = new TextField(model, id);
		field.setLabel(gt("Icon"));
		field.setValue(gt(defaultIcon));
		field.propertie()
				.add("name",      paramName)
				.add("type",      "text")
				.add("maxlength", "4000");
		return field;
	}
}