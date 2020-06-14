package chapter5;

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
import java.util.Map;

@RestController()
@RequestMapping("test")
@SpringBootApplication
public class Chapter5Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter5Application.class, args);
	}

	@GetMapping("/jasper")
	public void getReport(@RequestParam("type") String reportType, HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		List<Map> studentList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {

			Map s1=new HashMap();
			s1.put("grade",  i+"年级学生信息:");
			s1.put("name","张"+i);
			s1.put("score",i);
			s1.put("sex",i%2==0?"男":"女");
			studentList.add(s1);
			Map s2=new HashMap();
			s2.put("grade",  i+"年级学生信息:");
			s2.put("name","王"+i);
			s2.put("score",i);
			s2.put("sex",i%2==0?"男":"女");
			studentList.add(s2);
			Map s3=new HashMap();
			s3.put("grade",  i+"年级学生信息:");
			s3.put("name","刘"+i);
			s3.put("score",i);
			s3.put("sex",i%2==0?"男":"女");
			studentList.add(s3);
		}
		String jasperPath = JasperReportUtil.getJasperFileDir("chapter5_1");
		if (reportType.equals("pdf")) {
			JasperReportUtil.exportToPdf(jasperPath, parameters, studentList, response);
		} else if (reportType.equals("html")) {
			JasperReportUtil.exportToHtml(jasperPath, parameters, studentList, response);
		}
	}

}
