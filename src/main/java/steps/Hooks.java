package steps;

import Standard.factory.WebDriverFactory;
import Standard.inspect.Inspecionador;
import Standard.utils.evidencia.Evidencia;
import Standard.utils.evidencia.EvidenciaIterator;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    @Before(order = 0)
    public void beforeScenarioStart() {
        System.out.println("-----------------Start of Scenario-----------------");
    }

    @Before(order = 0)
    public void setUp() {
        WebDriverFactory.startChromeDriver();
    }

    @After(order = 0)
    public void afterScenarioFinish() {
        System.out.println("-----------------End of Scenario-----------------");
    }

    @After(order = 1)
    public void tearDown() {
        WebDriverFactory.killCurrentRunningDriver();
        if (!Evidencia.getDocument().isOpen()) {
            String Evd = EvidenciaIterator.getTeste();
            Inspecionador.getMessage("Evidencia gerada como: " + Evd);
        } else {
            Inspecionador.getMessage("Evidencia não foi fechada. Finalizando evidencia como 'FAILED'...");
            EvidenciaIterator.finishEvidencia(true);
        }
    }
}
