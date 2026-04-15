package nosi.webapps.sistema_de_carros.PageHelpers;

import nosi.core.webapp.Core;
import nosi.webapps.sistema_de_carros.dao.TblCarro;
import nosi.webapps.sistema_de_carros.pages.lista_de_carros_.Lista_de_carros_;
import nosi.webapps.sistema_de_carros.pages.lista_de_carros_.Lista_de_carros_View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listar_PageHelper {

    public void index( Lista_de_carros_ model) throws IOException, IllegalArgumentException, IllegalAccessException {
        try {
            TblCarro tblcarrofilter = new TblCarro().find();
            if (Core.isNotNullOrZero(model.getPreco())) {
                tblcarrofilter.andWhere("preco", "=", Core.toInt(model.getPreco()));
            }
            if (Core.isNotNullOrZero(model.getIntervalo_de_data())) {
                String datas[] = model.getIntervalo_de_data().trim().split(" / ");
                tblcarrofilter.andWhereBetween("dataRegistro", Core.formatDate(datas[0], "dd-mm-yyyy", "yyyy-mm-dd"), Core.formatDate(datas[1], "dd-mm-yyyy", "yyyy-mm-dd"));
            }
            if (Core.isNotNullOrZero(false)) {
                tblcarrofilter.andWhere("vendido", "=", false);
            }
            List<TblCarro> tblcarroList = tblcarrofilter.all();
            List<Lista_de_carros_.Table_1> tblcarroTable = new ArrayList<>();
            if (Core.isNotNull(tblcarroList)) {
                for (TblCarro tblcarro : tblcarroList) {
                    Lista_de_carros_.Table_1 row = new Lista_de_carros_.Table_1();
                    row.setFoto(Core.getLinkFileByUuid(tblcarro.getIdFoto()));
                    row.setMarca_tbl(tblcarro.getIdModeloFk().getIdMarcaFk().getNomeMarca());
                    row.setPreco_tbl(tblcarro.getPreco());
                    row.setModelo_tbl(tblcarro.getIdModeloFk().getNomeModelo());
                    row.setManual_tbl(Core.getLinkFileByUuid(tblcarro.getIdManual()));
                    row.setData_resgistro_tbl(Core.convertLocalDateToString(tblcarro.getDataRegistro(), Core.DD_MM_YYYY));
                    row.setId_carro_tbl(tblcarro.getIdCarro().toString());
                    tblcarroTable.add(row);
                }
                model.setTable_1(tblcarroTable);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
