package br.com.mariapuri.mydom.reports;

import java.util.List;

import org.flywaydb.core.internal.util.StringUtils;

import br.com.mariapuri.mydom.app.domain.dto.PersonDTO;
import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.reports.custom.BaseReport;

public class PersonReport extends BaseReport<PersonDTO, PersonDTO> {

  public PersonReport(List<PersonDTO> list, PersonDTO filter, UserModel user) {
    super(list, filter, user);
    setParameters();
  }

  private void setParameters() {

    var selectedFilters = "";
    // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    setJasperReportName("/reports/report-name.jasper");
    setJasperSheetName("/reports/report-name.jasper");

    if (this.filter != null) {

      if (this.filter.getName() != null && StringUtils.hasText(this.filter.getName())) {
        selectedFilters += "Nome: " + this.filter.getName() + ";";
      }

      paramiters.put("SELECTED_FILTERS", selectedFilters);

    }

  }

}
