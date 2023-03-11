package br.com.mariapuri.mydom.app.service.custom;

import java.util.List;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.reports.custom.StandartReport;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class BaseServiceWithStandartReport<MODEL, REPOSITORY, LIST, FILTER> extends BaseService<MODEL, REPOSITORY>  {

  public byte[] getReport(List<LIST> list, FILTER filter, String reportName) throws Exception {
    var user = new UserModel();
		var report = new StandartReport(list, filter, user, reportName);
    return report.getReport();
  }
  
  public byte[] getSheet(List<LIST> list, FILTER filter, String reportName) throws Exception {
    var user = new UserModel();
		var report = new StandartReport(list, filter, user, reportName);
    return report.getSheet();    
  } 	
  
}
