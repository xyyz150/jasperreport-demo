package chapter5;

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

            Random random = new Random();
            int count = ((Double) (random.nextDouble() * 10)).intValue();
            for(int j=0;j<=count;j++){
                Map s1 = new HashMap();
                s1.put("grade", i + "年级学生信息:");
                s1.put("name", "张三" + i+"-"+j);
                s1.put("score", j);
                s1.put("sex", j % 2 == 0 ? "男" : "女");
                studentList.add(s1);
            }
        }
        String jasperPath = JasperReportUtil.getJasperFileDir("chapter5_2");
        if (reportType.equals("pdf")) {
            JasperReportUtil.exportToPdf(jasperPath, parameters, studentList, response);
        } else if (reportType.equals("html")) {
            JasperReportUtil.exportToHtml(jasperPath, parameters, studentList, response);
        }
    }

}
