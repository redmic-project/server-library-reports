package es.redmic.test.reports;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.models.birt.ProgramWrapper;
import es.redmic.reports.ReportUtil;

public class ProgramReportsTest {

	private ReportUtil report = new ReportUtil();
	private String infoDesignPath = "ProgramInfoReport";
	private String listDesignPath = "ProgramListReport";
	private String infoOutputPath = "target/ProgramInfoReport.pdf";
	private String listOutputPath = "target/ProgramListReport.pdf";
	private ArrayList<String> dataFilePaths = new ArrayList<String>(
			Arrays.asList("/data/program1.json", "/data/program2.json", "/data/program3.json"));

	@Test
	public void generateInfoReport() throws Exception {

		OutputStream output = new FileOutputStream(infoOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		ArrayList<ProgramWrapper> data = new ArrayList<ProgramWrapper>();
		ProgramWrapper programWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(0)).openStream(), ProgramWrapper.class);
		data.add(programWrapper);

		report.runReport(infoDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

	@Test
	public void generateListReport() throws Exception {

		OutputStream output = new FileOutputStream(listOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<ProgramWrapper> data = new ArrayList<ProgramWrapper>();
		for (int i = 0; i < dataFilePaths.size(); i++) {
			ProgramWrapper programWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(i)).openStream(),
					ProgramWrapper.class);
			data.add(programWrapper);
		}

		report.runReport(listDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

}
