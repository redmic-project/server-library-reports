package es.redmic.reports;

/*-
 * #%L
 * Reports
 * %%
 * Copyright (C) 2019 REDMIC Project / Server
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
