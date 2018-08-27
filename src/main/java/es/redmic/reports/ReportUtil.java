package es.redmic.reports;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;

public class ReportUtil {
	
	private static final EngineConfig CONFIG = new EngineConfig();

	static {
		try {
			CONFIG.setLogConfig(null, Level);
			Platform.startup(CONFIG);
		} catch (BirtException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private HTMLRenderOption getHTMLRenderOptions(OutputStream outs) {

		// set render options including output type
		HTMLRenderOption options = new HTMLRenderOption();
		options.setOutputStream(outs);
		options.setSupportedImageFormats("PNG");
		options.setEmbeddable(true);
		options.setOutputFormat("html");
		return options;
	}

	private PDFRenderOption getPDFRenderOptions(OutputStream outs) {

		// set render options including output type
		PDFRenderOption options = new PDFRenderOption();
		options.setOutputStream(outs);
		options.setSupportedImageFormats("PNG");
		// options.setEmbeddable(true);
		options.setOutputFormat("pdf");
		return options;
	}

	private IRenderOption getODTRenderOptions(OutputStream outs) {

		// set render options including output type
		RenderOption options = new RenderOption();
		options.setOutputStream(outs);
		options.setSupportedImageFormats("PNG");
		// options.setEmbeddable(true);
		options.setOutputFormat("odt");
		return options;
	}

	@SuppressWarnings("unchecked")
	public void runReport(final String reportPathResource, ArrayList<?> data, OutputStream outs, String format, Map<String, String> params)
			throws Exception {
		
		InputStream reportResource;
		
		reportResource =  getClass().getResourceAsStream(ReportPaths.valueOf(reportPathResource).toString()); 
		

		IReportEngineFactory factory = (IReportEngineFactory) Platform
				.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);

		IReportEngine engine = factory.createReportEngine(CONFIG);
		engine.changeLogLevel(Level.ALL);

		final IReportRunnable design = engine.openReportDesign(reportResource);
		IRunAndRenderTask task = engine.createRunAndRenderTask(design);
		IRenderOption renderFormat = null;
		if (format.equals("pdf"))
			renderFormat = getPDFRenderOptions(outs);
		else if (format.equals("odt"))
			renderFormat = getODTRenderOptions(outs);
		else if (format.equals("html"))
			renderFormat = getHTMLRenderOptions(outs);
		else
			return;
		task.setRenderOption(renderFormat);

		task.getAppContext().put("data", data);
		
		if (params != null && !params.isEmpty()) {
			Iterator<String> i = params.keySet().iterator();
			while (i.hasNext()){
				String key = i.next();
				task.setParameter(key, params.get(key), key);
			}
		}

		task.run();
	}

	public void runReport(final String reportResource, ArrayList<?> data, OutputStream outs, String format) throws Exception {

		runReport(reportResource, data, outs, format, null);
	}

}
