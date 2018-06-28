package es.redmic.reports;

public enum ReportPaths {
	
	ActivityInfoReport("administrative/ActivityInfoReport.rptdesign"),
	ActivityListReport("administrative/ActivityListReport.rptdesign"),
	ProgramInfoReport("administrative/ProgramInfoReport.rptdesign"),
	ProgramListReport("administrative/ProgramListReport.rptdesign"),
	ProjectInfoReport("administrative/ProjectInfoReport.rptdesign"),
	ProjectListReport("administrative/ProjectListReport.rptdesign"),
	SpeciesInfoReport("administrative/taxonomy/SpeciesInfoReport.rptdesign"),
	SpeciesListReport("administrative/taxonomy/SpeciesListReport.rptdesign"),
	DocumentInfoReport("administrative/DocumentInfoReport.rptdesign"),
	DocumentListReport("administrative/DocumentListReport.rptdesign");
	
	private final String path;
	
	ReportPaths(final String path) {
		this.path = path;
	}
	
	@Override
    public String toString() {
        return "/resources/design/" + path;
    }
}
