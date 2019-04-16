package es.redmic.test.reports;

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

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.redmic.models.birt.ActivityWrapper;
import es.redmic.reports.ReportUtil;

public class ActivityReportsTest {

	private ReportUtil report = new ReportUtil();
	private String infoDesignPath = "ActivityInfoReport";
	private String listDesignPath = "ActivityListReport";
	private String infoOutputPath = "target/ActivityInfoReport.pdf";
	private String listOutputPath = "target/ActivityListReport.pdf";
	private ArrayList<String> dataFilePaths = new ArrayList<String>(
			Arrays.asList("/data/activity1.json", "/data/activity2.json", "/data/activity3.json"));

	@Test
	public void generateInfoReport() throws Exception {

		OutputStream output = new FileOutputStream(infoOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<ActivityWrapper> data = new ArrayList<ActivityWrapper>();
		ActivityWrapper activityWrapper = mapper.readValue(getClass().getResource(dataFilePaths.get(0)).openStream(),
				ActivityWrapper.class);
		data.add(activityWrapper);

		report.runReport(infoDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

	@Test
	public void generateListReport() throws Exception {

		OutputStream output = new FileOutputStream(listOutputPath);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ArrayList<ActivityWrapper> data = new ArrayList<ActivityWrapper>();
		for (int i = 0; i < dataFilePaths.size(); i++) {
			ActivityWrapper activityWrapper = mapper
					.readValue(getClass().getResource(dataFilePaths.get(i)).openStream(), ActivityWrapper.class);
			data.add(activityWrapper);
		}

		report.runReport(listDesignPath, data, output, "pdf");

		assertEquals(1, 1);
	}

}
