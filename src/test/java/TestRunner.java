import java.util.ArrayList;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

/**
 * Created by Ivan_Matsur on 1/21/2017.
 */
public class TestRunner {

  public static void main(String[] args) {
    TestNG testNG = new TestNG();
    testNG.addListener(new TestListenerAdapter());

    final XmlSuite xmlSuite = new XmlSuite();
    xmlSuite.setName("TmpSuite");
    xmlSuite.setSuiteFiles(new ArrayList<String>() {
      {
        add("./src/main/resources/suites/config.xml");
      }
    });

    testNG.setXmlSuites(new ArrayList<XmlSuite>() {
      {
        add(xmlSuite);
      }
    });

    testNG.run();
  }
}