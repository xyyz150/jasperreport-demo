package chapter3;

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
public class Chapter3Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter3Application.class, args);
	}

	@GetMapping("/jasper")
	public void getReport(@RequestParam("type") String reportType, HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", "小明");
		parameters.put("age", "18");
		parameters.put("pic","https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png?where=super");

		List<HashMap> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("Field1",  "Detail1-Field1-" + i);
			item.put("Field2",  "Detail1-Field2-" + i);
			item.put("Field3",  "Detail2-Field3-" + i);
			item.put("Field4",  "Detail2-Field4-" + i);
			item.put("Field5",  "Detail2-Field5-" + i);
			if(i%2==0){
				item.put("show1",  true);
			}else {
				item.put("show1",  false);
			}
			list.add(item);
		}
		String jasperPath = JasperReportUtil.getJasperFileDir("chapter3");
		if (reportType.equals("pdf")) {
			JasperReportUtil.exportToPdf(jasperPath, parameters, list, response);
		} else if (reportType.equals("html")) {
			JasperReportUtil.exportToHtml(jasperPath, parameters, list, response);
		}
	}

}
