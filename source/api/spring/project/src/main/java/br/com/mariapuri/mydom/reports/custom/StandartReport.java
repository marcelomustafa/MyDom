package br.com.mariapuri.mydom.reports.custom;

import java.util.List;

import br.com.mariapuri.mydom.app.domain.model.UserModel;

public class StandartReport<L, F> extends BaseReport<L, F>  {
	
	private String reportName;
	
  public StandartReport(List<L> list, F filter, UserModel user, String reportName) {
    super(list, filter, user);

    //"/reports/report-name.jasper"
    this.reportName = reportName;
    
    setParameters();
  }

  private void setParameters() {

    var selectedFilters = "";
    //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    setJasperReportName(reportName);
    setJasperSheetName(reportName);

    if (this.filter != null) {

//      if (this.filter.getName() != null && StringUtils.hasText(this.filter.getName())) {
//        selectedFilters += "Nome: " + this.filter.getName() + ";";
//      }

      paramiters.put("SELECTED_FILTERS", selectedFilters);

    }

  }	

}
