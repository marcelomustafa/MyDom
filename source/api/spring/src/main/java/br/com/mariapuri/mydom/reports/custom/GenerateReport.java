package br.com.mariapuri.mydom.reports.custom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.enums.ReportType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public abstract class GenerateReport<T> {

//  @Autowired
//  private PropertyConfig property;

  //private static final Log logger = LogFactory.getLog(GenerateReport.class.getName());
  protected Map<String, Object> paramiters = new HashMap<>();

//  private String nameEmpresa = "NOT SETED";

  protected byte[] creatReport(String nameJasper, ReportType reportType, List<T> dataList, UserModel user)
      throws JRException {

    JasperPrint jasperPrint = new JasperPrint();
    byte[] bytes = {};

    setParametersToGenereteReport();
    InputStream inputStream = this.getClass().getResourceAsStream(nameJasper);

    switch (reportType) {
    case PDF_LIST_CONTENT:
    case SHEET_LIST_CONTENT:
      jasperPrint = JasperFillManager.fillReport(inputStream, paramiters, new JRBeanCollectionDataSource(dataList));
      break;

    case PDF_JSON_CONTENT:
    case SHEET_JSON_CONTENT:
      String dataJson = dataList.get(0).toString();
      InputStream jsonArray = new ByteArrayInputStream(dataJson.getBytes(StandardCharsets.UTF_8));
      jasperPrint = JasperFillManager.fillReport(inputStream, paramiters, new JsonDataSource(jsonArray));     
      break;
    }

    switch (reportType) {
    case PDF_LIST_CONTENT:
    case PDF_JSON_CONTENT:
      bytes = JasperExportManager.exportReportToPdf(jasperPrint);
      break;
      
    case SHEET_LIST_CONTENT:
    case SHEET_JSON_CONTENT:
      bytes = createJasperSheet(jasperPrint).toByteArray();
      break;
    }

    return bytes;
  }

  private ByteArrayOutputStream createJasperSheet(JasperPrint jasperPrint) throws JRException {

    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
    JRXlsxExporter exporter = new JRXlsxExporter();
    ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
    
    configuration.setOnePagePerSheet(false);   
    exporter.setExporterInput(SimpleExporterInput.getInstance(Arrays.asList(jasperPrint)));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
    exporter.setConfiguration(configuration);
    exporter.exportReport();

    return xlsReport;

  }

  private void setParametersToGenereteReport() {

//    try {
//      nameEmpresa = property.getNomeEmpresa();
//    } catch (Exception e) {
//      nameEmpresa = "NOT FOUNDED";
//    }

    paramiters.put("REPORT_LOCALE", new Locale("pt", "BR"));
    paramiters.put("REPORT_TIME_ZONE", TimeZone.getDefault());

  }

}
