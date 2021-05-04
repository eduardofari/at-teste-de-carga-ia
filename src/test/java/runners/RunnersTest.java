package runners;

import Standard.inspect.FileUtils;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/evidencias/html/evidence-html", "json:target/evidencias/json/evidencia.json"},
        snippets = SnippetType.CAMELCASE,
        monochrome = true,
        strict = true,
        features = {"classpath:features"},
        glue = {"classpath:steps"},
        tags = {"@preAprovacao"}
)
public class RunnersTest {

    @BeforeClass
    public static void setUp() {
        setWebDriverAmbientVariable();
        setAmbientTest();
        setBrowserTest();
        setPortalTest();
    }

    private static void setBrowserTest() {
        String browser = System.getProperty("browserdriver");
        if (browser == null || browser.isEmpty()) {
            System.getProperty("browserdriver", "Chrome");
        }
    }

    private static void setAmbientTest() {
        String ambiente = System.getProperty("ambiente.teste");
        if (ambiente == null || ambiente.isEmpty()) {
            System.setProperty("ambiente.teste", "HML");
        }
    }

    private static void setPortalTest() {
        String portal = System.getProperty("link.portal");
        if (portal == null || portal.isEmpty()) {
            System.setProperty("link.portal", "ODONTOPREV");
        }
    }

    private static void setWebDriverAmbientVariable() {
        //Path para o Chrome Driver
        System.setProperty("webdriver.chrome.driver",
                FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS, "path.chrome.driver"));
        //Path para o Firefox Driver
        System.setProperty("webdriver.gecko.driver",
                FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS, "path.firefox.driver"));
    }
}
