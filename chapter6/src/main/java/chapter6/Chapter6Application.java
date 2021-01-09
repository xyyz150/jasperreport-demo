package chapter6;

import common.JasperReportUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController()
@RequestMapping("test")
@SpringBootApplication
public class Chapter6Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter6Application.class, args);
    }

    @GetMapping("/jasper")
    public void getReport(@RequestParam("type") String reportType, HttpServletResponse response)
            throws Exception {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        List<Map> studentList = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            Map s1 = new HashMap();
            s1.put("ID", 1 + "");
            s1.put("Name", "张三" + i);
            s1.put("Work", "work " + i);
            studentList.add(s1);
        }
        parameters.put("dataList", studentList);
        String jasperPath = JasperReportUtil.getJasperFileDir("chapter6-2");
        if (reportType.equals("pdf")) {
            JasperReportUtil.exportToPdf(jasperPath, parameters, null, response);
        } else if (reportType.equals("html")) {
            JasperReportUtil.exportToHtml(jasperPath, parameters, null, response);
        }
    }

}
