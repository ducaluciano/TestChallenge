package Pages;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends AbstractPageObject {
	public LandingPage(WebDriver driver) {
		
		super(driver);
	}

	@Override
	protected By getElementoPrincipal() {
	
		By elemento =  By.id("__next");
		return elemento;
	}
	
	
	By searchInput = By.className("InputBar__SearchInput-t6v2m1-1");
	By searchButton = By.className("InputBar__SearchButton-t6v2m1-2");
	By filtros =  By.name("aggregations");
	By grillaResultado =  By.className("listingDesktopstyled__SearchResultList-wzwlr8-6");
	By tituloItem = By.className("PieceTitle-sc-1eg7yvt-0");
	By checkboxMarca =  By.name("checkboxBrand");
	By filtroMarcas = 	By.name("brandsFilter");
	By botonSiguiente = By.className ("ant-pagination-next");
	By paginador = By.className ("ant-pagination");
	By paginadorItem = By.className ("ant-pagination-item");
	By cantidadMarca = By.className ("quantity");
	

	
	
	 //Click en buscar

    public void clickBuscar(){
    	
            driver.findElement(searchButton).click();

    }
    
    public int paginationSize(){
    	
    	return driver.findElement(paginador).findElements(paginadorItem).size();

}

    //Set criterio de busqueda

    public void setBusqueda(String textoBusqueda){
    	
        driver.findElement(searchInput).sendKeys(textoBusqueda);

    }
    
    public void setFiltroTipo (String textoFiltro) 
    {

    	driver.findElement(By.partialLinkText(textoFiltro+" (")).click();
    }
    
    public int contienenMarcaFiltrada()
    {
    	int cantidadTotal = 0;
    	String marca = filtrarPorPrimeraMarca();
    	int paginationSize = paginationSize();
    	for(int i=0; i< paginationSize; i++){
    	cantidadTotal = checkMarca(marca, cantidadTotal);
    	driver.findElement(botonSiguiente).click();
    	
    	}
    	
    	return cantidadTotal;
		
    }
    
    public String getCantidadMarca ()
    {
    	return  driver.findElement(filtroMarcas).findElement(cantidadMarca).getText();
    }
    
    public String filtrarPorPrimeraMarca () //navega a la primer marca y devuelve el nombre
    {
    	WebElement primerMarca = driver.findElement(filtroMarcas).findElements(checkboxMarca).get(0);
   	 
    	String marca = primerMarca.getAttribute("value");
    	
    	primerMarca.findElement(By.xpath("./..")).click(); //va a el padre HREF
    	
       
    	return marca;
    }
    
    public String getBreadcrumbText ()
    {
    	return driver.findElement(By.name("breadcrumb")).getText();
    }
    
    public List<WebElement> getListadoMostrado ()
    {
    	List<WebElement> lista = (driver.findElement(grillaResultado).findElements(tituloItem));
    
    	//WebElement siguiente = driver.findElement(botonSiguiente);
    	
    	
    	return (lista);
    }
    
 
    // Buscar
    public void buscar(String textoBusqueda){

        //Llena el campo de busqueda
        this.setBusqueda(textoBusqueda);

        //Click buscar
        this.clickBuscar();        
    }
    
    public Integer checkMarca (String marca, int cant) {
    	List<WebElement> elementos = getListadoMostrado();
    	
    	for(int i=0; i< elementos.size(); i++){
    		cant++;
    		assertTrue("El elemento "+elementos.get(i).getText()+ " no contiene la marca "+ marca+" en su titulo", elementos.get(i).getText().toLowerCase().contains(marca));
    		
    	}
    	
    	return cant;
    	
    }
    
   		
    	}
  