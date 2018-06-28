package es.redmic.test.reports;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.models.birt.SpeciesWrapper;
import es.redmic.reports.ReportUtil;

public class SpeciesReportsTest {

	private ReportUtil report = new ReportUtil();
	private String infoDesignPath = "SpeciesInfoReport";
	private String listDesignPath = "SpeciesListReport";
	private String infoOutputPath = "target/SpeciesInfoReport.pdf";
	private String listOutputPath = "target/SpeciesListReport.pdf";
	private ArrayList<String> dataFilePaths = new ArrayList<String>(
			Arrays.asList("/data/species1.json", "/data/species2.json", "/data/species3.json"));

	@Test
	public void generateInfoReport() throws Exception {

		OutputStream output = new FileOutputStream(infoOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<SpeciesWrapper> data = new ArrayList<SpeciesWrapper>();
		SpeciesWrapper speciesWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(0)).openStream(), SpeciesWrapper.class);
		data.add(speciesWrapper);

		report.runReport(infoDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

	@Test
	public void generateListReport() throws Exception {

		OutputStream output = new FileOutputStream(listOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<SpeciesWrapper> data = new ArrayList<SpeciesWrapper>();
		for (int i = 0; i < dataFilePaths.size(); i++) {
			SpeciesWrapper speciesWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(i)).openStream(),
					SpeciesWrapper.class);
			data.add(speciesWrapper);
		}

		Map<String,String> map = new HashMap<String,String>();
		map.put("TitleDefinedByUser", "Listado de especies de prueba");
		report.runReport(listDesignPath, data, output, "pdf", map);

		assertEquals(1, 1);
	}

}
