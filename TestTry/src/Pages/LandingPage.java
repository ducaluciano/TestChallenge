package Pages;
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
	By grillaResultado =  By.name("itemsGrid");
	By tituloItem = By.className("PieceTitle-sc-1eg7yvt-0");
	By checkboxMarca =  By.name("checkboxBrand");
	By filtroMarcas = 	By.name("brandsFilter");
	
	
	 //Click en buscar

    public void clickBuscar(){
    	
            driver.findElement(searchButton).click();

    }

    //Set criterio de busqueda

    public void setBusqueda(String textoBusqueda){
    	
        driver.findElement(searchInput).sendKeys(textoBusqueda);

    }
    
    public void setFiltroTexto (String textoFiltro) 
    {
    	driver.findElement(By.partialLinkText(textoFiltro)).click();
    }
    
    public boolean contienenMarcaFiltrada()
    {
    	String marca = filtrarPorPrimeraMarca();
    	List<WebElement> elementos = getListadoMostrado();
    	boolean correcto = true;
    	System.out.println(elementos.size());
    	
    	for(int i=0; i< elementos.size(); i++){
    		if (elementos.get(i).getText().toLowerCase().contains("ond"))
    	      {}
    		else {correcto = false;}
    		System.out.println(elementos.get(i).getText().toLowerCase().contains("ond"));
    		System.out.println(correcto);
    		System.out.println(marca);
    		System.out.println(elementos.get(i).getText());
    		
    	    }
    	
		return correcto;
    }
    
    public String filtrarPorPrimeraMarca () 
    {
    	WebElement marcasList =	driver.findElement(filtroMarcas);
    	String marca = marcasList.findElements(checkboxMarca).get(0).getAttribute("value");
    	driver.get(marcasList.findElements(By.tagName("a")).get(0).getAttribute("href")); //cant use click ?
    	
    	return marca;
    }
    
    public void getBreadcrumb ()
    {
    	driver.findElement(By.name("breadcrumb"));
    }
    
    public List<WebElement> getListadoMostrado ()
    {
    	return (driver.findElement(grillaResultado).findElements(tituloItem));
    }
    
 
    // Buscar
    public void buscar(String textoBusqueda){

        //Llena el campo de busqueda
        this.setBusqueda(textoBusqueda);

        //Click buscar
        this.clickBuscar();        
    }
   
}