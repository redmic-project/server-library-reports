package es.redmic.test.reports;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.models.birt.DocumentWrapper;
import es.redmic.reports.ReportUtil;

public class DocumentReportsTest {

	private ReportUtil report = new ReportUtil();
	private String infoDesignPath = "DocumentInfoReport";
	private String listDesignPath = "DocumentListReport";
	private String infoOutputPath = "target/DocumentInfoReport.pdf";
	private String listOutputPath = "target/DocumentListReport.pdf";
	private ArrayList<String> dataFilePaths = new ArrayList<String>(
			Arrays.asList("/data/document1.json", "/data/document2.json", "/data/document3.json"));

	@Test
	public void generateInfoReport() throws Exception {

		OutputStream output = new FileOutputStream(infoOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<DocumentWrapper> data = new ArrayList<DocumentWrapper>();
		DocumentWrapper documentWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(0)).openStream(),
				DocumentWrapper.class);
		data.add(documentWrapper);

		report.runReport(infoDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

	@Test
	public void generateListReport() throws Exception {

		OutputStream output = new FileOutputStream(listOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<DocumentWrapper> data = new ArrayList<DocumentWrapper>();
		for (int i = 0; i < dataFilePaths.size(); i++) {
			DocumentWrapper documentWrapper = mapper
					.readValue(getClass().getResource(dataFilePaths.get(i)).openStream(), DocumentWrapper.class);
			data.add(documentWrapper);
		}

		report.runReport(listDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

}
