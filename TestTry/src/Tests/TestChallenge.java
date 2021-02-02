package Tests;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import Pages.LandingPage;

public class TestChallenge extends BaseTest {

	public TestChallenge() {
		super();
	}

	@Test
	public void test() {
		String filtroBusqueda = "Heladera";
		String filtroCategoria = "Heladeras";
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.buscar(filtroBusqueda);
		landingPage.setFiltroTipo(filtroCategoria);
		String cantidadFiltro = landingPage.getCantidadMarca();
		String total ="("+landingPage.contienenMarcaFiltrada()+")";
		assertEquals(cantidadFiltro, total, "La cantidad mostrada("+total+") no es igual a la cantidad de la lista"+cantidadFiltro);
		assertTrue("El Breadcrum no contiene la palabra Heladeras", landingPage.getBreadcrumbText().contains(filtroCategoria));
		
		
	}

	
}