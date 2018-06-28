package es.redmic.test.reports;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.models.birt.ProjectWrapper;
import es.redmic.reports.ReportUtil;

public class ProjectReportsTest {

	private ReportUtil report = new ReportUtil();
	private String infoDesignPath = "ProjectInfoReport";
	private String listDesignPath = "ProjectListReport";
	private String infoOutputPath = "target/ProjectInfoReport.pdf";
	private String listOutputPath = "target/ProjectListReport.pdf";
	private ArrayList<String> dataFilePaths = new ArrayList<String>(
			Arrays.asList("/data/project1.json", "/data/project2.json", "/data/project3.json"));

	@Test
	public void generateInfoReport() throws Exception {

		OutputStream output = new FileOutputStream(infoOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		ArrayList<ProjectWrapper> data = new ArrayList<ProjectWrapper>();
		ProjectWrapper projectWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(0)).openStream(), ProjectWrapper.class);
		data.add(projectWrapper);

		report.runReport(infoDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

	@Test
	public void generateListReport() throws Exception {

		OutputStream output = new FileOutputStream(listOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<ProjectWrapper> data = new ArrayList<ProjectWrapper>();
		for (int i = 0; i < dataFilePaths.size(); i++) {
			ProjectWrapper projectWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(i)).openStream(),
					ProjectWrapper.class);
			data.add(projectWrapper);
		}

		report.runReport(listDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

}
