package nosi.webapps.sistema_de_carros.PageHelpers;

import nosi.core.webapp.Core;
import nosi.webapps.sistema_de_carros.dao.TblMarca;
import nosi.webapps.sistema_de_carros.pages.parametrizacao_marcas.Parametrizacao_marcas;
import nosi.webapps.sistema_de_carros.pages.parametrizacao_marcas.Parametrizacao_marcasView;

import java.util.ArrayList;
import java.util.List;

public class ParametrizacaoMarcasPageHelper {
    public void index(Parametrizacao_marcas model, Parametrizacao_marcasView view){
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
            List<Parametrizacao_marcas.Table_1> tblmarcaTable = new ArrayList<>();
            if(Core.isNotNull(tblmarcaList)){
                for(TblMarca tblmarca : tblmarcaList){
                    Parametrizacao_marcas.Table_1 row  = new Parametrizacao_marcas.Table_1();
                    row.setMarca_tbl(tblmarca.getNomeMarca());
                    row.setId_marca_tbl(tblmarca.getIdMarca().toString());
                    tblmarcaTable.add(row);
                }
                model.setTable_1(tblmarcaTable);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
