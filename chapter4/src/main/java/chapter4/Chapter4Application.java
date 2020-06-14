package chapter4;

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
public class Chapter4Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter4Application.class, args);
	}

	@GetMapping("/jasper")
	public void getReport(@RequestParam("type") String reportType, HttpServletResponse response)
			throws Exception {
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		List<Map> teachList=new ArrayList<>();
		Map t1=new HashMap();
		t1.put("name","张老师");
		t1.put("job","语文老师");
		teachList.add(t1);
		Map t2=new HashMap();
		t2.put("name","王老师");
		t2.put("job","数学老师");
		teachList.add(t2);
		Map t3=new HashMap();
		t3.put("name","刘老师");
		t3.put("job","英语老师");
		teachList.add(t3);
		parameters.put("teachList", teachList);

		List<HashMap> list = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("grade",  i+"年级学生信息:");

			List<Map> studentList=new ArrayList<>();
			Map s1=new HashMap();
			s1.put("name","张"+i);
			s1.put("age",i+"");
			s1.put("sex",i%2==0?"男":"女");
			studentList.add(s1);
			Map s2=new HashMap();
			s2.put("name","王"+i);
			s2.put("age",i+"");
			s2.put("sex",i%2==0?"男":"女");
			studentList.add(s2);
			Map s3=new HashMap();
			s3.put("name","刘"+i);
			s3.put("age",i+"");
			s3.put("sex",i%2==0?"男":"女");
			studentList.add(s3);

			item.put("studentList",  studentList);
			list.add(item);
		}
		String jasperPath = JasperReportUtil.getJasperFileDir("chapter4");
		if (reportType.equals("pdf")) {
			JasperReportUtil.exportToPdf(jasperPath, parameters, list, response);
		} else if (reportType.equals("html")) {
			JasperReportUtil.exportToHtml(jasperPath, parameters, list, response);
		}
	}

}
