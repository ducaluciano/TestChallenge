package Tests;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Pages.LandingPage;

public class TestChallenge extends BaseTest {

	public TestChallenge() {
		super();
	}

	@Test
	public void test1() {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.buscar("Heladera");
		landingPage.setFiltroTexto("Heladeras");
		
		landingPage.contienenMarcaFiltrada();
		
	}

	
}