package chapter8;

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
public class Chapter7Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }

    @GetMapping("/jasper")
    public void getReport(@RequestParam("type") String reportType, HttpServletResponse response)
            throws Exception {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("username", "你好好好好好好好好好好好");
        List<Map> studentList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map s1 = new HashMap();
            s1.put("ID", 1 + "");
            String name = "";
            for (int j = 0; j < i; j++) {
                name += "张三";
            }
            s1.put("Name", "<font size=\"" + (name.length() > 10 ? 10 : 20) + "\">" + name + i + "</font>");
            studentList.add(s1);
        }
        String jasperPath = JasperReportUtil.getJasperFileDir("chapter8");
        if (reportType.equals("pdf")) {
            JasperReportUtil.exportToPdf(jasperPath, parameters, studentList, response);
        } else if (reportType.equals("html")) {
            JasperReportUtil.exportToHtml(jasperPath, parameters, studentList, response);
        }
    }
}
