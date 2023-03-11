package br.com.mariapuri.mydom.reports.custom;

import java.util.List;

import br.com.mariapuri.mydom.app.domain.model.UserModel;
import br.com.mariapuri.mydom.enums.ReportPaginationType;
import br.com.mariapuri.mydom.enums.ReportType;

public abstract class BaseReport<T, F> extends GenerateReport<T> {

	protected final List<T> list;
	protected final F filter;
	protected final UserModel user;

	private String jasperReportName;
	private String jasperSheetName;


	protected BaseReport(List<T> list, F filter, UserModel user) {
		this.list = list;
		this.filter = filter;
		this.user = user;
	}

	protected String getJasperReportName() {
		return jasperReportName;
	}

	protected void setJasperReportName(String jasperReportName) {
		this.jasperReportName = jasperReportName;
	}

	protected String getJasperSheetName() {
		return jasperSheetName;
	}

	protected void setJasperSheetName(String jasperSheetName) {
		this.jasperSheetName = jasperSheetName;
	}

	public byte[] getReport() throws Exception {
		setParametersToCreatReport(ReportPaginationType.WITH_PAGINATRION);
		return creatReport(jasperReportName, ReportType.PDF_LIST_CONTENT, list, user);
	}

	public byte[] getSheet() throws Exception {
		setParametersToCreatReport(ReportPaginationType.PAGINATIONLESS);
		return creatReport(jasperSheetName, ReportType.SHEET_LIST_CONTENT, list, user);
	}

	private void setParametersToCreatReport(ReportPaginationType pagiable) {
		paramiters.put("IS_IGNORE_PAGINATION", pagiable.isValue());
		paramiters.put("ROW_SIZE", this.list.size());
	}
}
