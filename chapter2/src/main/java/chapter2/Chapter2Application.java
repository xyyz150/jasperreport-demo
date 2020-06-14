package chapter2;

import common.JasperReportUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("test")
@SpringBootApplication
public class Chapter2Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter2Application.class, args);
	}

	@GetMapping("/jasper")
	public void getReport(@RequestParam("type") String reportType, HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", "小明");

		List<HashMap> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("Field1",  "Field1-" + i);
			item.put("Field2",  "Field2-" + i);
			list.add(item);
		}
		String jasperPath = JasperReportUtil.getJasperFileDir("chapter2");
		if (reportType.equals("pdf")) {
			JasperReportUtil.exportToPdf(jasperPath, parameters, list, response);
		} else if (reportType.equals("html")) {
			JasperReportUtil.exportToHtml(jasperPath, parameters, list, response);
		}
	}

}
